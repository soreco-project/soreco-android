package ch.soreco.android.ui.setup.layout;

import android.net.wifi.WifiConfiguration;
import android.text.Editable;

import java.util.List;

import ch.soreco.android.ui.PresenterIfc;
import ch.soreco.android.ui.ViewIfc;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */

public interface StepFinishContract {
    interface View extends ViewIfc<Presenter> {

        void setSSID(List<WifiConfiguration> wifiConfigurations);
    }

    interface Presenter extends PresenterIfc<View> {
        boolean isValid();
        void initialize();

        void setConfiguration(final WifiConfiguration item, final String password);
    }
}
