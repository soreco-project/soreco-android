package ch.soreco.android.ui.setup;

import android.view.View;

import ch.soreco.android.ui.PresenterIfc;
import ch.soreco.android.ui.ViewIfc;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */

public interface SetupContract {
    interface View extends ViewIfc<Presenter> {
        void setCancelable(boolean state);
        void setCancelCallback(android.view.View.OnClickListener callback);
    }

    interface Presenter extends PresenterIfc<View> {
        void enableCancelable(android.view.View.OnClickListener callback);
        void disableCancelable();
    }
}
