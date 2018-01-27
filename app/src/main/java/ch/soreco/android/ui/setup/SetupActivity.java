package ch.soreco.android.ui.setup;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import javax.inject.Inject;

import ch.soreco.android.R;
import ch.soreco.android.ui.BaseActivityView;
import ch.soreco.android.ui.setup.layout.SetupLayout;
import ch.soreco.android.ui.setup.layout.StepInfoLayout;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
public class SetupActivity extends BaseActivityView<SetupContract.Presenter> implements SetupContract.View {
    private ViewPagerContainerFragment viewPageContainer;

    private int selectedPagePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        selectedPagePos = 0;

        initializeViewPager();
        showViewPagerFragment();
    }

    private void initializeViewPager() {
        ArrayList<SetupLayout> pages = new ArrayList<>();
        pages.add(new StepInfoLayout());
        pages.add(new StepInfoLayout());

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
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    SetupLayout page = viewPageContainer.getPage(selectedPagePos);

                    if (!page.isValid()) {
                        viewPageContainer.setPage(selectedPagePos - 1);
                    }
                }
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
    protected void bindToPresenter(SetupContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }
}
