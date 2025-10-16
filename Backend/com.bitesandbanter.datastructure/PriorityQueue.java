
/**
 * PriorityQueue implementation for Order objects.
 * Orders are sorted based on the 'priority' field (1=highest priority, 3=lowest).
 */

package com.bitesandbanter.datastructure;

import com.bitesandbanter.model.Order;
import java.util.Optional;

public class PriorityQueue {
    // Uses a custom LinkedList to ensure insertion order by priority
    private LinkedList<Order> list = new LinkedList<>();

    public void enqueue(Order data) {
        Node<Order> newNode = new Node<>(data);

        // Insert logic ensures items with lower priority number are placed at the front
        if (list.isEmpty() || data.getPriority() < list.head.getData().getPriority()) {
            list.addToHead(data);
            return;
        }

        Node<Order> current = list.head;
        while (current.getNext() != null && data.getPriority() >= current.getNext().getData().getPriority()) {
            current = current.getNext();
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);
        if (newNode.getNext() == null) { list.tail = newNode; }
        list.size++;
    }

    public Order dequeue() {
        return list.removeFromHead(); // Always processes the highest priority (front of the list)
    }
    
    public java.util.List<Order> toList() { 
        return list.toList(); 
    }

    public Optional<Order> removeOrderById(String id) {
        return list.removeNode(order -> order.getId().equals(id));
    }
}
