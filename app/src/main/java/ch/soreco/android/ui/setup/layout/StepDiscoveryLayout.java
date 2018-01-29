package ch.soreco.android.ui.setup.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import ch.soreco.android.R;
import ch.soreco.android.ui.setup.control.CircleProgressBar;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */
public class StepDiscoveryLayout extends SetupStepLayout<StepDiscoveryContract.Presenter> implements StepDiscoveryContract.View {
    private CircleProgressBar circleProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_setup_step_discovery, container, false);
        circleProgressBar = view.findViewById(R.id.progressBar);

        return view;
    }

    @Override
    public boolean isValid() {
        return presenter.isValid();
    }

    @Override
    public void onActivated() {
        super.onActivated();
        presenter.discoveryExecute();
    }

    @Override
    @Inject
    protected void bindToPresenter(StepDiscoveryContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }

    @Override
    public void setPercent(final float percent) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                circleProgressBar.setProgress(percent);
            }
        });
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
