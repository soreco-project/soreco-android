package ch.soreco.android.ui.setup.layout;

import android.view.View;

import javax.inject.Inject;

import ch.soreco.android.ui.BasePresenter;
import ch.soreco.android.ui.setup.SetupContract;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */

public class StepDiscoveryPresenter extends BasePresenter<StepDiscoveryContract.View> implements StepDiscoveryContract.Presenter  {
    private SetupContract.Presenter wizardPresenter;
    private boolean isDiscoveryRunning;
    private View.OnClickListener cancelAction = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            cancelDiscovery();
        }
    };

    @Inject
    StepDiscoveryPresenter(final SetupContract.Presenter wizardPresenter) {
        this.wizardPresenter = wizardPresenter;

    }

    @Override
    public void bindView(StepDiscoveryContract.View view) {
    }

    @Override
    public void discoverDevices() {
        wizardPresenter.enableCancelable(cancelAction);
        isDiscoveryRunning = true;
    }

    @Override
    public void cancelDiscovery() {
        wizardPresenter.disableCancelable();
        isDiscoveryRunning = false;
    }

    @Override
    public boolean isValid() {
        return !isDiscoveryRunning;
    }
}
