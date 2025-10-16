/**
 * Defines a stock item tracked in the Inventory (e.g., "Beef Patties").
 */

package com.bitesandbanter.model;

public class InventoryItem {
    private String name;
    private int currentStock;
    private int minStock; // Minimum level for low stock alerts

    public InventoryItem(String name, int currentStock, int minStock) {
        this.name = name;
        this.currentStock = currentStock;
        this.minStock = minStock;
    }

    public String getName() { return name; }
    public int getCurrentStock() { return currentStock; }
    public void setCurrentStock(int currentStock) { this.currentStock = currentStock; }
    public int getMinStock() { return minStock; }
}
