package dev.oscarreyes.bakingrecipes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.oscarreyes.bakingrecipes.R;

public class InstructionsFragment extends Fragment {
	public InstructionsFragment() {
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// return super.onCreateView(inflater, container, savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_instructions, container, false);

		this.loadViews(rootView);

		return rootView;
	}

	private void loadViews(View view) {

	}
}
