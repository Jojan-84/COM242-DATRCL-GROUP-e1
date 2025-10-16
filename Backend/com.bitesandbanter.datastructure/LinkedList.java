/**
 * Foundational LinkedList class used as the underlying structure for the PriorityQueue.
 */

package com.bitesandbanter.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class LinkedList<T> {
    protected Node<T> head;
    protected Node<T> tail;
    protected int size;

    public boolean isEmpty() { return size == 0; }

    public void addToHead(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) { head = tail = newNode; } else { newNode.setNext(head); head = newNode; } size++;
    }

    public T removeFromHead() {
        if (isEmpty()) return null; T data = head.getData(); head = head.getNext(); if (head == null) tail = null; size--; return data;
    }
    
    public List<T> toList() {
        List<T> list = new ArrayList<>(); Node<T> current = head; while (current != null) { list.add(current.getData()); current = current.getNext(); } return list;
    }
    
    public Optional<T> removeNode(Predicate<T> condition) {
        // Logic to find and remove a node based on a condition (used by PriorityQueue to remove an Order by ID)
        if (isEmpty()) return Optional.empty(); if (condition.test(head.getData())) { return Optional.ofNullable(removeFromHead()); } return Optional.empty(); 
    }
}
