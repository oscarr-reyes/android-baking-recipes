package dev.oscarreyes.bakingrecipes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.entity.Recipe;
import dev.oscarreyes.bakingrecipes.util.AdapterClickListener;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
	private static final String TAG = RecipeAdapter.class.getSimpleName();

	private final List<Recipe> recipes;
	private final AdapterClickListener clickListener;

	public RecipeAdapter(List<Recipe> recipes, AdapterClickListener clickListener) {
		this.recipes = recipes;
		this.clickListener = clickListener;
	}

	@NonNull
	@Override
	public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		Context context = parent.getContext();
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(R.layout.recipe_list_item, parent, false);

		return new RecipeViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
		final Recipe recipe = this.recipes.get(position);

		holder.bind(recipe);
	}

	@Override
	public int getItemCount() {
		return this.recipes.size();
	}

	class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private TextView name;
		private TextView servings;

		public RecipeViewHolder(@NonNull View itemView) {
			super(itemView);

			this.name = itemView.findViewById(R.id.recipe_item_name);
			this.servings = itemView.findViewById(R.id.recipe_item_servings_value);

			itemView.setOnClickListener(this);
		}

		void bind(Recipe recipe) {
			final String recipeName = recipe.getName();
			final int recipeServings = recipe.getServings();

			this.name.setText(recipeName);
			this.servings.setText(String.valueOf(recipeServings));
		}

		@Override
		public void onClick(View v) {
			final int position = this.getAdapterPosition();

			clickListener.onItemClick(position);
		}
	}
}

