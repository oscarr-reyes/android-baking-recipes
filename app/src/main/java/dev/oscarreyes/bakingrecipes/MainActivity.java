package dev.oscarreyes.bakingrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;

import java.util.List;

import dev.oscarreyes.bakingrecipes.adapter.RecipeAdapter;
import dev.oscarreyes.bakingrecipes.api.BakingAPI;
import dev.oscarreyes.bakingrecipes.entity.Recipe;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String[] PERMISSIONS = new String[]{Manifest.permission.INTERNET};
	private static final int PERMISSION_CODE = 174;

	RecyclerView recipesRecycler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
		RecipeAdapter recipeAdapter = new RecipeAdapter(recipes);

		this.recipesRecycler.setAdapter(recipeAdapter);
	}

	private void fetchRecipeCollection() {
		BakingAPI.getRecipesMock(this, this::loadAdapter);
	}
}
