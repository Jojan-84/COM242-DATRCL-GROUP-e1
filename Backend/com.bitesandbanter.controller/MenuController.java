//Admin Menu Logic

package com.bitesandbanter.controller;

import com.bitesandbanter.service.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

/**
 * Controller for the Admin Menu Management screen (Menu.fxml).
 * Handles menu item CRUD and UNDO functions.
 */
public class MenuController {
    
    private final DataService dataService = DataService.getInstance();
    
    // --- FXML Bindings (MUST match the fx:id in Menu.fxml) ---
    @FXML private Button AddMenuItem;         // fx:id="AddMenuItem"
    @FXML private FlowPane menuFlowPane;      // fx:id="menuFlowPane"
    @FXML private Button undoButton;           // Example: fx:id="undoButton"

    @FXML
    public void initialize() {
        renderMenuItems();
    }
    
    public void renderMenuItems() {
        // Populates the menuFlowPane using dataService.getMenuItems()
        // Must be called after any CRUD or UNDO operation.
    }

    @FXML
    private void handleAddMenuItem(ActionEvent event) {
        // Logic to add a new item and call renderMenuItems()
    }
    
    private void handleDeleteItem(String itemId) {
        if (dataService.deleteMenuItem(itemId)) {
            // Update UI after successful delete/push to stack
            renderMenuItems();
        }
    }
    
    // --- UNDO Feature Link ---
    @FXML
    private void handleUndoAction(ActionEvent event) {
        String result = dataService.undoLastAdminAction();
        // Refresh the display to show the restored item
        renderMenuItems(); 
        System.out.println("Undo Result: " + result);
    }
}
