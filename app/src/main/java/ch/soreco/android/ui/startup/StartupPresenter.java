package ch.soreco.android.ui.startup;

import javax.inject.Inject;

import ch.soreco.android.ui.BasePresenter;

/**
 * Created by sandro.pedrett on 20.11.2017.
 */

public class StartupPresenter extends BasePresenter<StartupContract.View> implements StartupContract.Presenter {
    @Inject
    public StartupPresenter() {

    }

    @Override
    public void bindView(StartupContract.View view) {

    }

    @Override
    public void onStart() {
        super.onStart();

        // TODO check if firmware already configured?
        // then go to Main
    }

    public void startDiscoverWizard() {
        // TODO start DiscoverActivity
    }
}
