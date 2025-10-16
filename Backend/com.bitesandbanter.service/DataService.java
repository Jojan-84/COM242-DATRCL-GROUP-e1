//Backend Logic & Data Storage

package com.bitesandbanter.service;

import com.bitesandbanter.datastructure.PriorityQueue; 
import com.bitesandbanter.datastructure.Stack; 
import com.bitesandbanter.model.AdminAction;
import com.bitesandbanter.model.InventoryItem;
import com.bitesandbanter.model.Order;
import com.bitesandbanter.model.OrderItem;
import com.bitesandbanter.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Optional;

/**
 * Singleton service layer: Holds all application data (Menu, Inventory, Orders) and core logic.
 */
public class DataService {

    private static DataService instance;
    public static DataService getInstance() {
        if (instance == null) { instance = new DataService(); }
        return instance;
    }

    // --- Core Application State ---
    private final ObservableList<Product> menuItems = FXCollections.observableArrayList();
    private final Map<String, InventoryItem> inventory = new HashMap<>();
    private final PriorityQueue orderQueue = new PriorityQueue(); 
    private final List<Order> orderHistory = new ArrayList<>();
    private final Stack<AdminAction> undoStack = new Stack<>(); 
    private final AtomicInteger orderCounter = new AtomicInteger(1);
    private final Map<String, String> simpleUsers = Map.of("admin", "admin123"); 

    private DataService() { loadInitialData(); }

    private void loadInitialData() {
        // Initializes data based on the HTML content
        menuItems.addAll(Arrays.asList(
            new Product("menu-1", "Cheese Burger", 70.00, "Juicy beef patty with cheese.", 
                        Map.of("Beef Patties", 1, "Buns", 1, "Cheese Slices", 1)),
            new Product("menu-2", "French Fries", 100.00, "Crispy golden fries.", 
                        Map.of("French Fries", 1)),
            new Product("menu-3", "Soft Drink", 50.00, "Refreshing cold soda.", 
                        Map.of("Soda Syrup", 1))
        ));

        // Initializes Inventory data
        inventory.put("Beef Patties", new InventoryItem("Beef Patties", 100, 30));
        inventory.put("Buns", new InventoryItem("Buns", 100, 30));
        inventory.put("Cheese Slices", new InventoryItem("Cheese Slices", 100, 30));
        inventory.put("French Fries", new InventoryItem("French Fries", 100, 30));
        inventory.put("Soda Syrup", new InventoryItem("Soda Syrup", 100, 30));
    }
    
    // --- Public Getters ---
    public ObservableList<Product> getMenuItems() { return menuItems; }
    public PriorityQueue getOrderQueue() { return orderQueue; }
    public Map<String, InventoryItem> getInventory() { return inventory; }
    public List<Order> getOrderHistory() { return orderHistory; }

    // --- Core Business Logic (Simplified) ---
    public boolean authenticateUser(String username, String password) {
        return password.equals(simpleUsers.get(username)); 
    }

    public Optional<Order> placeOrder(List<OrderItem> items) {
        // ... (Inventory validation/deduction logic goes here) ...

        // Priority Logic (High, Medium, Low based on item count)
        int totalItemsCount = items.stream().mapToInt(OrderItem::getQuantity).sum();
        int priority = (totalItemsCount >= 5) ? 1 : ((totalItemsCount >= 3) ? 2 : 3);
        
        double total = items.stream().mapToDouble(OrderItem::getSubTotal).sum();
        String orderId = String.format("ord-%03d", orderCounter.getAndIncrement());
        
        Order newOrder = new Order(orderId, items, total, priority);
        orderQueue.enqueue(newOrder); 
        
        return Optional.of(newOrder);
    }
    
    // --- STACK/UNDO Functionality ---
    public boolean canUndo() { return !undoStack.isEmpty(); }

    public boolean deleteMenuItem(String productId) {
        Optional<Product> deletedProduct = menuItems.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst();

        if (deletedProduct.isPresent()) {
            menuItems.remove(deletedProduct.get());
            
            // PUSH: Record the DELETED item to allow UNDO
            undoStack.push(new AdminAction(
                "UNDO_ADD_MENU", deletedProduct.get(), "Deleted menu item: " + deletedProduct.get().getName()
            ));
            return true;
        }
        return false;
    }
    
    public String undoLastAdminAction() {
        if (!canUndo()) { return "No actions to undo."; }

        AdminAction action = undoStack.pop();
        
        switch (action.getType()) {
            case "UNDO_ADD_MENU":
                menuItems.add((Product) action.getData());
                return "Menu item restored.";
            case "UNDO_SUBTRACT_INV":
                // Logic to reverse inventory change
                return "Inventory restock reversed.";
            default:
                return "Unknown action type.";
        }
    }
}
