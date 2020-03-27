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
import dev.oscarreyes.bakingrecipes.adapter.StepAdapter;
import dev.oscarreyes.bakingrecipes.api.BakingAPI;
import dev.oscarreyes.bakingrecipes.entity.Step;

public class InstructionsFragment extends Fragment {

	private RecyclerView stepsRecycler;

	private int recipeIndex;

	public InstructionsFragment(int recipeIndex) {
		this.recipeIndex = recipeIndex;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_instructions, container, false);

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
		StepAdapter stepAdapter = new StepAdapter(steps);

		this.stepsRecycler.setAdapter(stepAdapter);
	}

	private void fetchSteps() {
		BakingAPI.getStepsByIndexMock(this.getContext(), this.recipeIndex, this::loadAdapter);
	}

	private void setupRecyclerLayout() {
		Context context = this.getContext();
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);

		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		this.stepsRecycler.setLayoutManager(linearLayoutManager);
		this.stepsRecycler.setHasFixedSize(true);
		this.stepsRecycler.addItemDecoration(dividerItemDecoration);
	}
}
