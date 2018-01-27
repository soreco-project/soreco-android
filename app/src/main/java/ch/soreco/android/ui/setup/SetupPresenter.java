package ch.soreco.android.ui.setup;

import javax.inject.Inject;

import ch.soreco.android.ui.BasePresenter;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */

public class SetupPresenter extends BasePresenter<SetupContract.View> implements SetupContract.Presenter {
    @Inject
    SetupPresenter() {

    }

    @Override
    public void bindView(SetupContract.View view) {
        // nothing to do yet
    }
}
