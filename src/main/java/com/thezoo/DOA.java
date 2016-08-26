package com.thezoo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DOA {
	
	static Scanner sc = new Scanner(System.in);
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=rootautoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "root";

	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;

	public static void connToDB() {
		try {
			System.out.println("Connecting to the Database");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to the database");
		} catch (SQLException e) {
			System.out.println("Database connection failed.");
			e.printStackTrace();
		}

	}

	public static void readFromDB() {

		try {
			ArrayList<Animal> ourZoo = new ArrayList<>();

			connToDB();

			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM `the_zoo`.`animal`");
			
			while (RES_SET.next()) {
				Animal ourAnimal = new Animal();
				ourAnimal.setAnimalName(RES_SET.getString("animal_name"));
				ourAnimal.setAnimalSpecies(RES_SET.getString("animal_species"));
				ourAnimal.setAnimalFood(RES_SET.getString("animal_food"));
				ourAnimal.setAnimalColor(RES_SET.getString("animal_color"));
				ourAnimal.setWeight(RES_SET.getDouble("weight"));
				ourAnimal.setCageNumber(RES_SET.getInt("cage_number"));
				ourAnimal.setImageLocation(RES_SET.getString("image_location"));
				
				ourZoo.add(ourAnimal);

			}

			for (Animal ourAnimal : ourZoo) {
				System.out.println(ourAnimal.toString());
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	
	public static void writeToDB() {

		Animal animalToAdd = new Animal();

		animalToAdd = aboutTheAnimal();
		
		connToDB();
		
		try {
			PREP_STMT =CONN.prepareStatement(insertToDB);
			
			PREP_STMT.setString(1, animalToAdd.getAnimalName());
			PREP_STMT.setString(2, animalToAdd.getAnimalSpecies());
			PREP_STMT.setString(3, animalToAdd.getAnimalColor());
			PREP_STMT.setString(4, animalToAdd.getAnimalFood());
			PREP_STMT.setString(5, animalToAdd.getImageLocation());
			PREP_STMT.setDouble(6, animalToAdd.getWeight());
			
			PREP_STMT.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}
	
	private static String insertToDB = "INSERT INTO `the_zoo`.`animal`"
			+ "(animal_name, animal_species, animal_food, animal_color, image_location, weight)"
			+ " VALUES "
			+ "(?, ?, ?, ?, ?, ?)";

	private static Animal aboutTheAnimal() {

		Animal animalToAdd = new Animal();

		System.out.println("What is the animal's name?");
		animalToAdd.setAnimalName(sc.nextLine());

		System.out.println("What is the species of the animal?");
		animalToAdd.setAnimalSpecies(sc.nextLine());

		System.out.println("What is the animal's food?");
		animalToAdd.setAnimalFood(sc.nextLine());
		
		System.out.println("What is the color of the animal?");
		animalToAdd.setAnimalColor(sc.nextLine());
		
		System.out.println("What is the animal's weight?");
		animalToAdd.setWeight(Double.parseDouble(sc.nextLine()));

		System.out.println("What is the image URL of the animal?");
		animalToAdd.setImageLocation(sc.nextLine());

		


		return animalToAdd;

	}
	
	public static void deleteFromDatabase() {
        Scanner sc = new Scanner(System.in);
        connToDB();
        
        System.out.println("What is the product ID you would like to delete?\n");
        String deleteID = sc.nextLine();
        
        try {
            PREP_STMT=CONN.prepareStatement("DELETE FROM the_zoo.animal WHERE cage_number = ?");
            PREP_STMT.setString(1, deleteID);
            PREP_STMT.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sc.close();
        
    }

}
