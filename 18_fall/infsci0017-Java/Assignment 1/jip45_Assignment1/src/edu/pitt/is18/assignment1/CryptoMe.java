package edu.pitt.is18.assignment1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CryptoMe {

    public static String RotThirteen(String input) {
        String output = "";

        ArrayList<Object> tempChar = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char a = input.charAt(i);
            if (a > 96 && a < 110) {
                a = (char) (a + 13);
                tempChar.add(a);
            } else if (a >= 110 && a < 123) {
                a = (char) (a - 13);
                tempChar.add(a);
            } else if (a > 64 && a < 78) {
                a = (char) (a + 13);
                tempChar.add(a);
            } else if (a >= 78 && a < 91) {
                a = (char) (a - 13);
                tempChar.add(a);
            } else {
                tempChar.add(a);
            }
        }
        System.out.println(tempChar);

        for (int i = 0; i < tempChar.size(); i++) {
            Object temp = tempChar.get(i);
            output += temp.toString();
        }
        return output;
    }

    public static String breakLines(int maxLength, String text) {
        String newText = "";
        final char separator = '\n';

        // Determine how many breaks have to insert into text
        int breaks;
        if (text.length() % maxLength == 0) {
            breaks = text.length() / maxLength - 1;
        } else {
            breaks = text.length() / maxLength;
        }

        if (breaks == 0) {
            newText = text;
            System.out.println(newText);
        } else {
            for (int i = 0; i < text.length(); i++) {
                if (i % maxLength == 0) {
                    newText += separator;
                }
                newText += text.charAt(i);
            }
        }
        return newText;
    }

    public static void main(String[] args) {

        // Parameters setup
        final int maxLen = 50;
        boolean valid = false;
        String stopChar = "q";

        do {
            String input = JOptionPane.showInputDialog("Please enter a sentence that you want to encrypt: ");
            if (input.equalsIgnoreCase(stopChar)) {
                valid = true;
            } else {
                System.out.println("\n" + input + "\n");

                String encryptInput = RotThirteen(input);
                System.out.println("\n" + encryptInput + "\n");

                String inputBreaks = breakLines(maxLen, encryptInput);
                System.out.println("\n" + inputBreaks + "\n");
                System.out.println("Encrypt by method ROT13 finished: \n" + inputBreaks);

                JOptionPane.showMessageDialog(null, inputBreaks);
            }
        } while (!valid);


    }
}