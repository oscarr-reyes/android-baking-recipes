package dev.oscarreyes.bakingrecipes.util;

import androidx.annotation.Nullable;
import androidx.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

public class DataIdlingResource implements IdlingResource {
	@Nullable private volatile ResourceCallback callback;

	private AtomicBoolean isIdle = new AtomicBoolean(true);

	@Override
	public String getName() {
		return this.getClass().getName();
	}

	@Override
	public boolean isIdleNow() {
		return this.isIdle.get();
	}

	@Override
	public void registerIdleTransitionCallback(ResourceCallback callback) {
		this.callback = callback;
	}

	public void setIdleState(boolean isIdleNow) {
		this.isIdle.set(isIdleNow);

		if (isIdleNow && this.callback != null) {
			this.callback.onTransitionToIdle();
		}
	}
}
