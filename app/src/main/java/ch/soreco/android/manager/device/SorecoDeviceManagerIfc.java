package ch.soreco.android.manager.device;

import ch.soreco.android.model.SorecoDeviceProfile;

/**
 * Manages one soreco device. Has do be initialize with a valid SorecoDeviceProfile.
 *
 * Created by sandro.pedrett on 24.02.2018.
 */
public interface SorecoDeviceManagerIfc {
    void initialize(final SorecoDeviceProfile device);

    /**
     * Publish wifi configuration.
     * @param SSID
     * @param password
     */
    boolean publishWifiConfig(String SSID, String password);

    /**
     * Reset soreco device
     */
    void reset();
}
