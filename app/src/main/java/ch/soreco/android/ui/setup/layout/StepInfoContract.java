package ch.soreco.android.ui.setup.layout;

import ch.soreco.android.ui.PresenterIfc;
import ch.soreco.android.ui.ViewIfc;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */

public interface StepInfoContract {
    interface View extends ViewIfc<Presenter> {

    }

    interface Presenter extends PresenterIfc<View> {
        boolean isValid();
    }
}
