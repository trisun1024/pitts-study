package jip45_Assignment2;

import java.util.Hashtable;

import javax.swing.JOptionPane;

public class UnitConverter {
	
	public static double convertAlgorithm(double Number, String Unit) {
		
		/* 
		 * Logic convert
		 * cm = in
		 * yr = m
		 * oz = gm
		 * lb = kg
		 */
		
		// Value Match 
		
		final double in2cm = 2.54;
		final double yr2m = 0.9144;
		final double oz2gm = 28.349523125;
		final double lb2kg = 0.45359237;
		
		Hashtable<String, Double> convertValueMatch = new Hashtable<>();
		convertValueMatch.put("in", new Double(in2cm));
		convertValueMatch.put("cm", new Double(1/in2cm));
		convertValueMatch.put("yr", new Double(yr2m));
		convertValueMatch.put("m", new Double(1/yr2m));
		convertValueMatch.put("oz", new Double(oz2gm));
		convertValueMatch.put("gm", new Double(1/oz2gm));
		convertValueMatch.put("lb", new Double(lb2kg));
		convertValueMatch.put("kg", new Double(1/lb2kg));
		
		// Build matching algorithm
		
		double convertFactor = convertValueMatch.get(Unit.toLowerCase());
		
		double outputValue = Number*convertFactor;
		
		return outputValue;
		
	}

	public static void main(String[] args) {

		String inputString = JOptionPane.showInputDialog("Please input your distance or weight amount for converting");

		double convertNumber = Double.parseDouble(inputString.split(" ")[0]);
		String convertUnit = inputString.split(" ")[1];
		
		double outputFinal = convertAlgorithm(convertNumber, convertUnit);
		
		String outputUnit = null;
		if (convertUnit.equalsIgnoreCase("in")) {
			outputUnit = "cm";
		} else if (convertUnit.equalsIgnoreCase("cm")) {
			outputUnit = "in";
		} else if (convertUnit.equalsIgnoreCase("yr")) {
			outputUnit = "m";
		} else if (convertUnit.equalsIgnoreCase("m")) {
			outputUnit = "yr";
		} else if (convertUnit.equalsIgnoreCase("oz")) {
			outputUnit = "gm";
		} else if (convertUnit.equalsIgnoreCase("gm")) {
			outputUnit = "oz";
		} else if (convertUnit.equalsIgnoreCase("lb")) {
			outputUnit = "kg";
		} else if (convertUnit.equalsIgnoreCase("kg")) {
			outputUnit = "lb";
		} 

		System.out.println(inputString + " = " + outputFinal + " " + outputUnit);
		
	}

}
