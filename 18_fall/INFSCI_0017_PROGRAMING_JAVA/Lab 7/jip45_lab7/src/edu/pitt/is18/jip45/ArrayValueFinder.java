package edu.pitt.is18.jip45;

import javax.swing.*;
import java.util.Random;

public class ArrayValueFinder {

    public static double max(double[] data) {
        double tempMaxValue = 0.0;
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                tempMaxValue = data[i];
            } else {
                if (data[i] >= tempMaxValue) {
                    tempMaxValue = data[i];
                }
            }
        }
        return tempMaxValue;
    }

    public static double min(double[] data) {
        double tempMinValue = 0.0;
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                tempMinValue = data[i];
            } else {
                if (data[i] <= tempMinValue) {
                    tempMinValue = data[i];
                }
            }
        }
        return tempMinValue;
    }

    public static double sum(double[] data) {
        double sum = 0.0;
        for (double element : data) {
            sum += element;
        }
        return sum;
    }

    public static double avg(double[] data) {
        double sum = 0.0;
        int number = 0;
        for (double element : data) {
            sum += element;
            number++;
        }
        double avg = sum / number;
        return avg;
    }

    public static void main(String[] args) {
        // Ask user to enter how many items will be entered
        String input = JOptionPane.showInputDialog("Please enter the number of items will be entered: ");

        // Convert into correct parameter setting and setup a double type of Array
        int numberOfItems = Integer.parseInt(input);
        double[] testData = new double[numberOfItems];

        // Fill the array with randomly generated values
        Random rd = new Random();
        for (int i = 0; i < testData.length; i++) {
            testData[i] = rd.nextDouble();
        }

        // Check array setting correctly
        System.out.println("--------------");
        System.out.println(testData.length);
        for (double element : testData) {
            System.out.println(element);
        }

        // Find max, min, sum, and average number by using methods
        double maxValue = max(testData);
        double minValue = min(testData);
        double sumTotal = sum(testData);
        double avgTotal = avg(testData);

        System.out.println("---------");
        System.out.println("Maximum of Array: " + maxValue);
        System.out.println("Minimum of Array: " + minValue);
        System.out.println("Sum of Array: " + sumTotal);
        System.out.println("Average of Array: " + avgTotal);
    }
}
