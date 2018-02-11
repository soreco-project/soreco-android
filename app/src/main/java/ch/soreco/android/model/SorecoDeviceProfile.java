package ch.soreco.android.model;

import android.net.wifi.WifiConfiguration;

/**
 * Created by sandro.pedrett on 29.01.2018.
 */

public class SorecoDeviceProfile {
    private WifiConfiguration hotspotAuthentication;

    public void setHotspotAuthentication(WifiConfiguration hotspotAuthentication) {
        this.hotspotAuthentication = hotspotAuthentication;
    }
}
