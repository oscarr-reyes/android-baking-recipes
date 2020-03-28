package dev.oscarreyes.bakingrecipes.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class InstructionsPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragments = new ArrayList<>();

	public InstructionsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
		super(fm, behavior);
	}

	public void addFragment(Fragment fragment) {
		this.fragments.add(fragment);
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}
}
