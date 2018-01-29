package ch.soreco.android.ui.setup.layout;

import android.view.View;

import javax.inject.Inject;

import ch.soreco.android.discovery.DiscoveryManagerIfc;
import ch.soreco.android.model.SorecoDevice;
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
    private boolean isCanceled;

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
        isCanceled = false;
        wizardPresenter.enableCancelable(cancelAction);

        task = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i <= 100; i++) {
                    if (isCanceled) {
                        return;
                    }
                    view.setPercent(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        task.start();
    }

    @Override
    public void cancelDiscovery() {
        isCanceled = true;
        view.showError("Discovery canceled");

        try {
            task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wizardPresenter.disableCancelable();

        wizardPresenter.prevPage();
    }

    @Override
    public boolean isValid() {
        return !task.isAlive();
    }

    private Thread task;
}
