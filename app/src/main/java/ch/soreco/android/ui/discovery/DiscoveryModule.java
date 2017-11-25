package ch.soreco.android.ui.discovery;

import ch.soreco.android.di.ActivityScoped;
import dagger.Binds;
import dagger.Module;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
@Module
public abstract class DiscoveryModule {
    @ActivityScoped
    @Binds
    abstract DiscoveryContract.Presenter discoveryPresenter(DiscoveryPresenter presenter);
}
