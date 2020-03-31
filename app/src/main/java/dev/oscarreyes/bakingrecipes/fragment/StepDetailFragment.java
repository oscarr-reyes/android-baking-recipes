package dev.oscarreyes.bakingrecipes.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import dev.oscarreyes.bakingrecipes.R;
import dev.oscarreyes.bakingrecipes.entity.Step;

public class StepDetailFragment extends Fragment {

	private Step step;

	private PlayerView stepPlayer;

	private SimpleExoPlayer exoPlayer;

	public StepDetailFragment(Step step) {
		this.step = step;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);

		this.loadViews(rootView);

		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();

		final String stepVideo = this.step.getVideoURL();

		if (!stepVideo.isEmpty()) {
			this.setupPlayer(Uri.parse(stepVideo));
		}
	}

	private void loadViews(View view) {
		this.stepPlayer = view.findViewById(R.id.step_detail_player);
	}

	private void setupPlayer(Uri mediaUri) {
		final Context context = this.getContext();
		final String agent = Util.getUserAgent(context, "BakingRecipes");
		final DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(context, agent);
		final DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
		final TrackSelector trackSelector = new DefaultTrackSelector();
		final LoadControl loadControl = new DefaultLoadControl();

		this.exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);

		this.stepPlayer.setPlayer(this.exoPlayer);

		MediaSource mediaSource = new ExtractorMediaSource(
			mediaUri,
			dataSourceFactory,
			extractorsFactory,
			null,
			null
		);

		this.exoPlayer.prepare(mediaSource);
	}

	private void playerRelease() {
		if (this.exoPlayer != null) {
			this.exoPlayer.stop();
			this.exoPlayer.release();
			this.exoPlayer = null;
		}
	}
}
