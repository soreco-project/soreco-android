package ch.soreco.android.ui.setup;

import android.net.wifi.WifiConfiguration;
import android.view.View;

import javax.inject.Inject;

import ch.soreco.android.model.SorecoDeviceProfile;
import ch.soreco.android.ui.BasePresenter;
import ch.soreco.android.ui.NavigatorIfc;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */

public class SetupPresenter extends BasePresenter<SetupContract.View> implements SetupContract.Presenter {
    private SetupContract.View view;
    private NavigatorIfc navigator;
    private SorecoDeviceProfile device;

    private WifiConfiguration wifiConfiguration;
    private String password;

    @Inject
    SetupPresenter(final NavigatorIfc navigator) {
        this.navigator = navigator;
    }

    @Override
    public void bindView(SetupContract.View view) {
        this.view = view;
    }

    @Override
    public void enableCancelable(View.OnClickListener callback) {
        view.setCancelable(true);
        view.setCancelCallback(callback);
    }

    @Override
    public void disableCancelable() {
        view.setCancelable(false);
        view.setCancelCallback(null);
    }

    @Override
    public void nextPage() {
        view.nextPage();
    }

    @Override
    public void prevPage() {
        view.prevPage();
    }

    @Override
    public void setDevice(SorecoDeviceProfile device) {
        this.device = device;
    }

    @Override
    public void setWifiConfiguration(WifiConfiguration wifiConfiguration, String password) {
        this.wifiConfiguration = wifiConfiguration;
        this.password = password;
    }

    @Override
    public void finish() {
        // setup device
        // 1) deviceManager.initialize(device);
        // 2) deviceManager.publishWifiConfig(callback);
        // 3) callback -> navigator.navigateToHomeScreen();
    }

    @Override
    public boolean isValid() {
        return device != null && wifiConfiguration != null;
    }
}
