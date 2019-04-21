package jip45_HW3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class RunForestRun {

    public static void main(String[] args) {

        // Setup parameters, create 1 million entries
        final int DEFAULT_NUMBER_OF_ENTRIES = 1000000;
        final int DEFAULT_INPUT_STRING = 1;
        final int NANOSEC_TO_SEC = 1000000000;

        ArrayList<Integer> testArrayList = new ArrayList<>();
        LinkedList<Integer> testLinkedList = new LinkedList<>();

        long startArrayAdd = System.nanoTime();
        for (int i = 0; i < DEFAULT_NUMBER_OF_ENTRIES; i++) {
            testArrayList.add(DEFAULT_INPUT_STRING);
        }
        long stopArrayAdd = System.nanoTime();
        double durationArrayAdd = (double) (stopArrayAdd - startArrayAdd) / NANOSEC_TO_SEC;

        long startArrayGet = System.nanoTime();
        //for (int i = 0; i < DEFAULT_NUMBER_OF_ENTRIES; i++) {
        //    testArrayList.get(i);
        //}
        for (Iterator<Integer> iterator = testArrayList.iterator(); iterator.hasNext();) {
            iterator.next();
        }
        long stopArrayGet = System.nanoTime();
        double durationArrayGet = (double) (stopArrayGet - startArrayGet) / NANOSEC_TO_SEC;

        long startArrayRemove = System.nanoTime();
        for (int i = DEFAULT_NUMBER_OF_ENTRIES - 1; i >= 0; i--) {
            testArrayList.remove(i);
        }
        long stopArrayRemove = System.nanoTime();
        double durationArrayRemove = (double) (stopArrayRemove - startArrayRemove) / NANOSEC_TO_SEC;


        // LinkedList adding time
        long startLinkedListAdd = System.nanoTime();
        for (int i = 0; i < DEFAULT_NUMBER_OF_ENTRIES; i++) {
            testLinkedList.add(DEFAULT_INPUT_STRING);
        }
        long stopLinkedListAdd = System.nanoTime();
        double durationLinkedListAdd = (double) (stopLinkedListAdd - startLinkedListAdd) / NANOSEC_TO_SEC;


        // LinkedList get time
        long startLinkedListGet = System.nanoTime();
        for (Iterator<Integer> iterator = testLinkedList.iterator(); iterator.hasNext();) {
            iterator.next();
        }
        long stopLinkedListGet = System.nanoTime();
        double durationLinkedListGet = (double) (stopLinkedListGet - startLinkedListGet) / NANOSEC_TO_SEC;


        // LinkedList remove time
        long startLinkedListRemove = System.nanoTime();
        for (int i = DEFAULT_NUMBER_OF_ENTRIES - 1; i >= 0; i--) {
            testLinkedList.remove(i);
        }
        long stopLinkedListRemove = System.nanoTime();
        double durationLinkedListRemove = (double) (stopLinkedListRemove - startLinkedListRemove) / NANOSEC_TO_SEC;


        System.out.println("Java List Comparsion: ");
        System.out.println("ArrayList add take: " + durationArrayAdd + " sec");
        System.out.println("LinkedList add take: " + durationLinkedListAdd + " sec");
        System.out.println("ArrayList get take: " + durationArrayGet + " sec");
        System.out.println("LinkedList get take: " + durationLinkedListGet + " sec");
        System.out.println("ArrayList remove take: " + durationArrayRemove + " sec");
        System.out.println("LinkedList remove take: " + durationLinkedListRemove + " sec");


    }

}
