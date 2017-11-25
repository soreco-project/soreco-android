package ch.soreco.android.ui.discovery;

import ch.soreco.android.ui.PresenterIfc;
import ch.soreco.android.ui.ViewIfc;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */

public interface DiscoveryContract {
    interface View extends ViewIfc<Presenter> {

    }

    interface Presenter extends PresenterIfc<View> {
    }
}
