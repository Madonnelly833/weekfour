package com.thezoo;

import java.util.Scanner;

public class TheZoo {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String userMenuInput = null;
		boolean menuCorrect = false;

		System.out.println("Welcome to The Zoo program");

		do {
			System.out.println("Press 1 to read the DB" + "\nPress 2 to add to the database"
					+ "\nPress 3 to delete from the database");
			userMenuInput = sc.nextLine();
			switch (userMenuInput) {
			case "1":
				DOA.readFromDB();
				break;
			case "2":
//				DOA.writeToDB();
				break;
			case "3":
				DOA.deleteFromDatabase();
				break;

			default:
				System.out.println("You've entered an invalid option");
				menuCorrect = true;
				break;
			}

		} while (menuCorrect);

		sc.close();
	}

}
