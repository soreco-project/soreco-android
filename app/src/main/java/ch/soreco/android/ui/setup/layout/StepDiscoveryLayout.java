package ch.soreco.android.ui.setup.layout;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import ch.soreco.android.R;
import ch.soreco.android.manager.discovery.DiscoveryManagerIfc;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */
public class StepDiscoveryLayout extends SetupStepLayout<StepDiscoveryContract.Presenter> implements StepDiscoveryContract.View {
    private final int coarseLocationCode = 0;

    private WifiManager wifiManager;
    private DiscoveryManagerIfc.WifiPermissionHandlerListener wifiScanListener;

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
    @Inject
    protected void bindToPresenter(StepDiscoveryContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestWifiScanResult(final DiscoveryManagerIfc.WifiPermissionHandlerListener listener) {
        wifiScanListener = listener;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, coarseLocationCode);
        } else {
            wifiScanListener.onWifiScanResult(wifiManager.getScanResults());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == coarseLocationCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (wifiManager != null && wifiScanListener != null) {
                wifiScanListener.onWifiScanResult(wifiManager.getScanResults());
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
