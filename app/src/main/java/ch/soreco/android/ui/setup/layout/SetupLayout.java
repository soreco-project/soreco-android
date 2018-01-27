package ch.soreco.android.ui.setup.layout;

import android.support.v4.app.Fragment;

import ch.soreco.android.ui.setup.SetupContract;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
public abstract class SetupLayout extends Fragment {
    private SetupContract.Presenter presenter;

    /**
     * @return true if page is valid and next page is available.
     */
    public abstract boolean isValid();
}
