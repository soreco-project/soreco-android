package ch.soreco.android.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
@Module
public abstract class ApplicationModule {
    @Binds
    abstract Context bindContext(Application application);
}
