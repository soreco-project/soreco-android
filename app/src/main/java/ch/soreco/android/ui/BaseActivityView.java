package ch.soreco.android.ui;

import android.os.Bundle;
import android.support.annotation.CallSuper;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by sandro.pedrett on 20.11.2017.
 */
public abstract class BaseActivityView<PresenterT extends PresenterIfc<?>> extends DaggerAppCompatActivity {
    protected PresenterT presenter;

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            throw new IllegalStateException("Presenter is not initialized. Missing @Inject on bindToPresenter.");
        }
        presenter.onCreate();
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    @CallSuper
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    @CallSuper
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    @CallSuper
    protected void onRestart() {
        super.onRestart();
        presenter.onRestart();
    }

    /**
     * Use this to set the presenter and bind the presenter to the view.
     */
    protected abstract void bindToPresenter(PresenterT presenter);
}
