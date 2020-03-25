package dev.oscarreyes.bakingrecipes.api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
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
import dev.oscarreyes.bakingrecipes.entity.Recipe;

public class BakingAPI {
	private static final String TAG = BakingAPI.class.getSimpleName();

	/**
	 * Reads the mock api file from raw resources
	 * @param context The context where to get the resource instance
	 */
	public static void getRecipesMock(final Context context) {
		new AsyncTask<Integer, Void, List<Recipe>>() {
			@Override
			protected List<Recipe> doInBackground(Integer... resources) {
				final int resource = resources[0];
				final Gson gson = new Gson();
				final String jsonString = readRawResource(context, resource);
				final Type listType = new TypeToken<List<Recipe>>(){}.getType();

				return gson.fromJson(jsonString, listType);
			}

			@Override
			protected void onPostExecute(List<Recipe> recipes) {
				super.onPostExecute(recipes);
			}
		}.execute(R.raw.baking);
	}

	/**
	 * Reads a raw resource file String
	 * @param context The context where to get the resource instance
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
}
