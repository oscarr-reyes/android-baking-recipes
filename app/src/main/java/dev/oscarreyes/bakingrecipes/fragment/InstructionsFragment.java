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
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.adapter.InstructionsPagerAdapter;
import dev.oscarreyes.bakingrecipes.util.RecipeStepListener;

public class InstructionsFragment extends Fragment {
	private static final String TAG = InstructionsFragment.class.getSimpleName();

	private TabLayout instructionsTab;
	private ViewPager instructionsPager;

	private RecipeStepListener callback;
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
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);

		try {
			this.callback = (RecipeStepListener) context;
		} catch (ClassCastException e) {
			final String message = String.format("%s must implement RecipeStepListener", context.toString());

			throw new ClassCastException(message);
		}
	}

	@Override
	public void onStart() {
		super.onStart();

		this.loadPager();
	}

	private void loadViews(View view) {
		Log.i(TAG, "Loading views");

		this.instructionsTab = view.findViewById(R.id.recipe_instructions_tabs);
		this.instructionsPager = view.findViewById(R.id.recipe_instructions_pager);

		this.instructionsTab.setupWithViewPager(this.instructionsPager);
	}

	private void loadPager() {
		Log.i(TAG, "Loading pager");

		FragmentManager fragmentManager = this.getFragmentManager();
		IngredientsFragment ingredientsFragment = new IngredientsFragment(recipeIndex);
		StepsFragment stepsFragment = new StepsFragment(recipeIndex, step -> this.callback.onStepSelected(step));

		InstructionsPagerAdapter instructionsAdapter = new InstructionsPagerAdapter(fragmentManager, 0);
		instructionsAdapter.addFragment(ingredientsFragment, this.getString(R.string.recipe_detail_tab_ingredients_label));
		instructionsAdapter.addFragment(stepsFragment, this.getString(R.string.recipe_detail_tab_steps_label));

		this.instructionsPager.setAdapter(instructionsAdapter);
	}
}
