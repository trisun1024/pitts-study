package edu.pitt.is18.jip45.lab10;

import java.util.ArrayList;
import java.util.Arrays;

public class RecursionExercise {
    /**
     * Method: sumOfDigits(int)
     * Summary: Write a recursive function with the following signature
     * that returns the sum of the digits of an integer.
     *
     * @param x
     * @return
     */
    public static int sumOfDigits(int x) {
        if (x == 0) return 0;
        else return x % 10 + sumOfDigits(x / 10);

    }

    /**
     * Method: printArrayElements(int[], int)
     * Summary: Write a recursive function (DO NOT USE LOOPS) that prints all the elements of an array of integers,
     * one per line. The parameters to the function should be int a[], and int size.
     *
     * @param a
     * @param size
     */
    public static void printArrayElements(int a[], int size) {
        if (size != -1) {
            System.out.println(a[size]);
            printArrayElements(a, size - 1);
        }
    }

    public static void combinationFinder(int a[], int size) {
        if (size > a.length) {
            System.out.println("Requiring size of combination overflows the existing array!");
            return;
        }
        if (size == a.length) {
            System.out.println(Arrays.toString(a));
        }
        if (size < a.length) {
            boolean[] used = new boolean[a.length];
            combinationFinderRecursion(a, size, 0, 0, used);
        }
    }

    public static void combinationFinderRecursion(int a[], int size, int startIndex, int currentSize, boolean[] used) {
        // firstly defind the stop point of recursion
        // case 1: currentSize = size
        if (currentSize == size) {
            for (int i = 0; i < a.length; i++) {
                if (used[i]) {
                    System.out.print(a[i] + " ");
                }
            }
            System.out.println(); // add a new line to separate
            return;
        }
        // case 2: focusIndex exceeds array length
        if (startIndex == a.length) return;
        // recursion
        used[startIndex] = true;
        combinationFinderRecursion(a, size, startIndex + 1, currentSize + 1, used);
        used[startIndex] = false;
        combinationFinderRecursion(a, size, startIndex + 1, currentSize, used);
    }

    public static void main(String[] args) {
        // test sumOfDigits recursion
        System.out.println("Find sum of digits: ");
        int a = 234;
        int b = 12;
        int c = 39;
        int d = -234;
        System.out.println("sum of digits of a: " + sumOfDigits(a));
        System.out.println("sum of digits of b: " + sumOfDigits(b));
        System.out.println("sum of digits of c: " + sumOfDigits(c));
        System.out.println("sum of digits of d: " + sumOfDigits(d));

        //test printArrayElements recursion
        System.out.println("Print all array elements: ");
        int[] x = { 1, 2, 3, 4, 5 };
        printArrayElements(x, x.length - 1);

        // test recursion method of combinations of size 3
        System.out.println("Find all combinations of size 3: ");
        combinationFinder(x, 3);
    }
}
