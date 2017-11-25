package ch.soreco.android.ui;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Created by sandro.pedrett on 20.11.2017.
 */
public abstract class BaseFragmentView<PresenterT extends PresenterIfc<?>> extends DaggerFragment {
    @Inject
    protected PresenterT presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            throw new IllegalStateException("Presenter is not initialized. Missing @Inject on bindToPresenter.");
        }
        presenter.onCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    /**
     * Use this to set the presenter and bind the presenter to the view.
     */
    protected abstract void bindToPresenter(PresenterT presenter);
}
