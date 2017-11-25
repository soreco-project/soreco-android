package ch.soreco.android.ui;

/**
 * Created by sandro.pedrett on 20.11.2017.
 */
public interface PresenterIfc<View> {

    void bindView(View view);

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onRestart();
}
