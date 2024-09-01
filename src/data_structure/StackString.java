package data_structure;

import java.util.EmptyStackException;

public class StackString {
    private Node top;
    private int size;

    private static class Node {
        private String data;
        private Node next;

        Node(String data) {
            this.data = data;
        }
    }

    public StackString() {
        top = null;
        size = 0;
    }

    public void push(String item) {
        Node t = new Node(item);
        t.next = top;
        top = t;
        size++;
    }

    public String pop() {
        if (top == null) {
            throw new EmptyStackException();
        }
        String item = top.data;
        top = top.next;
        size--;
        return item;
    }

    public String peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current = top;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }
}
