package dev.oscarreyes.bakingrecipes.api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.test.espresso.IdlingResource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.entity.Ingredient;
import dev.oscarreyes.bakingrecipes.entity.Recipe;
import dev.oscarreyes.bakingrecipes.entity.Step;
import dev.oscarreyes.bakingrecipes.util.DataIdlingResource;

public class BakingAPI {
	private static final String TAG = BakingAPI.class.getSimpleName();

	/**
	 * Reads the mock api file from raw resources
	 *
	 * @param context The context where to get the resource instance
	 */
	public static void getRecipesMock(final Context context, final RecipesResult recipesResult, @Nullable final DataIdlingResource idlingResource) {
		new AsyncTask<Integer, Void, List<Recipe>>() {
			@Override
			protected void onPreExecute() {
				if (idlingResource != null) {
					idlingResource.setIdleState(false);
				}
			}

			@Override
			protected List<Recipe> doInBackground(Integer... resources) {
				final int resource = resources[0];
				final Gson gson = new Gson();
				final String jsonString = readRawResource(context, resource);
				final Type listType = new TypeToken<List<Recipe>>() {
				}.getType();

				return gson.fromJson(jsonString, listType);
			}

			@Override
			protected void onPostExecute(List<Recipe> recipes) {
				recipesResult.result(recipes);

				if (idlingResource != null) {
					idlingResource.setIdleState(true);
				}
			}
		}.execute(R.raw.baking);
	}

	/**
	 * Reads the mock api file from raw resources and gets the ingredients of the selected recipe by index
	 *
	 * @param context           The context where to get the resource instance
	 * @param index             The index where to extract the ingredients
	 * @param ingredientsResult The listener for returning the result
	 */
	public static void getIngredientsByIndexMock(final Context context, final int index, final IngredientsResult ingredientsResult) {
		final Gson gson = new Gson();

		new AsyncTask<Integer, Void, JsonArray>() {
			@Override
			protected JsonArray doInBackground(Integer... resources) {
				final int resource = resources[0];
				final String jsonString = readRawResource(context, resource);
				final JsonArray jsonRecipes = gson.fromJson(jsonString, JsonArray.class);

				final JsonObject jsonRecipe = jsonRecipes.get(index)
					.getAsJsonObject();

				return jsonRecipe.get("ingredients")
					.getAsJsonArray();
			}

			@Override
			protected void onPostExecute(JsonArray jsonIngredients) {
				final Type listType = new TypeToken<List<Ingredient>>() {
				}.getType();

				final List<Ingredient> ingredients = gson.fromJson(jsonIngredients, listType);

				ingredientsResult.result(ingredients);
			}
		}.execute(R.raw.baking);
	}

	/**
	 * Reads the mock api file from raw resources and gets the steps of the selected recipe by index
	 *
	 * @param context     The context where to get the resource instance
	 * @param index       The index where to extract the steps
	 * @param stepsResult The listener for returning the result
	 */
	public static void getStepsByIndexMock(final Context context, final int index, final StepsResult stepsResult) {
		final Gson gson = new Gson();

		new AsyncTask<Integer, Void, JsonArray>() {
			@Override
			protected JsonArray doInBackground(Integer... resources) {
				final int resource = resources[0];
				final String jsonString = readRawResource(context, resource);
				final JsonArray jsonRecipes = gson.fromJson(jsonString, JsonArray.class);

				final JsonObject jsonRecipe = jsonRecipes.get(index)
					.getAsJsonObject();

				return jsonRecipe.get("steps")
					.getAsJsonArray();
			}

			@Override
			protected void onPostExecute(JsonArray jsonSteps) {
				final Type listType = new TypeToken<List<Step>>() {
				}.getType();

				final List<Step> steps = gson.fromJson(jsonSteps, listType);

				stepsResult.result(steps);
			}
		}.execute(R.raw.baking);
	}

	/**
	 * Reads a raw resource file String
	 *
	 * @param context  The context where to get the resource instance
	 * @param resource The id resource of the raw file
	 * @return The content of the file as string
	 */
	private static String readRawResource(Context context, int resource) {
		final InputStream input = context.getResources().openRawResource(resource);

		BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
		Writer writer = new StringWriter();

		try {
			String line;

			while ((line = buffer.readLine()) != null) {
				writer.write(line);
			}

			buffer.close();
			input.close();
		} catch (IOException e) {
			Log.e(TAG, String.format("Unable to read raw file. Reason: %s", e.getMessage()));
		}

		return writer.toString();
	}

	public interface RecipesResult {
		void result(List<Recipe> recipes);
	}

	public interface IngredientsResult {
		void result(List<Ingredient> ingredients);
	}

	public interface StepsResult {
		void result(List<Step> steps);
	}
}
