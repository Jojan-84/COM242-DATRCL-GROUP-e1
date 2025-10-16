//Admin Menu Controller

package com.bitesandbanter.controller;

import com.bitesandbanter.service.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller for the Admin Menu Management screen (Menu.fxml).
 */
public class MenuController {
    
    private final DataService dataService = DataService.getInstance();
    
    // --- FXML Bindings (fx:id MUST be set in Menu.fxml) ---
    @FXML private Button AddMenuItem;         // fx:id="AddMenuItem"
    @FXML private FlowPane menuFlowPane;      // fx:id="menuFlowPane"
    @FXML private Button undoButton;           // Example: fx:id="undoButton"

    @FXML
    public void initialize() {
        renderMenuItems();
    }
    
    public void renderMenuItems() {
        menuFlowPane.getChildren().clear();
        dataService.getMenuItems().forEach(item -> {
            VBox card = createMenuItemCard(item);
            menuFlowPane.getChildren().add(card);
        });
    }
    
    private VBox createMenuItemCard(Product item) {
        VBox card = new VBox(8);
        card.getChildren().add(new Label(item.getName())); 
        
        HBox buttonBox = new HBox(10);
        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> handleDeleteItem(item.getId())); 
        
        buttonBox.getChildren().addAll(new Button("Edit"), deleteBtn);
        card.getChildren().add(buttonBox);
        return card;
    }

    @FXML
    private void handleAddMenuItem(ActionEvent event) {
        // Logic to add a new item
    }
    
    private void handleDeleteItem(String itemId) {
        if (dataService.deleteMenuItem(itemId)) {
            renderMenuItems();
        }
    }
    
    @FXML
    private void handleUndoAction(ActionEvent event) {
        dataService.undoLastAdminAction();
        renderMenuItems(); 
    }
}
