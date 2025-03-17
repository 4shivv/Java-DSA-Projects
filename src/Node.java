/* Shivaganesh Nagamandla
 * COP 3330
 * Assignment 3
 */

package assignment3;

/**
 * Node class to represent an element in the linked list. This class does not
 * have a setItem method, as per the requirements.
 */
public class Node {
    private int item;
    private Node next;

    // Constructor to initialize the node with an item and the next node
    public Node(int item) {
        this.item = item;
        this.next = null;
    }

    // Getter for item
    public int getItem() {
        return item;
    }

    // Getter for the next node
    public Node getNext() {
        return next;
    }

    // Setter for the next node
    public void setNext(Node next) {
        this.next = next;
    }
} 