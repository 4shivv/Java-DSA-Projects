// Author: Shivaganesh Nagamandla
// Course: CS2 
// Semester: Spring 2025

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class GreedyRobots {
    // Class attributes as required
    private int[] robotRanges;
    private int[] buildingDistances;
    private int successfulDeliveries;
    private int unservedBuildings;
    private String robotFile;
    private String buildingFile;

    /**
     * Constructor for GreedyRobots
     * @param numRobots number of available robots
     * @param numBuildings number of buildings that need deliveries
     * @param robotFile name of the text file containing the maximum travel range of each robot
     * @param buildingFile name of the text file containing the distances of each building
     */
    public GreedyRobots(int numRobots, int numBuildings, String robotFile, String buildingFile) {
        robotRanges = new int[numRobots];
        buildingDistances = new int[numBuildings];
        successfulDeliveries = 0;
        unservedBuildings = 0;
        
        // Store the file names as instance variables
        this.robotFile = robotFile;
        this.buildingFile = buildingFile;
        
        // Read files to initialize the arrays
        readFiles();
    }

    /**
     * Reads the text files containing robot ranges and building distances
     */
    public void readFiles() {
        try {
            // Read robot ranges
            Scanner robotScanner = new Scanner(new File(this.robotFile));
            for (int i = 0; i < robotRanges.length; i++) {
                if (robotScanner.hasNextInt()) {
                    robotRanges[i] = robotScanner.nextInt();
                }
            }
            robotScanner.close();

            // Read building distances
            Scanner buildingScanner = new Scanner(new File(this.buildingFile));
            for (int i = 0; i < buildingDistances.length; i++) {
                if (buildingScanner.hasNextInt()) {
                    buildingDistances[i] = buildingScanner.nextInt();
                }
            }
            buildingScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
    }

    /**
     * Implements a greedy algorithm to maximize the number of deliveries
     */
    public void assignDeliveries() {
        // Sort robots by descending range (highest range first)
        Arrays.sort(robotRanges);
        reverseArray(robotRanges);
        
        // Sort buildings by ascending distance (closest first)
        Arrays.sort(buildingDistances);
        
        // Create an array to track which buildings have been served
        boolean[] buildingServed = new boolean[buildingDistances.length];
        
        // For each robot, assign it to as many buildings as possible
        for (int robot = 0; robot < robotRanges.length; robot++) {
            int remainingRange = robotRanges[robot];
            
            // Try to assign buildings to this robot
            for (int building = 0; building < buildingDistances.length; building++) {
                // If the building hasn't been served and the robot has enough range
                if (!buildingServed[building] && buildingDistances[building] <= remainingRange) {
                    // Assign the delivery
                    buildingServed[building] = true;
                    // Subtract the building's distance from the remaining range
                    remainingRange -= buildingDistances[building];
                }
            }
        }
        
        // Count successful deliveries and unserved buildings
        successfulDeliveries = 0;
        for (boolean served : buildingServed) {
            if (served) {
                successfulDeliveries++;
            }
        }
        unservedBuildings = buildingDistances.length - successfulDeliveries;
    }

    /**
     * Helper method to reverse an array
     * @param array the array to reverse
     */
    private void reverseArray(int[] array) {
        int left = 0;
        int right = array.length - 1;
        
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * Displays the results computed by assignDeliveries
     */
    public void displayResults() {
        System.out.println("Successful Deliveries: " + successfulDeliveries);
        System.out.println("Unserved Buildings: " + unservedBuildings);
    }
}