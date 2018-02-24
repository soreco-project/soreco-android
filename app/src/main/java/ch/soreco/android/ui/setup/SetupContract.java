package ch.soreco.android.ui.setup;

import android.net.wifi.WifiConfiguration;

import ch.soreco.android.model.SorecoDeviceProfile;
import ch.soreco.android.ui.PresenterIfc;
import ch.soreco.android.ui.ViewIfc;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */

public interface SetupContract {
    interface View extends ViewIfc<Presenter> {
        void setCancelable(boolean state);
        void setCancelCallback(android.view.View.OnClickListener callback);

        void nextPage();
        void prevPage();
    }

    interface Presenter extends PresenterIfc<View> {
        void enableCancelable(android.view.View.OnClickListener callback);
        void disableCancelable();

        void nextPage();
        void prevPage();

        void setDevice(SorecoDeviceProfile device);
        void setWifiConfiguration(WifiConfiguration item, String password);

        void finish();

        boolean isValid();

    }
}
