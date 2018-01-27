package ch.soreco.android.ui.setup.layout;

import javax.inject.Inject;

import ch.soreco.android.ui.BasePresenter;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */
public class StepInfoPresenter extends BasePresenter<StepInfoContract.View> implements StepInfoContract.Presenter {
    @Inject
    StepInfoPresenter() {

    }

    @Override
    public void bindView(StepInfoContract.View view) {

    }

    @Override
    public boolean isValid() {
        return true;
    }
}
