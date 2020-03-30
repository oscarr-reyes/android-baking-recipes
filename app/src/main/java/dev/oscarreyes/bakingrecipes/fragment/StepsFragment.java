package dev.oscarreyes.bakingrecipes.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.adapter.StepAdapter;
import dev.oscarreyes.bakingrecipes.api.BakingAPI;
import dev.oscarreyes.bakingrecipes.entity.Step;
import dev.oscarreyes.bakingrecipes.util.AdapterClickListener;
import dev.oscarreyes.bakingrecipes.util.RecipeStepListener;

public class StepsFragment extends Fragment {
	private static final String TAG = StepsFragment.class.getSimpleName();

	private int recipeIndex;

	private RecyclerView stepsRecycler;
	private RecipeStepListener recipeStepListener;

	public StepsFragment(int recipeIndex, RecipeStepListener recipeStepListener) {
		this.recipeIndex = recipeIndex;
		this.recipeStepListener = recipeStepListener;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

		this.loadViews(rootView);

		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();

		this.fetchSteps();
	}

	private void loadViews(View view) {
		this.stepsRecycler = view.findViewById(R.id.rv_steps);

		this.setupRecyclerLayout();
	}

	private void loadAdapter(List<Step> steps) {
		Log.i(TAG, String.format("Showing %d steps", steps.size()));

		final StepAdapter stepAdapter = new StepAdapter(
			steps,
			position -> this.recipeStepListener.onStepSelected(steps.get(position))
		);

		this.stepsRecycler.setAdapter(stepAdapter);
	}

	private void fetchSteps() {
		final Context context = this.getContext();

		BakingAPI.getStepsByIndexMock(context, this.recipeIndex, this::loadAdapter);
	}

	private void setupRecyclerLayout() {
		final Context context = this.getContext();
		final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
		final DividerItemDecoration dividerDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);

		layoutManager.setOrientation(RecyclerView.VERTICAL);

		this.stepsRecycler.setLayoutManager(layoutManager);
		this.stepsRecycler.setHasFixedSize(true);
		this.stepsRecycler.addItemDecoration(dividerDecoration);
	}
}
