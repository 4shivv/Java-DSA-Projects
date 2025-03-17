/* Shivaganesh Nagamandla
 * COP 3330
 * Assignment 3
 */



import java.io.IOException;

/**
 * Main class to test the LinkedList functionality.
 */
public class LinkedListTester {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        try {
            // Load data from the file
            list.loadFromFile("data.txt");

            // Process the linked list
            list.processList();

            // Save the processed list to the file
            list.saveToFile("processed.txt");
        } catch (IOException e) {
            System.err.println("An error occurred while reading or writing the file: " + e.getMessage());
        }
    }
} 