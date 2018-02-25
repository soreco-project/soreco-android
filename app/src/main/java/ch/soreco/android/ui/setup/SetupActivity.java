package ch.soreco.android.ui.setup;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

import javax.inject.Inject;

import ch.soreco.android.R;
import ch.soreco.android.ui.BaseActivityView;
import ch.soreco.android.ui.setup.control.WizardNavigation;
import ch.soreco.android.ui.setup.layout.*;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
public class SetupActivity extends BaseActivityView<SetupContract.Presenter> implements SetupContract.View {
    private WizardNavigation wizardNavigator;

    private int lastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        lastPage = 0;

        createViewPager();
    }

    @Override
    public void onStart() {
        super.onStart();

        initializeViewPager();
    }

    private void createViewPager() {
        ArrayList<SetupStepLayout> pages = new ArrayList<>();
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
                SetupStepLayout currentPage = wizardNavigator.getPage(lastPage);
                SetupStepLayout newPage;
                // validation if next page
                if (lastPage < position && !currentPage.isValid()) {
                    wizardNavigator.setPage(lastPage);
                    return;
                }

                boolean forward = lastPage < position;
                currentPage.onDeactivated(forward);

                newPage = wizardNavigator.getPage(position);
                newPage.onActivated();

                lastPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentFrame, wizardNavigator);
        transaction.commit();
    }

    private void initializeViewPager() {
        wizardNavigator.setNextAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetupStepLayout currentStep = wizardNavigator.getPage(lastPage);

                currentStep.commit();
                if (wizardNavigator.isLastPage() && currentStep.isValid()) {
                    currentStep.onDeactivated(true);

                    presenter.finish();
                    return;
                }

                wizardNavigator.setPage(lastPage + 1);
            }
        });
        wizardNavigator.setPrevAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wizardNavigator.setPage(lastPage - 1);
            }
        });
    }

    @Override
    public void setCancelCallback(View.OnClickListener callback) {
        wizardNavigator.setCancelAction(callback);
    }

    @Override
    public void nextPage() {
        wizardNavigator.setPage(lastPage + 1);
    }

    @Override
    public void prevPage() {
        wizardNavigator.setPage(lastPage - 1);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setCancelable(boolean state) {
        if (state) {
            wizardNavigator.enableCancelMode();
        } else {
            wizardNavigator.disableCancelMode();
        }
    }

    @Override
    public void onBackPressed() {
        // only back if not first page
        if (lastPage != 0) {
            wizardNavigator.setPage(lastPage - 1);
        }
    }

    @Override
    @Inject
    protected void bindToPresenter(SetupContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }
}
