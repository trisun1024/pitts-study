package edu.pitt.menuManager;



public class MenuTest {

	public static void main(String[] args) {
		// creation of menu items (2x entree, 1x side, 2x salad, 1x dessert)	
		Entree entreeChicken = new Entree("Sizzling Chicken & Shrimp.",
				"Garlic-marinated all-natural chicken and shrimp tossed with onions and peppers.",
				1050);
		Entree entreePasta = new Entree("Cajun Shrimp Pasta.",
				"Sautéed all-natural chicken and red bell peppers tossed with fettuccine ribbons in a spicy Cajun Alfredo sauce.",
				1010);
		Side sidePotatoes = new Side("Creamy Mashed Potatoes.", "Fresh Idaho potatoes mashed to creamy perfection.", 210);
		Salad saladHouse = new Salad("House Salad.", "Romaine and iceberg lettuce, served with carrots, cucumbers, tomatoes and choice of dressings.", 210);
		Salad saladCaeser = new Salad("Caeser Salad.", "Romaine lettuce, parmesan cheese and baked croutons, served with Caesar dressing.", 370);
		Dessert dessertChocolate = new Dessert("Molten Lava Cake.", "Warm, rich chocolate cake served with vanilla ice cream and drizzled with chocolate sauce.", 230);

		// creation of 2 menus & assignment of dishes to the menus
		Menu menu1 = new Menu("Light Dinner");
		menu1.setEntree(entreePasta);
		menu1.setSalad(saladHouse);

		Menu menu2 = new Menu("Date Night");
		menu2.setEntree(entreeChicken);
		menu2.setSide(sidePotatoes);
		menu2.setSalad(saladCaeser);
		menu2.setDessert(dessertChocolate);

		// print menus and total calories for each
		System.out.println(menu1.getName());
		System.out.println(menu1.description());
		System.out.println("Total calories for this meal: " + menu1.totalCalories());

		System.out.println("\n" + menu2.getName());
		System.out.println(menu2.description());
		System.out.println("Total calories for this meal: " + menu2.totalCalories());
	}

}
