package ch.soreco.android.ui.setup;

import dagger.Binds;
import dagger.Module;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
@Module
public abstract class SetupModule {
    @Binds
    abstract SetupContract.Presenter setupPresenter(SetupPresenter presenter);
}
