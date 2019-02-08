package edu.pitt.menuManager;

public class MenuTester {


	public static void main(String[] args) {
		
		/* the following imports the appropriate files into a MenuRandomize
			item and then creates a random menu and prints it out */
		MenuRandomize randomize = new MenuRandomize("data/entrees.txt", "data/sides.txt", "data/salads.txt", "data/desserts.txt");
		
		Menu myMenu = randomize.randomMenu();
		
		System.out.println(myMenu.description()+"\nTotal calories: " + myMenu.totalCalories());

	}

}
