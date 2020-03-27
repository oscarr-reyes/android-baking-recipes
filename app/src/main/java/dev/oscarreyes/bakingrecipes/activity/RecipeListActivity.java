package dev.oscarreyes.bakingrecipes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.adapter.RecipeAdapter;
import dev.oscarreyes.bakingrecipes.api.BakingAPI;
import dev.oscarreyes.bakingrecipes.entity.Recipe;

public class RecipeListActivity extends AppCompatActivity {
	private static final String TAG = RecipeListActivity.class.getSimpleName();
	private static final String[] PERMISSIONS = new String[]{Manifest.permission.INTERNET};
	private static final int PERMISSION_CODE = 174;

	public static final String BUNDLE_RECIPE_INDEX = "recipe-index";
	public static final String BUNDLE_RECIPE_NAME = "recipe-name";

	RecyclerView recipesRecycler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_list);

		this.loadViews();
		this.setupLayout();
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
		RecipeAdapter recipeAdapter = new RecipeAdapter(recipes, index -> {
			final Recipe recipe = recipes.get(index);

			this.transitionToDetail(recipe);
		});

		this.recipesRecycler.setAdapter(recipeAdapter);
	}

	private void transitionToDetail(Recipe recipe) {
		final Intent intent = new Intent(this, RecipeDetailActivity.class);

		intent.putExtra(BUNDLE_RECIPE_INDEX, recipe.getId());
		intent.putExtra(BUNDLE_RECIPE_NAME, recipe.getName());

		this.startActivity(intent);
	}

	private void fetchRecipeCollection() {
		BakingAPI.getRecipesMock(this, this::loadAdapter);
	}
}
