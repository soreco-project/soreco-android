package ch.soreco.android.di;

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
}
