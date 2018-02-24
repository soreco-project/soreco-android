package ch.soreco.android.di;

import javax.inject.Singleton;

import ch.soreco.android.manager.device.SorecoDeviceManager;
import ch.soreco.android.manager.device.SorecoDeviceManagerIfc;
import ch.soreco.android.manager.discovery.DiscoveryManager;
import ch.soreco.android.manager.discovery.DiscoveryManagerIfc;
import dagger.Binds;
import dagger.Module;

/**
 * Created by sandro.pedrett on 29.01.2018.
 */
@Module
public abstract class ServiceModule {
    @Singleton
    @Binds
    abstract DiscoveryManagerIfc discoveryManager(DiscoveryManager manager);

    @Singleton
    @Binds
    abstract SorecoDeviceManagerIfc deviceManager(SorecoDeviceManager manager);
}
