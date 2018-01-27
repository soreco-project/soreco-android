package ch.soreco.android.ui.setup.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ch.soreco.android.R;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */
public class StepFinishLayout extends SetupStepLayout<StepFinishContract.Presenter> implements StepFinishContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_setup_step_finish, container, false);
    }

    @Override
    public boolean isValid() {
        return presenter.isValid();
    }

    @Override
    @Inject
    protected void bindToPresenter(StepFinishContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }
}
