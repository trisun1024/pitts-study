package edu.pitt.menuManager;

public class Side {
	
	// variables for item name, description, and calorie count
	private String name;
	private String description;
	private int calories;

	public Side(String name, String description, int calories) {
		/** Constructor Side(String, String, int)
		 * @param name a String
		 * @param description a String
		 * @param calories an integer number
		 */
		
		this.name = name;
		this.description = description;
		this.calories = calories;
	}

	/* the following are getters and setters for the variables defined above;
 	traditional JavaDoc style comments not used to reduce redundancy */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}
}
