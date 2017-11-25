package ch.soreco.android.ui.discovery;

import android.os.Bundle;

import javax.inject.Inject;

import ch.soreco.android.R;
import ch.soreco.android.ui.BaseActivityView;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
public class DiscoveryActivity extends BaseActivityView<DiscoveryContract.Presenter> implements DiscoveryContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
    }

    @Override
    @Inject
    protected void bindToPresenter(DiscoveryContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }
}
