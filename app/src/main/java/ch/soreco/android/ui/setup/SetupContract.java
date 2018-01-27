package ch.soreco.android.ui.setup;

import ch.soreco.android.ui.PresenterIfc;
import ch.soreco.android.ui.ViewIfc;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */

public interface SetupContract {
    interface View extends ViewIfc<Presenter> {

    }

    interface Presenter extends PresenterIfc<View> {
    }
}
