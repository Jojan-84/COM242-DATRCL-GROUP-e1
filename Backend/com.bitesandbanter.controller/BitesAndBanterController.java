// Customer/Main Controller

package com.bitesandbanter.controller;

import com.bitesandbanter.model.Product;
import com.bitesandbanter.model.OrderItem;
import com.bitesandbanter.service.DataService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Controller: Handles Customer Ordering, Login, and overall View Switching.
 */
public class BitesAndBanterController {

    private final DataService dataService = DataService.getInstance();
    private final ObservableList<Product> menuItems = dataService.getMenuItems();
    private final ObservableList<OrderItem> currentOrderItems = FXCollections.observableArrayList();

    // --- FXML Bindings (fx:id MUST be set in FXML) ---
    @FXML private VBox customerView;         // fx:id="customerView"
    @FXML private VBox loginView;            // fx:id="loginView"
    @FXML private VBox adminView;            // fx:id="adminView"
    
    @FXML private VBox menuItemsContainer;   // fx:id="menuItemsContainer" (For dynamic items)
    @FXML private ListView<OrderItem> orderList; // fx:id="orderList"
    @FXML private Label orderTotal;           // fx:id="orderTotal"
    @FXML private Button placeOrderBtn;      // fx:id="placeOrderBtn"
    
    @FXML private TextField username;         // fx:id="username"
    @FXML private PasswordField password;     // fx:id="password"

    @FXML
    public void initialize() {
        setupCustomerView(); 
        updateOrderSummary();
        orderList.setItems(currentOrderItems);
    }
    
    // --- UI/Data Logic ---
    private void setupCustomerView() {
        menuItemsContainer.getChildren().clear();
        for (Product item : menuItems) {
            VBox itemBox = new VBox(5);
            Spinner<Integer> quantitySpinner = new Spinner<>(0, 99, 0);
            
            // Link via ID: e.g., fx:id="qty-menu-1"
            quantitySpinner.setId("qty-" + item.getId()); 
            quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> updateOrderSummary());
            
            itemBox.getChildren().addAll(new Label(item.getName()), quantitySpinner);
            menuItemsContainer.getChildren().add(itemBox);
        }
    }

    private void updateOrderSummary() {
        double total = 0;
        currentOrderItems.clear(); 
        
        for (Product item : menuItems) {
            Spinner<Integer> spinner = (Spinner<Integer>) menuItemsContainer.lookup("#qty-" + item.getId());
            if (spinner != null && spinner.getValue() > 0) {
                OrderItem orderItem = new OrderItem(item, spinner.getValue());
                currentOrderItems.add(orderItem);
                total += orderItem.getSubTotal();
            }
        }
        orderTotal.setText(String.format("₱%.2f", total));
        placeOrderBtn.setDisable(total == 0);
    }
    
    // --- Event Handlers ---
    @FXML private void handleLoginView(ActionEvent event) { showView(loginView); }
    private void showAdminPanel() { showView(adminView); }

    @FXML
    private void placeOrder(ActionEvent event) {
        List<OrderItem> itemsToOrder = new ArrayList<>(currentOrderItems);
        dataService.placeOrder(itemsToOrder).ifPresent(order -> {
            // Update UI after successful order
        });
    }
    
    @FXML
    private void handleLogin(ActionEvent event) {
        if (dataService.authenticateUser(username.getText(), password.getText())) {
            showAdminPanel();
        } else {
            // Error handling
        }
    }
    private void showView(VBox view) { /* Logic to set view visibility */ }
}
