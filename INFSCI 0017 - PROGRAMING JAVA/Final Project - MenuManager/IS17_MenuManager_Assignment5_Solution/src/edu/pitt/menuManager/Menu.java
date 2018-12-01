package edu.pitt.menuManager;

public class Menu {
	
	
	// variables creating a new menu name, entree, salad, side, and dessert for this menu
	private String name;
	private Entree entree;
	private Salad salad;
	private Side side;
	private Dessert dessert;

	public Menu(String name) {
		/** Constructor Menu(String)
		 * @param name a String
		 */
		
		this.name = name;
		entree = null;
		salad = null;
		side = null;
		dessert = null;
	}

	public Menu(String name, Entree entree, Side side) {
		/** Constructor Menu(String, Entree, Side)
		 * @param name a String
		 * @param entree an Entree
		 * @param side a Side
		 */
		
		this.name = name;
		this.entree = entree;
		this.side = side;
		salad = null;
		dessert = null;
	}

	public Menu(String name, Entree entree, Side side, Salad salad, Dessert dessert) {
		/** Constructor Menu(String, Entree, Side, Salad, Dessert)
		 * @param name a String
		 * @param entree an Entree
		 * @param side a Side
		 * @param salad a Salad
		 * @param dessert a Dessert
		 */
		
		this.name = name;
		this.entree = entree;
		this.side = side;
		this.salad = salad;
		this.dessert = dessert;
	}

	public int totalCalories(){
		/** Method totalCalories
		 * @return the sum of calories of all menu items
		 */
		
		int sumCals = 0;

		if (entree != null) {
			sumCals += entree.getCalories();
		}
		if (side != null) {
			sumCals += side.getCalories();
		}
		if (salad != null) {
			sumCals += salad.getCalories();
		}
		if (dessert != null) {
			sumCals += dessert.getCalories();
		}

		return sumCals;
	}

	public String description() {
		/** Method description
		 * @return a complete description of the existing
		 * 			menu items including the names and descriptions
		 */
		
		String menuDescription = "";

		if (entree != null) {
			menuDescription = menuDescription + "Entree: " + entree.getName() + ". " + entree.getDescription();
		} else {
			menuDescription = menuDescription + "Entree: N/A";
		}

		if (side != null) {
			menuDescription = menuDescription + "\nSide: " + side.getName() + ". " + side.getDescription();
		} else {
			menuDescription = menuDescription + "\nSide: N/A";
		}

		if (salad != null) {
			menuDescription = menuDescription + "\nSalad: " + salad.getName() + ". " + salad.getDescription();
		} else {
			menuDescription = menuDescription + "\nSalad: N/A";
		}

		if (dessert != null) {
			menuDescription = menuDescription + "\nDessert: " + dessert.getName() + ". " + dessert.getDescription();
		} else {
			menuDescription = menuDescription + "\nDessert: N/A";
		}

		return menuDescription;
	}


	/* the following are getters and setters for the variables defined above;
	 	traditional JavaDoc style comments not used to reduce redundancy */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Entree getEntree() {
		return entree;
	}

	public void setEntree(Entree entree) {
		this.entree = entree;
	}

	public Salad getSalad() {
		return salad;
	}

	public void setSalad(Salad salad) {
		this.salad = salad;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public Dessert getDessert() {
		return dessert;
	}

	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}
}
