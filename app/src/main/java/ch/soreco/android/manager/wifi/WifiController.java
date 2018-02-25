package ch.soreco.android.manager.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by sandro.pedrett on 25.02.2018.
 */
public class WifiController implements WifiControllerIfc {
    private final Context context;
    private final WifiManager wifiManager;
    private final WifiBroadcastReceiver wifiBroadcastReceiver;

    private WifiPermissionHandler permissionHandler;
    private WifiPermissionCallback callback;

    @Inject
    public WifiController(final Context context) {
        this.context = context;
        this.wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        this.wifiBroadcastReceiver = new WifiBroadcastReceiver();
    }


    @Override
    public void enableWifi() {
        wifiManager.setWifiEnabled(true);
    }

    @Override
    public void startWifiScan(WifiPermissionHandler permissionHandler, WifiPermissionCallback callback) {
        this.permissionHandler = permissionHandler;
        this.callback = callback;

        registerBroadcast();

        if (!wifiManager.isWifiEnabled()){
            enableWifi();
        }

        wifiManager.startScan();
    }

    @Override
    public void cancelWifiScan() {
        unregisterBroadcast();
    }

    @Override
    public boolean connectTo(WifiConfiguration config) {
        return wifiManager.enableNetwork(config.networkId, false);
    }

    @Override
    public int addNetwork(WifiConfiguration configuration) {
        return wifiManager.addNetwork(configuration);
    }

    @Override
    public List<WifiConfiguration> getConfiguredNetworks() {
        return wifiManager.getConfiguredNetworks();
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

    /**
     * Broadcast receiver to get wifiscan results.
     * Since Android 6 permission are changed and you have to get result by activity.
     */
    final class WifiBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterBroadcast();

            if (permissionHandler != null) {
                permissionHandler.requestWifiScanResult(callback);
            }
        }
    }
}
