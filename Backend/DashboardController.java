package com.bitesandbanter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Controller for the Admin Dashboard view (Dashboard.fxml).
 * Displays summarized statistics, charts, and stock alerts.
 */
public class DashboardController {

    // --- FXML Bindings from Dashboard.fxml ---
    // IMPORTANT: You must add these 'fx:id's to the correct Labels in your FXML!
    @FXML private Label totalEarnings;      [cite_start]// Linked to the Label showing revenue (text="0.00" [cite: 22])
    @FXML private Label pendingOrdersCount; [cite_start]// Linked to the Label showing pending count (text="0" [cite: 28])
    @FXML private Label completedOrdersCount; [cite_start]// Linked to the Label showing completed count (text="0" [cite: 32])
    
    @FXML private VBox priorityOrdersChartArea; [cite_start]// Container for the Chart (VBox at [cite: 35])
    @FXML private VBox stockAlertsList;     [cite_start]// Container for stock alerts (VBox at [cite: 38])

    // --- Sidebar Button Handlers (Linked to Buttons in Dashboard.fxml) ---
    // These methods handle navigation within the Admin view.
    @FXML private void handleDashboardClick(ActionEvent event) { /* Already here */ }
    @FXML private void handleOrdersClick(ActionEvent event) { 
        System.out.println("Switch to Orders View");
        // Logic to replace the center of the BorderPane with the Orders FXML
    }
    @FXML private void handleInventoryClick(ActionEvent event) { 
        System.out.println("Switch to Inventory View");
    }
    @FXML private void handleMenuClick(ActionEvent event) { 
        System.out.println("Switch to Menu View");
    }
    
    [cite_start]// fx:id on the Logout Button in Dashboard.fxml [cite: 18]
    @FXML private void handleLogoutClick(ActionEvent event) {
        System.out.println("Logging out...");
        // Logic to switch back to the Login View (similar to BitesAndBanterController.handleLogout)
    }
    
    // --- Data Rendering ---
    
    @FXML
    public void initialize() {
        updateStatsDisplay();
        loadChartAndAlerts(); 
    }
    
    /**
     * Updates the Labels with current application statistics (Total Earnings, etc.).
     */
    public void updateStatsDisplay() {
        // In a real app, fetch data from the shared data service/controller
        double revenue = 15000.75; 
        int pending = 5;
        int completed = 95;
        
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));

        if (totalEarnings != null) {
            totalEarnings.setText(currencyFormatter.format(revenue));
        }

        if (pendingOrdersCount != null) {
            pendingOrdersCount.setText(String.valueOf(pending));
        }
        
        if (completedOrdersCount != null) {
            completedOrdersCount.setText(String.valueOf(completed));
        }
    }
    
    private void loadChartAndAlerts() {
        // Placeholder for charts and alerts (replaces Chart.js/HTML display logic)
        if (priorityOrdersChartArea != null) {
            Label chartPlaceholder = new Label("Chart Placeholder: Priority Orders");
            priorityOrdersChartArea.getChildren().add(chartPlaceholder);
        }
        
        if (stockAlertsList != null) {
            Label alertPlaceholder = new Label("Placeholder: Low Stock Alerts");
            stockAlertsList.getChildren().add(alertPlaceholder);
        }
    }
}
