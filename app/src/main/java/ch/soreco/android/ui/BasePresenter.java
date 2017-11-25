package ch.soreco.android.ui;

import android.support.annotation.CallSuper;

import javax.inject.Inject;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
public abstract class BasePresenter<TView extends ViewIfc<?>> implements PresenterIfc<TView> {

    @Inject
    protected NavigatorIfc navigator;

    @Override
    @CallSuper
    public void onCreate() {

    }

    @Override
    @CallSuper
    public void onStart() {

    }

    @Override
    @CallSuper
    public void onResume() {

    }

    @Override
    @CallSuper
    public void onPause() {

    }

    @Override
    @CallSuper
    public void onStop() {

    }

    @Override
    @CallSuper
    public void onRestart() {

    }
}
