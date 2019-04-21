package edu.pitt.menuManager;

import java.util.ArrayList;
import java.util.Random;

public class MenuRandomize {
	
	private ArrayList<Entree> entrees = new ArrayList<Entree>();
	private ArrayList<Side> sides = new ArrayList<Side>();
	private ArrayList<Salad> salads = new ArrayList<Salad>();
	private ArrayList<Dessert> desserts = new ArrayList<Dessert>();
	
	public MenuRandomize(String entreeFile, String sideFile, String saladFile, String dessertFile) {
		/** Constructor MenuRandomize
		 * @param entreeFile a String
		 * @param sideFile a String
		 * @param saladFile a String
		 * @param dessertFile a String
		 */
		
		// the following lines fill the ArrayLists from the corresponding files
		entrees = FileManager.readEntrees(entreeFile);
		sides = FileManager.readSides(sideFile);
		salads = FileManager.readSalads(saladFile);
		desserts = FileManager.readDesserts(dessertFile);
	}

	public Menu randomMenu() {
		/** Method randomMenu
		 * @return the randomized menu
		 */
		
		Menu randMenu = new Menu("Here is your random menu:");	// menu created and given title
		
		for (int i = 0; i < 4; i++) {
			/* The code below generates a random number between 0 and 3 (the # of lines in each 
			 * text file is 4) - that number determines the random menu items. */ 
			Random r = new Random();
			final int LOWER_BOUND = 0;
			final int UPPER_BOUND = 3;
			int randomNum = r.nextInt(UPPER_BOUND-LOWER_BOUND) + LOWER_BOUND;
			
			/* the following switch fills the menu with random items;
			 	a switch was used to avoid several chaining if statements*/
			switch(i) {
				case 0:
					randMenu.setEntree(entrees.get(randomNum));
					break;
				case 1:
					randMenu.setSide(sides.get(randomNum));
					break;
				case 2:
					randMenu.setSalad(salads.get(randomNum));
					break;
				case 3:
					randMenu.setDessert(desserts.get(randomNum));
					break;
				default:
					break;
			}
		}
		
		return randMenu;
	}

}
