package ch.soreco.android.manager.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;

import java.util.List;

/**
 * Created by sandro.pedrett on 25.02.2018.
 */

public interface WifiControllerIfc {

    void enableWifi();
    void startWifiScan(WifiPermissionHandler permissionHandler, WifiPermissionCallback callback);
    void cancelWifiScan();
    boolean connectTo(WifiConfiguration config);
    int addNetwork(WifiConfiguration configuration);
    List<WifiConfiguration> getConfiguredNetworks();


    interface WifiPermissionHandler {
        void requestWifiScanResult(final WifiPermissionCallback handler);
    }

    interface WifiPermissionCallback {
        void onWifiScanResult(List<ScanResult> result);
    }
}
