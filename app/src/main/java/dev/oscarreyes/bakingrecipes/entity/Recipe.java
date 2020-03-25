package dev.oscarreyes.bakingrecipes.entity;

public class Recipe {
	private int id;
	private String name;
	private Object ingredients;
	private Object steps;
	private int servings;
	private String image;

	public Recipe() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getIngredients() {
		return ingredients;
	}

	public void setIngredients(Object ingredients) {
		this.ingredients = ingredients;
	}

	public Object getSteps() {
		return steps;
	}

	public void setSteps(Object steps) {
		this.steps = steps;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
