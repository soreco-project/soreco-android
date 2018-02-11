package ch.soreco.android.manager.discovery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.soreco.android.model.SorecoDeviceProfile;

/**
 * A discovery manager for soreco devices. A soreco device as to be connected to a home network.
 * All devices has the same SSID and password, so you can easily search for wifi networks and connect to the given network.
 * In a second step you can send the home network authentications to the device and connect your phone back to home network.
 *
 * Created by sandro.pedrett on 29.01.2018.
 */
public class DiscoveryManager implements DiscoveryManagerIfc, DiscoveryManagerIfc.WifiPermissionHandlerListener {
    private final Context context;
    private final WifiManager wifiManager;
    private final WifiBroadcastReceiver wifiBroadcastReceiver;

    private Listener listener;
    private WifiPermissionHandler permissionHandler;

    private boolean isRunning;

    @Inject
    public DiscoveryManager(final Context context) {
        this.context = context;
        this.isRunning = false;
        this.wifiBroadcastReceiver = new WifiBroadcastReceiver();

        this.wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    public void findSorecoDevicesAsync(final Listener callback, final WifiPermissionHandler permissionHandler) {
        this.isRunning = true;
        this.listener = callback;
        this.permissionHandler = permissionHandler;

        registerBroadcast();

        wifiManager.setWifiEnabled(true);
        wifiManager.startScan();
    }

    @Override
    public void cancel() {
        this.isRunning = false;

        unregisterBroadcast();
    }

    @Override
    public void onWifiScanResult(List<ScanResult> result) {
        List<SorecoDeviceProfile> profiles = new ArrayList<>();

        for (ScanResult scan : result) {
            if (isWifiASorecoDevice(scan)) {
                final WifiConfiguration configuration = new WifiConfiguration();
                configuration.SSID = scan.SSID;
                configuration.preSharedKey = "PW";
                configuration.status = WifiConfiguration.Status.ENABLED;
                configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);

                SorecoDeviceProfile profile = new SorecoDeviceProfile();
                profile.setHotspotAuthentication(configuration);
            }
        }

        // stop service
        this.isRunning = false;
        unregisterBroadcast();

        listener.onSorecoDevicesFound(profiles);
    }

    private boolean isWifiASorecoDevice(ScanResult result) {
        return result.SSID.startsWith("soreco-");
    }

    private void registerBroadcast() {
        context.registerReceiver(wifiBroadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    private void unregisterBroadcast() {
        if (wifiBroadcastReceiver == null) return;

        try {
            context.unregisterReceiver(wifiBroadcastReceiver);
        } catch (Exception ignore) {
            // wifiBroadcastReceiver already unregistered
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Broadcast receiver to get wifiscan results.
     * Since Android 6 permission are changed and you have to get result by activity.
     */
    final class WifiBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterBroadcast();

            permissionHandler.requestWifiScanResult(DiscoveryManager.this);
        }
    }
}
