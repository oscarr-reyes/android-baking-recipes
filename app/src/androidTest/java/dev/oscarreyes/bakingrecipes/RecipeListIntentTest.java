package dev.oscarreyes.bakingrecipes;


import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dev.oscarreyes.bakingrecipes.activity.RecipeInstructionsActivity;
import dev.oscarreyes.bakingrecipes.activity.RecipeListActivity;

@RunWith(AndroidJUnit4.class)
public class RecipeListIntentTest {
	@Rule
	public IntentsTestRule<RecipeListActivity> intentsTestRule = new IntentsTestRule<>(RecipeListActivity.class);

	private IdlingResource idlingResource;

	@Before
	public void registerIdlingResource() {
		ActivityScenario activityScenario = ActivityScenario.launch(RecipeListActivity.class);

		activityScenario.onActivity(activity -> {
			RecipeListActivity recipeListActivity = (RecipeListActivity) activity;

			this.idlingResource = recipeListActivity.getIdlingResource();

			IdlingRegistry.getInstance()
				.register(this.idlingResource);
		});
	}

	@After
	public void unregisterIdlingResource() {
		if (this.idlingResource != null) {
			IdlingRegistry.getInstance().unregister(this.idlingResource);
		}
	}

	@Test
	public void clickRecipe_startRecipeInstructionsActivity() {
		ViewInteraction viewInteraction = Espresso.onView(ViewMatchers.withId(R.id.rv_recipes));

		viewInteraction.perform(
			RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click())
		);

		Intents.intended(IntentMatchers.hasComponent(RecipeInstructionsActivity.class.getName()));
	}
}
