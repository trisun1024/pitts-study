package PythagoreanThm;

import javax.swing.JOptionPane;

public class PythagoreanThm {
	
	public static void main(String[] args) {

		// Input triangle's two sides in String
		String aStr = JOptionPane.showInputDialog("Input the length of triangle side a");
		String bStr = JOptionPane.showInputDialog("Input the length of triangle side b");
		
		// Consider user will enter a number so do not need check the input
		
		// Convert String to Double
		double a = Double.parseDouble(aStr);
		double b = Double.parseDouble(bStr);
		
		// Calculate the third 
		
		final double powRateTwo = 2.0;
		double c = Math.sqrt(Math.pow(a, powRateTwo) + Math.pow(b, powRateTwo));
		
		// Round result
	
		final int divNum = 100;
		double cRound = (int) Math.round(c * divNum)/(double) divNum;
		
		// Output result
		
		JOptionPane.showMessageDialog(null, "The hypotenuse is " + cRound);
		
	}

}
