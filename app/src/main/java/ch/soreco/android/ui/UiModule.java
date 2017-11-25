package ch.soreco.android.ui;


import dagger.Binds;
import dagger.Module;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
@Module
public abstract class UiModule {
    @Binds
    public abstract NavigatorIfc navigator(Navigator navigator);
}
