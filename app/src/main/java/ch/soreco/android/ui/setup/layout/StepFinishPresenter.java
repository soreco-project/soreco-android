package ch.soreco.android.ui.setup.layout;


import android.net.wifi.WifiConfiguration;

import javax.inject.Inject;

import ch.soreco.android.manager.discovery.DiscoveryManagerIfc;
import ch.soreco.android.ui.BasePresenter;
import ch.soreco.android.ui.setup.SetupContract;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */

public class StepFinishPresenter extends BasePresenter<StepFinishContract.View> implements StepFinishContract.Presenter {
    private DiscoveryManagerIfc discoveryManager;
    private SetupContract.Presenter setupPresenter;
    private StepFinishContract.View view;

    @Inject
    StepFinishPresenter(final DiscoveryManagerIfc discoveryManager, final SetupContract.Presenter setupPresenter) {
        this.discoveryManager = discoveryManager;
        this.setupPresenter = setupPresenter;
    }

    @Override
    public void bindView(StepFinishContract.View view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        view.setSSID(discoveryManager.getWifiConfigurations());
    }

    @Override
    public void setConfiguration(WifiConfiguration item, String password) {
        setupPresenter.setWifiConfiguration(item, password);
    }

    @Override
    public boolean isValid() {
        return setupPresenter.isValid();
    }
}
