package ch.soreco.android.ui;

/**
 * Created by sandro.pedrett on 20.11.2017.
 */
public interface ViewIfc<Presenter extends PresenterIfc<?>> {
    void onStart();

    void onResume();

    void onPause();

    void onStop();
}
