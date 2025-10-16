/**
 * Represents a complete Order in the system (used by the PriorityQueue and Admin Table).
 */

package com.bitesandbanter.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private String id;
    private List<OrderItem> items;
    private double total;
    private String status;   // e.g., "new", "preparing", "completed"
    private int priority;    // 1 (High) is processed before 3 (Low)
    private LocalDateTime timestamp;

    public Order(String id, List<OrderItem> items, double total, int priority) {
        this.id = id;
        this.items = items;
        this.total = total;
        this.priority = priority;
        this.status = "new";
        this.timestamp = LocalDateTime.now();
    }
    public String getId() { return id; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getPriority() { return priority; }
}
