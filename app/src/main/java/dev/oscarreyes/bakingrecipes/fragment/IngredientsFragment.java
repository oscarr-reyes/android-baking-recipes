package dev.oscarreyes.bakingrecipes.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import dev.oscarreyes.bakingrecipes.entity.Ingredient;

public class IngredientsFragment extends Fragment {
	private static final String TAG = IngredientsFragment.class.getSimpleName();

	private RecyclerView ingredientsRecycler;

	private List<Ingredient> ingredients;

	public IngredientsFragment(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
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

		this.loadAdapter();
	}

	private void loadAdapter() {
		Log.i(TAG, String.format("Showing %d ingredients", this.ingredients.size()));

		IngredientAdapter ingredientAdapter = new IngredientAdapter(this.ingredients);

		this.ingredientsRecycler.setAdapter(ingredientAdapter);
	}

	private void loadViews(View view) {
		this.ingredientsRecycler = view.findViewById(R.id.rv_ingredients);

		this.setupRecyclerLayout();
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
