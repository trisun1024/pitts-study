package CircleAreaPerimeter;

import javax.swing.JOptionPane;

public class CircleAreaPerimeter {
	
	public static void main(String[] args) {
		
		// Input radius of circle
		String rStr = JOptionPane.showInputDialog("Input the length of triangle side a");
		
		// Convert to double
		double r = Double.parseDouble(rStr);
		
		// Use predefined Constant PI compute area and perimeter
		
		final double powRateTwo = 2.0;
		double area = Math.PI * Math.pow(r, powRateTwo);
		final int constantValue = 2;
		double perimeter = constantValue * Math.PI * r;
		
		// Round result
		
		final int divNum1 = 1000;  //round in 3
		final int divNum2 = 100;  //round in 2
		double areaRound = (int) Math.round(area * divNum1)/(double) divNum1;  // round in 3
		double perimeterRound = (int) Math.round(perimeter * divNum2)/(double) divNum2;  // round in 2
		
		// Output result
		
		JOptionPane.showMessageDialog(null, "The circle of a radius " + rStr + " has an area of " + areaRound + " and a perimeter of " + perimeterRound);
	
	}

}
