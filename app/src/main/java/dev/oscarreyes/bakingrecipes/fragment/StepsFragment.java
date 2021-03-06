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
import dev.oscarreyes.bakingrecipes.adapter.StepAdapter;
import dev.oscarreyes.bakingrecipes.entity.Step;
import dev.oscarreyes.bakingrecipes.util.AdapterClickListener;

public class StepsFragment extends Fragment {
	private static final String TAG = StepsFragment.class.getSimpleName();

	private List<Step> steps;

	private RecyclerView stepsRecycler;
	private AdapterClickListener clickListener;

	public StepsFragment(List<Step> steps, AdapterClickListener clickListener) {
		this.steps = steps;
		this.clickListener = clickListener;
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

		this.loadAdapter();
	}

	private void loadViews(View view) {
		this.stepsRecycler = view.findViewById(R.id.rv_steps);

		this.setupRecyclerLayout();
	}

	private void loadAdapter() {
		Log.i(TAG, String.format("Showing %d steps", this.steps.size()));

		final StepAdapter stepAdapter = new StepAdapter(
			this.steps,
			this.clickListener
		);

		this.stepsRecycler.setAdapter(stepAdapter);
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
