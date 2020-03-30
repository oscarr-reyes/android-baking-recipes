package dev.oscarreyes.bakingrecipes.util;

import dev.oscarreyes.bakingrecipes.entity.Step;

public interface RecipeStepListener {
	void onStepSelected(Step step);
}
