//Admin Dashboard Controller

package com.bitesandbanter.controller;

import com.bitesandbanter.service.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Controller for the Admin Dashboard view (Dashboard.fxml).
 */
public class DashboardController {

    private final DataService dataService = DataService.getInstance();
    
    // --- FXML Bindings (fx:id MUST be set in FXML) ---
    @FXML private Label totalEarnings;      // fx:id="totalEarnings"
    @FXML private Label pendingOrdersCount; // fx:id="pendingOrdersCount"
    @FXML private Label completedOrdersCount; // fx:id="completedOrdersCount"
    
    @FXML private VBox priorityOrdersChartArea; // fx:id="priorityOrdersChartArea"
    @FXML private VBox stockAlertsList;     // fx:id="stockAlertsList"
    @FXML private Button undoButton;           // Example: fx:id="undoButton"

    @FXML
    public void initialize() {
        updateStatsDisplay();
    }
    
    public void updateStatsDisplay() {
        double totalRevenue = dataService.getOrderHistory().stream().mapToDouble(Order::getTotal).sum();
        long completed = dataService.getOrderHistory().size();
        long pending = dataService.getOrderQueue().toList().stream().filter(o -> o.getStatus().equals("new") || o.getStatus().equals("preparing")).count();
        
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));

        if (totalEarnings != null) { totalEarnings.setText(currencyFormatter.format(totalRevenue)); }
        if (pendingOrdersCount != null) { pendingOrdersCount.setText(String.valueOf(pending)); }
        if (completedOrdersCount != null) { completedOrdersCount.setText(String.valueOf(completed)); }
    }
    
    @FXML
    private void handleUndoAction(ActionEvent event) {
        dataService.undoLastAdminAction();
        updateStatsDisplay();
    }
    
    // --- Sidebar Navigation Handlers ---
    @FXML private void handleOrdersClick(ActionEvent event) { System.out.println("Switch to Orders View"); }
    @FXML private void handleMenuClick(ActionEvent event) { System.out.println("Switch to Menu View"); }
    @FXML private void handleLogoutClick(ActionEvent event) { System.out.println("Logout triggered"); }
}
