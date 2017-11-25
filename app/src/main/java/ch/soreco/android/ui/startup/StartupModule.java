package ch.soreco.android.ui.startup;

import ch.soreco.android.di.ActivityScoped;
import ch.soreco.android.di.FragmentScoped;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by sandro.pedrett on 20.11.2017.
 */
@Module
public abstract class StartupModule {
    @ActivityScoped
    @Binds
    abstract StartupContract.Presenter startupPresenter(StartupPresenter presenter);
}
