package dev.oscarreyes.bakingrecipes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.entity.Step;

public class RecipeDetailActivity extends AppCompatActivity {

	private Step step;

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
	}

	private void loadBundle() {
		Bundle bundle = this.getIntent().getExtras();

		this.step = bundle.getParcelable(RecipeInstructionsActivity.BUNDLE_RECIPE_STEP);
	}

	private void loadViews() {

	}
}
