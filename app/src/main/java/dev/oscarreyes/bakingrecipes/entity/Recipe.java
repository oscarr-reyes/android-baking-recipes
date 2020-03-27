package dev.oscarreyes.bakingrecipes.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {
	private int id;
	private String name;
	private int servings;
	private String image;

	public Recipe() {
	}

	protected Recipe(Parcel in) {
		id = in.readInt();
		name = in.readString();
		servings = in.readInt();
		image = in.readString();
	}

	public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
		@Override
		public Recipe createFromParcel(Parcel in) {
			return new Recipe(in);
		}

		@Override
		public Recipe[] newArray(int size) {
			return new Recipe[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeInt(servings);
		dest.writeString(image);
	}
}
