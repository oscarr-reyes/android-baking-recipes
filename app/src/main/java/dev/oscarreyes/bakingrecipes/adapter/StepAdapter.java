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
import dev.oscarreyes.bakingrecipes.entity.Step;
import dev.oscarreyes.bakingrecipes.util.AdapterClickListener;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {
	private static final String TAG = StepAdapter.class.getSimpleName();

	private List<Step> steps;
	private AdapterClickListener clickListener;

	public StepAdapter(List<Step> steps, AdapterClickListener clickListener) {
		this.steps = steps;
		this.clickListener = clickListener;
	}

	@NonNull
	@Override
	public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		Context context = parent.getContext();
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(R.layout.step_list_item, parent, false);

		return new StepViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
		final Step step = this.steps.get(position);

		holder.bind(step);
	}

	@Override
	public int getItemCount() {
		return this.steps.size();
	}

	class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private TextView shortDescription;

		public StepViewHolder(@NonNull View itemView) {
			super(itemView);

			this.loadViews(itemView);

			itemView.setOnClickListener(this);
		}

		private void loadViews(View view) {
			this.shortDescription = view.findViewById(R.id.step_item_short_description);
		}

		void bind(Step step) {
			final String shortDescription = step.getShortDescription();

			this.shortDescription.setText(shortDescription);
		}

		@Override
		public void onClick(View v) {
			final int position = this.getAdapterPosition();

			clickListener.onItemClick(position);
		}
	}
}
