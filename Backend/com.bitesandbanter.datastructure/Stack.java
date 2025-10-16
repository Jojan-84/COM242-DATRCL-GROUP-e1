/**
 * Generic Stack implementation (Last-In, First-Out or LIFO).
 * Used specifically for the Admin Undo feature to manage action history.
 */

package com.bitesandbanter.datastructure;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack<T> {
    private final LinkedList<T> list = new LinkedList<>();

    public void push(T data) {
        list.addFirst(data); // Add to head (TOP of Stack)
    }

    public T pop() {
        if (list.isEmpty()) { throw new EmptyStackException("Cannot pop from an empty stack."); }
        return list.removeFirst(); // Remove from head (TOP of Stack)
    }

    public T peek() {
        return list.peekFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public List<T> toList() {
        return new ArrayList<>(list);
    }
}
