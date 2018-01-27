package ch.soreco.android.ui.startup;

import ch.soreco.android.ui.ViewIfc;
import ch.soreco.android.ui.PresenterIfc;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */

public interface StartupContract {
    interface View extends ViewIfc<Presenter> {

    }

    interface Presenter extends PresenterIfc<View> {
        void startSetupWizard();
    }
}
