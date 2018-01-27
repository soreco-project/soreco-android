package ch.soreco.android.ui.startup;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import ch.soreco.android.R;
import ch.soreco.android.ui.BaseActivityView;

public class StartupActivity extends BaseActivityView<StartupContract.Presenter> implements StartupContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }

    @Override
    @Inject
    protected void bindToPresenter(StartupContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }

    public void onStartPressed(View view) {
        presenter.startSetupWizard();
    }
}
