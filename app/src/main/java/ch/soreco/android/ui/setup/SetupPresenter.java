package ch.soreco.android.ui.setup;

import android.view.View;

import javax.inject.Inject;

import ch.soreco.android.model.SorecoDevice;
import ch.soreco.android.ui.BasePresenter;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */

public class SetupPresenter extends BasePresenter<SetupContract.View> implements SetupContract.Presenter {
    private SetupContract.View view;

    @Inject
    SetupPresenter() {

    }

    @Override
    public void bindView(SetupContract.View view) {
        this.view = view;
    }

    @Override
    public void enableCancelable(View.OnClickListener callback) {
        view.setCancelable(true);
        view.setCancelCallback(callback);
    }

    @Override
    public void disableCancelable() {
        view.setCancelable(false);
        view.setCancelCallback(null);
    }

    @Override
    public void nextPage() {
        view.nextPage();
    }

    @Override
    public void prevPage() {
        view.prevPage();
    }

    @Override
    public void setDevice(SorecoDevice device) {

    }
}
