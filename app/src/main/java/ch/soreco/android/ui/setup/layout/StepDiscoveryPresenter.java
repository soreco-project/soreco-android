package ch.soreco.android.ui.setup.layout;

import android.view.View;

import java.util.List;

import javax.inject.Inject;

import ch.soreco.android.manager.discovery.DiscoveryManagerIfc;
import ch.soreco.android.model.SorecoDeviceProfile;
import ch.soreco.android.ui.BasePresenter;
import ch.soreco.android.ui.setup.SetupContract;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */

public class StepDiscoveryPresenter extends BasePresenter<StepDiscoveryContract.View> implements StepDiscoveryContract.Presenter, DiscoveryManagerIfc.Listener  {
    private SetupContract.Presenter wizardPresenter;
    private DiscoveryManagerIfc discoveryManager;

    private View.OnClickListener cancelAction = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            cancelDiscovery();
        }
    };
    private StepDiscoveryContract.View view;

    @Inject
    StepDiscoveryPresenter(final SetupContract.Presenter wizardPresenter, final DiscoveryManagerIfc discoveryManager) {
        this.wizardPresenter = wizardPresenter;
        this.discoveryManager = discoveryManager;
    }

    @Override
    public void bindView(StepDiscoveryContract.View view) {
        this.view = view;
    }

    @Override
    public void discoveryExecute() {
        wizardPresenter.enableCancelable(cancelAction);

        discoveryManager.findSorecoDevicesAsync(this, view);
    }

    @Override
    public void cancelDiscovery() {
        view.showMessage("Discovery canceled");

        discoveryManager.cancel();

        wizardPresenter.disableCancelable();
        wizardPresenter.prevPage();
    }

    @Override
    public boolean isValid() {
        return !discoveryManager.isRunning();
    }

    @Override
    public void onSorecoDevicesFound(List<SorecoDeviceProfile> devices) {
        if (devices.size() == 0) {
            wizardPresenter.disableCancelable();
            wizardPresenter.prevPage();
            view.showMessage("No devices found. Make sure your soreco is running.");
        }
    }

    @Override
    public void onError(Throwable throwable) {
        wizardPresenter.disableCancelable();
        wizardPresenter.prevPage();

        view.showMessage(throwable.getMessage());
    }
}
