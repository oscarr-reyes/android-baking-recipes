package dev.oscarreyes.bakingrecipes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.entity.Step;
import dev.oscarreyes.bakingrecipes.fragment.StepDetailFragment;

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
		this.loadFragments();
	}

	private void loadFragments() {
		StepDetailFragment detailFragment = new StepDetailFragment(this.step);
		FragmentManager fragmentManager = this.getSupportFragmentManager();

		fragmentManager.beginTransaction()
			.add(R.id.step_container, detailFragment)
			.commit();
	}
}
