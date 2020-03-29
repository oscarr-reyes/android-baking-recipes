package dev.oscarreyes.bakingrecipes.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class InstructionsPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragments = new ArrayList<>();
	private List<String> pageTitles = new ArrayList<>();

	public InstructionsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
		super(fm, behavior);
	}

	public void addFragment(Fragment fragment) {
		this.fragments.add(fragment);
	}

	public void addFragment(Fragment fragment, String tabTitle) {
		this.fragments.add(fragment);
		this.pageTitles.add(tabTitle);
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

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return this.pageTitles.get(position);
	}
}
