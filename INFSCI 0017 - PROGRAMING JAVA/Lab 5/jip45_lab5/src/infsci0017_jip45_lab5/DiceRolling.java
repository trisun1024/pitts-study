package infsci0017_jip45_lab5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class DiceRolling {

	public static void RollDice(int timesOfRoll) {

		// Parameters setup
		// Dice have six faces and two dices are rolling in each time, conclude random total number 
		final int diceFace = 6;
		final int numberOfDiceRoll = 2;
		int random = diceFace * numberOfDiceRoll;
		
		ArrayList<Integer> diceList = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < timesOfRoll; i++) {
			int rolltemp = r.nextInt(random) + 1;
			while (rolltemp < numberOfDiceRoll) {
				int rolltemptemp = r.nextInt(random) + 1;
				rolltemp = rolltemptemp;
			}
			diceList.add(rolltemp);
		}
		// Print out result
		System.out.println("The result of " + timesOfRoll + " rolldice \n" + diceList);

		// Dice possible values generator
		// Create a HashMap to store the frequency of element
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

		for (int i : diceList) {
			Integer j = frequencyMap.get(i);
			frequencyMap.put(i, (j == null) ? 1 : j + 1);
		}

		// Display the occurrence of elements in the ArrayList
		System.out.println("The occurrence of different numbers is " + "\n");
		for (Map.Entry<Integer, Integer> val : frequencyMap.entrySet()) {
			System.out.println("Dice number " + val.getKey() + " " + "occurs: " + val.getValue() + " times");
		}

		// Combine occurrences with ArrayList and calculate properties
		final Double sizeTotal = (double) diceList.size();
		ArrayList<Object> rollDiceOutput = new ArrayList<>();
		for (int i = 0; i < diceList.size(); i++) {
			for (Map.Entry<Integer, Integer> val : frequencyMap.entrySet()) {

				if (diceList.get(i) == val.getKey()) {
					int diceValue = diceList.get(i);
					int property = val.getValue();
					String x = diceValue + " " + property / sizeTotal;
					rollDiceOutput.add(x);
				}
			}

		}

		// result
		System.out.println("------------------ \nFinal Result, values of dice vs probabilities of occurrence : ");
		for (int i = 0; i < rollDiceOutput.size(); i++) {
			System.out.println(rollDiceOutput.get(i));
		}
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);

		// Simple test
		RollDice(10);

		// Ask user for a real run
		
		System.out.println("Please input a number of times of dice rolling: ");
		int timesOfRoll = in.nextInt();

		boolean value = false;
		do {
			if (timesOfRoll>0) {
				RollDice(timesOfRoll);
                boolean yorn = false;
				do {
                    System.out.println("Do you want to continue this game? (y/n)");
                    String answer = in.next();
                    if (answer.equalsIgnoreCase("y")) {
                        yorn = true;
                        value = false;
                    } else if (answer.equalsIgnoreCase("n")) {
                        System.out.println("Program stop! ");
                        yorn = true;
                        value = true;
                    } else {
                        System.out.println("Incorrect input, please try again! ");
                    }
                } while (!yorn);
			} else {
				System.out.println("Please input a number greater than zero! ");
			}
		} while (!value);
	}

}
