package ch.soreco.android.manager.device;

import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;

import ch.soreco.android.SorecoProtocol;

import static ch.soreco.android.SorecoProtocol.*;

/**
 * Created by sandro.pedrett on 25.02.2018.
 */

public class RemoteStack implements RemoteStackIfc {
    private OutputStream stream;

    @Inject
    public RemoteStack() {
    }

    public void initialize(OutputStream stream) {
        this.stream = stream;
    }

    @Override
    public void sendWifiConfig(String ssid, String password) {
        WifiConfigMessage.Builder wifiBuilder = WifiConfigMessage.newBuilder();
        wifiBuilder.setPassword(password);
        wifiBuilder.setSsid(ssid);

        Protocol.Builder protocolBuilder = Protocol.newBuilder();
        protocolBuilder.setWifiConfig(wifiBuilder);

        sendMessage(protocolBuilder);
    }

    private void sendMessage(Protocol.Builder builder) {
        try {
            builder.build().writeTo(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
