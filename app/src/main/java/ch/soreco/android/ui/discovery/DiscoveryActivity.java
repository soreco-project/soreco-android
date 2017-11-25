package ch.soreco.android.ui.discovery;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import javax.inject.Inject;

import ch.soreco.android.R;
import ch.soreco.android.ui.BaseActivityView;
import ch.soreco.android.ui.discovery.layout.DiscoveryLayout;
import ch.soreco.android.ui.discovery.layout.TextStepsLayout;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
public class DiscoveryActivity extends BaseActivityView<DiscoveryContract.Presenter> implements DiscoveryContract.View {
    private ViewPagerContainerFragment viewPageContainer;

    private int selectedPagePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        selectedPagePos = 0;

        initializeViewPager();
        showViewPagerFragment();
    }

    private void initializeViewPager() {
        ArrayList<DiscoveryLayout> pages = new ArrayList<>();
        pages.add(new TextStepsLayout());
        pages.add(new TextStepsLayout());

        viewPageContainer = ViewPagerContainerFragment.newInstance(pages, new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // nothing to do yet
            }

            @Override
            public void onPageSelected(int position) {
                selectedPagePos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO check if page are valid
            }
        });
    }

    private void showViewPagerFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentFrame, viewPageContainer);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        // only back if not first page
        if (selectedPagePos != 0) {
            viewPageContainer.setPage(selectedPagePos - 1);
        }
    }

    @Override
    @Inject
    protected void bindToPresenter(DiscoveryContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }
}
