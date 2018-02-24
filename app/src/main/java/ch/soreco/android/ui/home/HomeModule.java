package ch.soreco.android.ui.home;

import ch.soreco.android.di.ActivityScoped;
import dagger.Binds;
import dagger.Module;

/**
 * Created by sandro.pedrett on 24.02.2018.
 */
@Module
public abstract class HomeModule {
    @ActivityScoped
    @Binds
    abstract HomeContract.Presenter homePresenter(HomePresenter presenter);
}
