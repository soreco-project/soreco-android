package ch.soreco.android.di;

import ch.soreco.android.ui.home.HomeActivity;
import ch.soreco.android.ui.home.HomeModule;
import ch.soreco.android.ui.setup.SetupActivity;
import ch.soreco.android.ui.setup.SetupModule;
import ch.soreco.android.ui.startup.StartupActivity;
import ch.soreco.android.ui.startup.StartupModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by sandro.pedrett on 20.11.2017.
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = StartupModule.class)
    abstract StartupActivity startupActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SetupModule.class)
    abstract SetupActivity setupActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();
}
