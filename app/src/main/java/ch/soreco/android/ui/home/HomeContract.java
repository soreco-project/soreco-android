package ch.soreco.android.ui.home;

import ch.soreco.android.ui.PresenterIfc;
import ch.soreco.android.ui.ViewIfc;

/**
 * Created by sandro.pedrett on 24.02.2018.
 */

public interface HomeContract {
    interface View extends ViewIfc<Presenter> {

    }

    interface Presenter extends PresenterIfc<View> {
    }
}
