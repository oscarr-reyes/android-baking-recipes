package dev.oscarreyes.bakingrecipes.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import dev.oscarreyes.bakingrecipes.BuildConfig;
import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.api.BakingAPI;
import dev.oscarreyes.bakingrecipes.entity.Ingredient;
import dev.oscarreyes.bakingrecipes.entity.Step;
import dev.oscarreyes.bakingrecipes.fragment.InstructionsFragment;
import dev.oscarreyes.bakingrecipes.util.AdapterClickListener;

public class RecipeInstructionsActivity extends AppCompatActivity implements AdapterClickListener {
	public static final String BUNDLE_RECIPE_STEP = "recipe-step";

	private ActionBar actionBar;

	private String recipeName;
	private int recipeIndex;
	private List<Step> steps;
	private List<Ingredient> ingredients;
	private boolean starred;
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_instructions);

		this.loadBundle();
		this.loadViews();

		this.preferences = this.getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
		this.starred = this.preferences.getInt(this.getString(R.string.pref_key_recipe_index), -1) != -1;
	}

	@Override
	protected void onStart() {
		super.onStart();

		this.setToolbarInfo();
		this.fetchData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = this.getMenuInflater();

		inflater.inflate(R.menu.menu_recipe, menu);

		if (this.starred) {
			MenuItem item = menu.findItem(R.id.menu_recipe_favorite);

			item.setIcon(R.drawable.ic_star_black_18dp);
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		final int itemId = item.getItemId();

		if (itemId == R.id.menu_recipe_favorite) {
			this.toggleStar(item);
		}

		return true;
	}

	@Override
	public void onItemClick(int position) {
		final Step step = this.steps.get(position);
		final Intent intent = new Intent(this, RecipeDetailActivity.class);

		intent.putExtra(BUNDLE_RECIPE_STEP, step);

		this.startActivity(intent);
	}

	private void loadBundle() {
		Bundle bundle = this.getIntent().getExtras();

		this.recipeName = bundle.getString(RecipeListActivity.BUNDLE_RECIPE_NAME);
		this.recipeIndex = bundle.getInt(RecipeListActivity.BUNDLE_RECIPE_INDEX);
	}

	private void loadViews() {
		this.actionBar = this.getSupportActionBar();
	}

	private void loadFragments() {
		InstructionsFragment instructionsFragment = new InstructionsFragment(this.ingredients, this.steps);
		FragmentManager fragmentManager = this.getSupportFragmentManager();

		fragmentManager.beginTransaction()
			.add(R.id.instructions_container, instructionsFragment)
			.commit();
	}

	private void setToolbarInfo() {
		this.actionBar.setTitle(this.recipeName);
	}

	private void toggleStar(MenuItem item) {
		final String keyRecipeIndex = this.getString(R.string.pref_key_recipe_index);
		final String keyRecipeName = this.getString(R.string.pref_key_recipe_name);
		final String keyRecipeContent = this.getString(R.string.pref_key_recipe_content);

		SharedPreferences.Editor editor = this.preferences.edit();
		int resourceIcon;

		if (this.starred) {
			editor
				.remove(keyRecipeIndex)
				.remove(keyRecipeName)
				.remove(keyRecipeContent);

			resourceIcon = R.drawable.ic_star_border_black_18dp;

			this.starred = false;
		} else {
			editor
				.putInt(keyRecipeIndex, this.recipeIndex)
				.putString(keyRecipeName, this.recipeName)
				.putString(keyRecipeContent, "Random content");

			resourceIcon = R.drawable.ic_star_black_18dp;

			this.starred = true;
		}

		editor.apply();
		item.setIcon(resourceIcon);
	}

	private void fetchData() {
		BakingAPI.getStepsByIndexMock(this, this.recipeIndex, this::loadSteps);
		BakingAPI.getIngredientsByIndexMock(this, this.recipeIndex, this::loadIngredients);
	}

	private void loadSteps(List<Step> steps) {
		this.steps = steps;

		if (this.ingredients != null) {
			this.loadFragments();
		}
	}

	private void loadIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;

		if (this.steps != null) {
			this.loadFragments();
		}
	}
}
