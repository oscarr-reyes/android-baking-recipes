package dev.oscarreyes.bakingrecipes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.adapter.RecipeAdapter;
import dev.oscarreyes.bakingrecipes.api.BakingAPI;
import dev.oscarreyes.bakingrecipes.entity.Recipe;
import dev.oscarreyes.bakingrecipes.util.DataIdlingResource;

public class RecipeListActivity extends AppCompatActivity {
	private static final String TAG = RecipeListActivity.class.getSimpleName();
	private static final String[] PERMISSIONS = new String[]{Manifest.permission.INTERNET};
	private static final int PERMISSION_CODE = 174;

	public static final String BUNDLE_RECIPE_INDEX = "recipe-index";
	public static final String BUNDLE_RECIPE_NAME = "recipe-name";

	RecyclerView recipesRecycler;

	private DataIdlingResource idlingResource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_list);

		this.loadViews();
		this.setupLayout();
		this.requestPermissions(PERMISSIONS, PERMISSION_CODE);
	}

	@Override
	protected void onStart() {
		super.onStart();
		fetchRecipeCollection();
	}

	private void loadViews() {
		this.recipesRecycler = this.findViewById(R.id.rv_recipes);
	}

	private void setupLayout() {
		GridLayoutManager layoutManager = new GridLayoutManager(this, 1);

		layoutManager.setOrientation(RecyclerView.VERTICAL);

		this.recipesRecycler.setLayoutManager(layoutManager);
		this.recipesRecycler.setHasFixedSize(true);
	}

	private void loadAdapter(List<Recipe> recipes) {
		RecipeAdapter recipeAdapter = new RecipeAdapter(recipes, position -> {
			final Recipe recipe = recipes.get(position);

			this.transitionToDetail(position, recipe.getName());
		});

		this.recipesRecycler.setAdapter(recipeAdapter);
	}

	private void transitionToDetail(int index, String recipeName) {
		final Intent intent = new Intent(this, RecipeInstructionsActivity.class);

		intent.putExtra(BUNDLE_RECIPE_INDEX, index);
		intent.putExtra(BUNDLE_RECIPE_NAME, recipeName);

		this.startActivity(intent);
	}

	private void fetchRecipeCollection() {
		BakingAPI.getRecipesMock(this, this::loadAdapter, this.idlingResource);
	}

	public IdlingResource getIdlingResource() {
		if (this.idlingResource == null) {
			this.idlingResource = new DataIdlingResource();
		}

		return this.idlingResource;
	}
}
