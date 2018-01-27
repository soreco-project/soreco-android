package ch.soreco.android.ui.setup.layout;

import javax.inject.Inject;

import ch.soreco.android.ui.BasePresenter;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */

public class StepFinishPresenter extends BasePresenter<StepFinishContract.View> implements StepFinishContract.Presenter {
    @Inject
    StepFinishPresenter() {

    }

    @Override
    public void bindView(StepFinishContract.View view) {

    }

    @Override
    public boolean isValid() {
        return true;
    }
}
