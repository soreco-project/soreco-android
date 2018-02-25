package ch.soreco.android.ui.setup.layout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ch.soreco.android.R;
import ch.soreco.android.manager.wifi.WifiControllerIfc;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */
public class StepDiscoveryLayout extends SetupStepLayout<StepDiscoveryContract.Presenter> implements StepDiscoveryContract.View {
    private static final int COARSE_LOCATION_CODE = 0;
    private static final int FINE_LOCATION_CODE = 1;

    private WifiManager wifiManager;
    private WifiControllerIfc.WifiPermissionCallback wifiScanListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // get UI components
        final View view = inflater.inflate(R.layout.fragment_setup_step_discovery, container, false);

        // get services
        wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        return view;
    }

    @Override
    public boolean isValid() {
        return presenter.isValid();
    }

    @Override
    public void onActivated() {
        super.onActivated();
        presenter.discoveryExecute();
    }

    @Override
    public void onDeactivated(boolean forward) {
        super.onDeactivated(forward);
        if (!forward) {
            presenter.cancelDiscovery();
        }
    }

    @Override
    @Inject
    protected void bindToPresenter(StepDiscoveryContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void requestWifiScanResult(final WifiControllerIfc.WifiPermissionCallback listener) {
        wifiScanListener = listener;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // wifi scan is only available if location is enabled
            LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            if (locationManager != null && !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, COARSE_LOCATION_CODE);
        } else {
            wifiScanListener.onWifiScanResult(wifiManager.getScanResults());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case COARSE_LOCATION_CODE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("SORECO", "No access to location.");
                }

                wifiScanListener.onWifiScanResult(wifiManager.getScanResults());
                break;
            case FINE_LOCATION_CODE:
                break;
        }
    }
}
