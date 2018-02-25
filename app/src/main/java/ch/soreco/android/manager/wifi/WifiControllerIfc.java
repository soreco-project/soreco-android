package ch.soreco.android.manager.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;

import java.util.List;

/**
 * Created by sandro.pedrett on 25.02.2018.
 */

public interface WifiControllerIfc {

    void enableWifi();
    void startWifiScan(WifiPermissionHandler permissionHandler);
    void cancelWifiScan();

    int addNetwork(WifiConfiguration configuration);

    List<WifiConfiguration> getConfiguredNetworks();

    interface WifiPermissionHandler {
        void requestWifiScanResult(final WifiPermissionCallback handler);

        WifiPermissionCallback requestWifiScanCallback();
    }

    interface WifiPermissionCallback {
        void onWifiScanResult(List<ScanResult> result);
    }
}
