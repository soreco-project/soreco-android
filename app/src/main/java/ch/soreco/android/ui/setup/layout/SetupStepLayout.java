package ch.soreco.android.ui.setup.layout;

import ch.soreco.android.ui.BaseFragmentView;
import ch.soreco.android.ui.PresenterIfc;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
public abstract class SetupStepLayout<T extends PresenterIfc<?>> extends BaseFragmentView<T> {
    /**
     * @return true if page is valid and next page is available.
     */
    public abstract boolean isValid();

    public void onActivated() {}
    public void onDeactivated(boolean forward) {}

    public void commit() {}
}
