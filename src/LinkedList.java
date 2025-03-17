/* Shivaganesh Nagamandla
 * COP 3330
 * Assignment 3
 */

package assignment3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LinkedList {
    private Node head;

    // Constructor to initialize the linked list
    public LinkedList() {
        head = null;
    }

    // Method to add a node to the end of the linked list
    public void add(int item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    // Method to process the linked list according to the given rules
    public void processList() {
        if (head == null) {
            return;
        }

        int count = -100;
        Node current = head;
        Node previous = null;

        while (current != null && current.getNext() != null) {
            Node nextNode = current.getNext();
            if (nextNode.getItem() == current.getItem() * 2 + 7) {
                // Perform the required deletion and insertion
                if (previous == null) {
                    head = nextNode.getNext();
                } else {
                    previous.setNext(nextNode.getNext());
                }
                Node newNode = new Node(count++);
                if (previous == null) {
                    newNode.setNext(head);
                    head = newNode;
                } else {
                    newNode.setNext(previous.getNext());
                    previous.setNext(newNode);
                }
                current = newNode.getNext();
                previous = newNode;
            } else {
                previous = current;
                current = nextNode;
            }
        }

        // Add count to the end of the list
        add(count);
    }

    // Method to load data from a file into the linked list
    public void loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        if (line != null) {
            String[] numbers = line.split(" ");
            for (String number : numbers) {
                int num = Integer.parseInt(number);
                if (num > 0) {
                    add(num);
                }
            }
        }
        reader.close();
    }

    // Method to save the linked list to a file
    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        Node current = head;
        while (current != null) {
            writer.write(current.getItem() + " ");
            current = current.getNext();
        }
        writer.close();
    }
} 