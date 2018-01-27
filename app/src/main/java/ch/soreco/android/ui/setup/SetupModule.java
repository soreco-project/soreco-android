package ch.soreco.android.ui.setup;

import ch.soreco.android.di.ActivityScoped;
import ch.soreco.android.di.FragmentScoped;
import ch.soreco.android.ui.setup.layout.StepDiscoveryContract;
import ch.soreco.android.ui.setup.layout.StepDiscoveryLayout;
import ch.soreco.android.ui.setup.layout.StepDiscoveryPresenter;
import ch.soreco.android.ui.setup.layout.StepFinishContract;
import ch.soreco.android.ui.setup.layout.StepFinishLayout;
import ch.soreco.android.ui.setup.layout.StepFinishPresenter;
import ch.soreco.android.ui.setup.layout.StepInfoContract;
import ch.soreco.android.ui.setup.layout.StepInfoLayout;
import ch.soreco.android.ui.setup.layout.StepInfoPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
@Module
public abstract class SetupModule {
    @ActivityScoped
    @Binds
    abstract SetupContract.Presenter setupPresenter(SetupPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract StepInfoLayout stepInfoLayout();

    @FragmentScoped
    @Binds
    abstract StepInfoContract.Presenter stepInfoPresenter(StepInfoPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract StepDiscoveryLayout stepDiscoveryLayout();

    @FragmentScoped
    @Binds
    abstract StepDiscoveryContract.Presenter stepDiscoveryPresenter(StepDiscoveryPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract StepFinishLayout stepFinishLayout();

    @FragmentScoped
    @Binds
    abstract StepFinishContract.Presenter stepFinishPresenter(StepFinishPresenter presenter);

}
