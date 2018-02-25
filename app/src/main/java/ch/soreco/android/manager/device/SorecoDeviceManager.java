package ch.soreco.android.manager.device;


import javax.inject.Inject;

import ch.soreco.android.manager.wifi.WifiControllerIfc;
import ch.soreco.android.model.SorecoDeviceProfile;

/**
 * Created by sandro.pedrett on 24.02.2018.
 */
public class SorecoDeviceManager implements SorecoDeviceManagerIfc {
    private final RemoteStackIfc remoteStack;
    private final WifiControllerIfc wifiController;

    private SorecoDeviceProfile device;

    @Inject
    public SorecoDeviceManager(final RemoteStackIfc remoteStack, final WifiControllerIfc wifiController){
        this.remoteStack = remoteStack;
        this.wifiController = wifiController;
    }

    @Override
    public void initialize(final SorecoDeviceProfile device) {
        this.device = device;
        // TODO discovery device in wifi
        // TODO get details (SN)
    }

    @Override
    public boolean publishWifiConfig(final String SSID, final String password) {
        boolean result = connectSorecoHotspot();


        remoteStack.sendWifiConfig(SSID, password);

        return result;
    }

    @Override
    public void reset() {
        // TODO send reset command
    }

    private boolean connectSorecoHotspot() {
        return wifiController.connectTo(device.getHotspotConfig());
    }
}
