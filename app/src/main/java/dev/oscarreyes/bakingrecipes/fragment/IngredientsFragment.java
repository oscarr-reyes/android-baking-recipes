package dev.oscarreyes.bakingrecipes.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.adapter.IngredientAdapter;
import dev.oscarreyes.bakingrecipes.api.BakingAPI;
import dev.oscarreyes.bakingrecipes.entity.Ingredient;

public class IngredientsFragment extends Fragment {
	private int recipeIndex;
	private RecyclerView ingredientsRecycler;

	public IngredientsFragment(int recipeIndex) {
		this.recipeIndex = recipeIndex;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);

		this.loadViews(rootView);

		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();

		this.fetchIngredients();
	}

	private void loadAdapter(List<Ingredient> ingredients) {
		IngredientAdapter ingredientAdapter = new IngredientAdapter(ingredients);

		this.ingredientsRecycler.setAdapter(ingredientAdapter);
	}

	private void loadViews(View view) {
		this.ingredientsRecycler = view.findViewById(R.id.rv_ingredients);

		this.setupRecyclerLayout();
	}

	private void fetchIngredients() {
		Context context = this.getContext();
		BakingAPI.getIngredientsByIndexMock(context, this.recipeIndex, this::loadAdapter);
	}

	private void setupRecyclerLayout() {
		Context context = this.getContext();
		LinearLayoutManager layoutManager = new LinearLayoutManager(context);
		DividerItemDecoration dividerDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);

		layoutManager.setOrientation(RecyclerView.VERTICAL);

		this.ingredientsRecycler.setLayoutManager(layoutManager);
		this.ingredientsRecycler.setHasFixedSize(true);
		this.ingredientsRecycler.addItemDecoration(dividerDecoration);
	}
}
