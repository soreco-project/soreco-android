package ch.soreco.android.ui.home;

import android.os.Bundle;

import javax.inject.Inject;

import ch.soreco.android.R;
import ch.soreco.android.ui.BaseActivityView;

/**
 * Created by sandro.pedrett on 24.02.2018.
 */

public class HomeActivity extends BaseActivityView<HomeContract.Presenter> implements HomeContract.View {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    @Inject
    protected void bindToPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }
}
