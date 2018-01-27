package ch.soreco.android.ui.setup;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import javax.inject.Inject;

import ch.soreco.android.R;
import ch.soreco.android.ui.BaseActivityView;
import ch.soreco.android.ui.setup.control.WizardNavigation;
import ch.soreco.android.ui.setup.layout.SetupLayout;
import ch.soreco.android.ui.setup.layout.StepDiscoveryLayout;
import ch.soreco.android.ui.setup.layout.StepFinishLayout;
import ch.soreco.android.ui.setup.layout.StepInfoLayout;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
public class SetupActivity extends BaseActivityView<SetupContract.Presenter> implements SetupContract.View {
    private WizardNavigation wizardNavigator;

    private int selectedPagePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        selectedPagePos = 0;

        createViewPager();
    }

    @Override
    public void onStart() {
        super.onStart();

        initializeViewPager();
    }

    private void createViewPager() {
        ArrayList<SetupLayout> pages = new ArrayList<>();
        pages.add(new StepInfoLayout());
        pages.add(new StepDiscoveryLayout());
        pages.add(new StepFinishLayout());

        wizardNavigator = WizardNavigation.newInstance(pages, new ViewPager.OnPageChangeListener() {

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
                    SetupLayout page = wizardNavigator.getPage(selectedPagePos - 1);

                    if (!page.isValid()) {
                        wizardNavigator.setPage(selectedPagePos - 1);
                    }
                }
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentFrame, wizardNavigator);
        transaction.commit();
    }

    private void initializeViewPager() {
        wizardNavigator.setNextAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wizardNavigator.setPage(selectedPagePos + 1);
            }
        });
        wizardNavigator.setPrevAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wizardNavigator.setPage(selectedPagePos - 1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // only back if not first page
        if (selectedPagePos != 0) {
            wizardNavigator.setPage(selectedPagePos - 1);
        }
    }

    @Override
    @Inject
    protected void bindToPresenter(SetupContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }
}
