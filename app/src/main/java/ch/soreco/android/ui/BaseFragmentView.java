package ch.soreco.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Created by sandro.pedrett on 20.11.2017.
 */
public abstract class BaseFragmentView<PresenterT extends PresenterIfc<?>> extends DaggerFragment {
    protected PresenterT presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter == null) {
            throw new IllegalStateException("Presenter is not initialized. Missing @Inject on bindToPresenter.");
        }
        presenter.onCreate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onStop();
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

    public PresenterT getPresenter() {
        return presenter;
    }

    protected abstract void bindToPresenter(PresenterT presenter);
}
