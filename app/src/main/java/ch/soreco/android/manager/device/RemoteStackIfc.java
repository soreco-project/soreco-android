package ch.soreco.android.manager.device;

import java.io.OutputStream;

/**
 * Created by sandro.pedrett on 25.02.2018.
 */

public interface RemoteStackIfc {
    void initialize(OutputStream stream);

    void sendWifiConfig(String ssid, String password);
}
