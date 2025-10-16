/**
 * Represents a single line item in a customer's cart or a final order.
 * Simplified model: Customization fields were intentionally removed.
 */

package com.bitesandbanter.model;

public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    // Getters
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getSubTotal() { return product.getPrice() * quantity; }
}
