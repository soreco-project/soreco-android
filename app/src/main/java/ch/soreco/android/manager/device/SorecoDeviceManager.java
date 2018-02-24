package ch.soreco.android.manager.device;


import javax.inject.Inject;

import ch.soreco.android.model.SorecoDeviceProfile;

/**
 * Created by sandro.pedrett on 24.02.2018.
 */
public class SorecoDeviceManager implements SorecoDeviceManagerIfc {
    private SorecoDeviceProfile device;

    @Inject
    public SorecoDeviceManager(){

    }

    @Override
    public void initialize(final SorecoDeviceProfile device) {
        this.device = device;
        // TODO discovery device in wifi
        // TODO get details (SN)
    }

    @Override
    public void publishWifiConfig(final String SSID, final String password) {
        // TODO send to device
    }

    @Override
    public void reset() {
        // TODO send reset command
    }
}
