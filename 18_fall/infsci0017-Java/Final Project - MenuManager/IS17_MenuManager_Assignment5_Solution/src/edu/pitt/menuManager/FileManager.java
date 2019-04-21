package edu.pitt.menuManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

public class FileManager {
	
	
	public static ArrayList<Entree> readEntrees(String fileName) {
		/** Method readEntrees(String)
		 * @param fileName a String
		 * @return an ArrayList of entrees read from a file
		 */
		
		// create new entree ArrayList
		ArrayList<Entree> myEntrees = new ArrayList<Entree>();
		
		/* the following reads in the entrees from the appropriate file, splitting the lines
			into the various parameters based on the characters signaling the split, before
			adding a new entree to the ArrayList with all the parameters; exceptions are
			also handled */
		try {
            FileReader fr = new FileReader(fileName); 
            BufferedReader br = new BufferedReader(fr); 
            String line = "";
            line = br.readLine();
            do{
            	String[] name = line.split("@@");
            	myEntrees.add(new Entree(name[0], name[1], Integer.parseInt(name[2])));
            }while ((line = br.readLine()) != null);
            br.close();
            fr.close();
		} catch (IOException e) {
            System.out.println("File error!");
		}
		
		return myEntrees;
	}
	
	public static ArrayList<Side> readSides(String fileName) {
		/** Method readSides(String)
		 * @param fileName a String
		 * @return an ArrayList of sides read from a file
		 */
		
		// create a new ArrayList of sides
		ArrayList<Side> mySides = new ArrayList<Side>();
		
		/* the following reads in the sides from the appropriate file, splitting the lines
			into the various parameters based on the characters signaling the split, before
			adding a new side to the ArrayList with all the parameters; exceptions are
			also handled */
		try {
            FileReader fr = new FileReader(fileName); 
            BufferedReader br = new BufferedReader(fr); 
            String line = "";
            line = br.readLine();
            do{
            	String[] name = line.split("@@");
            	mySides.add(new Side(name[0], name[1], Integer.parseInt(name[2])));
            }while ((line = br.readLine()) != null);
            br.close();
            fr.close();
		} catch (IOException e) {
            System.out.println("File error!");
		}
		
		return mySides;
	}
	
	public static ArrayList<Salad> readSalads(String fileName) {
		/** Method readSalads(String)
		 * @param fileName a String
		 * @return an ArrayList of salads read from a file
		 */
		
		// create a new ArrayList of salads
		ArrayList<Salad> mySalads = new ArrayList<Salad>();
		
		/* the following reads in the salads from the appropriate file, splitting the lines
			into the various parameters based on the characters signaling the split, before
			adding a new salad to the ArrayList with all the parameters; exceptions are
			also handled */
		try {
            FileReader fr = new FileReader(fileName); 
            BufferedReader br = new BufferedReader(fr); 
            String line = "";
            line = br.readLine();
            do{
            	String[] name = line.split("@@");
            	mySalads.add(new Salad(name[0], name[1], Integer.parseInt(name[2])));
            }while ((line = br.readLine()) != null);
            br.close();
            fr.close();
		} catch (IOException e) {
            System.out.println("File error!");
		}
		
		return mySalads;
	}
	
	public static ArrayList<Dessert> readDesserts(String fileName) {
		/** Method readDesserts(String)
		 * @param fileName a String
		 * @return an ArrayList of desserts read from a file
		 */
		
		// create a new ArrayList of desserts
		ArrayList<Dessert> myDesserts = new ArrayList<Dessert>();
		
		/* the following reads in the desserts from the appropriate file, splitting the lines
			into the various parameters based on the characters signaling the split, before
			adding a new desserts to the ArrayList with all the parameters; exceptions are
			also handled */
		try {
            FileReader fr = new FileReader(fileName); 
            BufferedReader br = new BufferedReader(fr); 
            String line = "";
            line = br.readLine();
            do{
            	String[] name = line.split("@@");
            	myDesserts.add(new Dessert(name[0], name[1], Integer.parseInt(name[2])));
            }while ((line = br.readLine()) != null);
            br.close();
            fr.close();
		} catch (IOException e) {
            System.out.println("File error!");
		}
		
		return myDesserts;
	}
}
