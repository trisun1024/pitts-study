package jip45_lab4;

import java.util.Scanner;

public class DivisionAlgorithm {

	public static int baseCalculator(int inputValue, int base) {
		int i = 0;
		int finalValue = inputValue;
		while (finalValue >= base) {
			int nextValue = finalValue / base;
			finalValue = nextValue;
			i++;
		}
		return i;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);

		int valueOne;
		do {
			System.out.println("Please enter value one, which is greater than zero: ");
			valueOne = in.nextInt();

		} while (valueOne < 0);

		int baseValue;
		do {
			System.out.println("Please enter the base value, which is greater than zero: ");
			baseValue = in.nextInt();

		} while (baseValue < 0);

		int valueOneBase = baseCalculator(valueOne, baseValue);

		System.out.println(valueOneBase);

		in.close();

	}

}
