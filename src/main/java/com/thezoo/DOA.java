package com.thezoo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DOA {

	static Scanner sc = new Scanner(System.in);

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=rootautoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "root";

	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;

	public static void connToDB() {
		try {

			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to the Database");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to the database");
		} catch (SQLException | ClassNotFoundException e) {
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

				ourAnimal.setCageNumber(RES_SET.getInt("animal_id"));
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

	public static void writeToDB(Animal addToDB) {

		Animal animalToAdd = new Animal();

		animalToAdd = addToDB;

		connToDB();

		try {
			PREP_STMT = CONN.prepareStatement(insertToDB);

			PREP_STMT.setString(1, animalToAdd.getAnimalName());
			PREP_STMT.setString(2, animalToAdd.getAnimalSpecies());
			PREP_STMT.setString(5, animalToAdd.getAnimalColor());
			PREP_STMT.setString(4, animalToAdd.getAnimalFood());
			PREP_STMT.setString(6, animalToAdd.getImageLocation());
			PREP_STMT.setDouble(3, animalToAdd.getWeight());

			PREP_STMT.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private static String insertToDB = "INSERT INTO `the_zoo`.`animal`"
			+ "(animal_name, animal_species, weight, animal_food, animal_color, image_location)" + " VALUES "
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

	public static void updateDB() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the ID number of the animal you would like to update.");
		int updateAnimalId = Integer.parseInt(sc.nextLine());

		Animal animalToUpdate = new Animal();
		animalToUpdate = aboutTheAnimal();

		connToDB();
		try {
			PREP_STMT = CONN.prepareStatement(updateDB);

			PREP_STMT.setString(1, animalToUpdate.getAnimalName());
			PREP_STMT.setString(2, animalToUpdate.getAnimalSpecies());
			PREP_STMT.setString(3, animalToUpdate.getAnimalColor());
			PREP_STMT.setString(4, animalToUpdate.getAnimalFood());
			PREP_STMT.setDouble(5, animalToUpdate.getWeight());
			PREP_STMT.setString(6, animalToUpdate.getImageLocation());
			PREP_STMT.setInt(7, updateAnimalId);
			PREP_STMT.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		sc.close();
	}

	private static String updateDB = ("UPDATE `the_zoo`.`animal` SET `animal_name` = ?,    `animal_species` = ?, `animal_color` = ?, `animal_food` = ?, `weight` = ?, `image_location` = ?,WHERE `animal_id` = ?");

	public static void deleteFromDatabase() {
		Scanner sc = new Scanner(System.in);
		connToDB();

		System.out.println("What is the product ID you would like to delete?\n");
		String deleteID = sc.nextLine();

		try {
			PREP_STMT = CONN.prepareStatement("DELETE FROM our_schema.zoo WHERE cage_number = ?");
			PREP_STMT.setString(1, deleteID);
			PREP_STMT.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sc.close();

	}

}
