package com.thezoo;

public class Animal {
	
	private String animalName = null;
	private String animalSpecies = null;
	private String animalFood = null;
	private String animalColor = null;
	private int cageNumber = 0;
	private double weight = 0.0;
	private String imageLocation = null;
	
	public Animal() {
		super();
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public String getAnimalSpecies() {
		return animalSpecies;
	}

	public void setAnimalSpecies(String animalSpecies) {
		this.animalSpecies = animalSpecies;
	}

	public String getAnimalFood() {
		return animalFood;
	}

	public void setAnimalFood(String animalFood) {
		this.animalFood = animalFood;
	}

	public String getAnimalColor() {
		return animalColor;
	}

	public void setAnimalColor(String animalColor) {
		this.animalColor = animalColor;
	}

	public int getCageNumber() {
		return cageNumber;
	}

	public void setCageNumber(int cageNumber) {
		this.cageNumber = cageNumber;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	@Override
	public String toString() {
		return "Animal [animalName=" + animalName + ", animalSpecies=" + animalSpecies + ", animalFood=" + animalFood
				+ ", animalColor=" + animalColor + ", cageNumber=" + cageNumber + ", weight=" + weight
				+ ", imageLocation=" + imageLocation + "]";
	}


	
	

}
