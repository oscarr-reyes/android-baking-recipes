package dev.oscarreyes.bakingrecipes.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.fragment.InstructionsFragment;

public class RecipeDetailActivity extends AppCompatActivity {

	private ActionBar actionBar;

	private String recipeName;
	private int recipeIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_detail);

		this.loadBundle();
		this.loadViews();
	}

	@Override
	protected void onStart() {
		super.onStart();

		this.setToolbarInfo();
	}

	private void loadBundle() {
		Bundle bundle = this.getIntent().getExtras();

		this.recipeName = bundle.getString(RecipeListActivity.BUNDLE_RECIPE_NAME);
		this.recipeIndex = bundle.getInt(RecipeListActivity.BUNDLE_RECIPE_INDEX);
	}

	private void loadViews() {
		this.actionBar = this.getSupportActionBar();

		this.loadFragments();
	}

	private void loadFragments() {
		InstructionsFragment instructionsFragment = new InstructionsFragment(this.recipeIndex);
		FragmentManager fragmentManager = this.getSupportFragmentManager();

		fragmentManager.beginTransaction()
			.add(R.id.instructions_container, instructionsFragment)
			.commit();
	}

	private void setToolbarInfo() {
		this.actionBar.setTitle(this.recipeName);
	}
}
