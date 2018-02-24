package ch.soreco.android.ui.home;

import javax.inject.Inject;

import ch.soreco.android.manager.device.SorecoDeviceManagerIfc;
import ch.soreco.android.ui.BasePresenter;

/**
 * Created by sandro.pedrett on 24.02.2018.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    private SorecoDeviceManagerIfc deviceManager;

    @Inject
    public HomePresenter(final SorecoDeviceManagerIfc deviceManager) {
        this.deviceManager = deviceManager;
    }

    @Override
    public void onResume() {
        super.onResume();
        // TODO register device notification
    }

    @Override
    public void onPause() {
        super.onPause();
        // TODO unregister notification
    }

    @Override
    public void bindView(HomeContract.View view) {

    }
}
