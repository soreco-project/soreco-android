package ch.soreco.android.manager.discovery;


import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;

import java.util.List;

import ch.soreco.android.manager.wifi.WifiControllerIfc;
import ch.soreco.android.model.SorecoDeviceProfile;

/**
 * Created by sandro.pedrett on 29.01.2018.
 */
public interface DiscoveryManagerIfc {
    /**
     * Search for soreco wifi SSID async.
     * @see Listener#onSorecoDevicesFound(List)
     */
    void findSorecoDevicesAsync(final Listener listener, WifiControllerIfc.WifiPermissionHandler permissionHandler);

    /**
     * Cancel async search.
     */
    void cancel();

    List<WifiConfiguration> getWifiConfigurations();

    /**
     * @return true if discover task is running, otherwise false
     */
    boolean isRunning();

    interface Listener {
        void onSorecoDevicesFound(final List<SorecoDeviceProfile> devices);
        void onError(final Throwable throwable);
    }
}
