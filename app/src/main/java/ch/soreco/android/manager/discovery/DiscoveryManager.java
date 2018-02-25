package ch.soreco.android.manager.discovery;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.soreco.android.manager.wifi.WifiControllerIfc;
import ch.soreco.android.model.SorecoDeviceProfile;

/**
 * A discovery manager for soreco devices. A soreco device as to be connected to a home network.
 * All devices has the same SSID and password, so you can easily search for wifi networks and connect to the given network.
 * In a second step you can send the home network authentications to the device and connect your phone back to home network.
 *
 * Created by sandro.pedrett on 29.01.2018.
 */
public class DiscoveryManager implements DiscoveryManagerIfc, WifiControllerIfc.WifiPermissionCallback {
    // be sure that these consts are matches with the firmware
    private static final String SSID_PREFIX = "soreco-";
    private static final String DEFAULT_SORECO_PW = "abcd1234";

    private final WifiControllerIfc wifiController;

    private Listener listener;

    private boolean isRunning;

    @Inject
    public DiscoveryManager(final WifiControllerIfc wifiController) {
        this.wifiController = wifiController;
        this.isRunning = false;
    }

    @Override
    public void findSorecoDevicesAsync(final Listener callback, final WifiControllerIfc.WifiPermissionHandler permissionHandler) {
        this.isRunning = true;
        this.listener = callback;

        wifiController.enableWifi();
        wifiController.startWifiScan(permissionHandler, this);
    }

    @Override
    public void cancel() {
        this.isRunning = false;
        wifiController.cancelWifiScan();
    }

    @Override
    public List<WifiConfiguration> getWifiConfigurations() {
        return wifiController.getConfiguredNetworks();
    }

    @Override
    public void onWifiScanResult(List<ScanResult> result) {
        // stop service
        wifiController.cancelWifiScan();

        List<SorecoDeviceProfile> profiles = new ArrayList<>();
        for (ScanResult scan : result) {
            if (isWifiASorecoDevice(scan)) {
                final WifiConfiguration configuration = new WifiConfiguration();
                // Please note the quotes. String should contain ssid in quotes
                configuration.SSID = "\"" + scan.SSID + "\"";
                configuration.preSharedKey = "\"" + DEFAULT_SORECO_PW + "\"";

                configuration.status = WifiConfiguration.Status.ENABLED;
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                configuration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                configuration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                configuration.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                configuration.networkId = wifiController.addNetwork(configuration);

                SorecoDeviceProfile profile = new SorecoDeviceProfile();
                profile.setHotspotAuthentication(configuration);

                profiles.add(profile);
            }
        }

        this.isRunning = false;
        listener.onSorecoDevicesFound(profiles);
    }

    private boolean isWifiASorecoDevice(ScanResult result) {
        return result.SSID.startsWith(SSID_PREFIX);
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

}
