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
import dev.oscarreyes.bakingrecipes.entity.Ingredient;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
	private List<Ingredient> ingredients;

	public IngredientAdapter(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@NonNull
	@Override
	public IngredientAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		Context context = parent.getContext();
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(R.layout.ingredient_list_item, parent, false);

		return new IngredientViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull IngredientAdapter.IngredientViewHolder holder, int position) {
		Ingredient ingredient = this.ingredients.get(position);

		holder.bind(ingredient);
	}

	@Override
	public int getItemCount() {
		return this.ingredients.size();
	}

	class IngredientViewHolder extends RecyclerView.ViewHolder {
		private TextView textQuantity;
		private TextView textMeasure;
		private TextView textIngredient;

		public IngredientViewHolder(@NonNull View itemView) {
			super(itemView);

			this.loadViews(itemView);
		}

		private void loadViews(View view) {
			this.textQuantity = view.findViewById(R.id.ingredient_item_quantity);
			this.textMeasure = view.findViewById(R.id.ingredient_item_measure);
			this.textIngredient = view.findViewById(R.id.ingredient_item_ingredient);
		}

		void bind(Ingredient ingredient) {
			final String quantity = String.valueOf(ingredient.getQuantity());
			final String measure = ingredient.getMeasure();
			final String ingredientText = ingredient.getIngredient();

			this.textQuantity.setText(quantity);
			this.textMeasure.setText(measure);
			this.textIngredient.setText(ingredientText);
		}
	}
}
