package ch.soreco.android.ui.setup.layout;

import ch.soreco.android.manager.wifi.WifiControllerIfc;
import ch.soreco.android.ui.PresenterIfc;
import ch.soreco.android.ui.ViewIfc;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */

public interface StepDiscoveryContract {
    interface View extends ViewIfc<Presenter>, WifiControllerIfc.WifiPermissionHandler {
        void showMessage(String message);
    }

    interface Presenter extends PresenterIfc<View> {
        void discoveryExecute();
        void cancelDiscovery();

        boolean isValid();
    }
}
