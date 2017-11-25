package ch.soreco.android.ui.discovery.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ch.soreco.android.R;
import ch.soreco.android.di.ActivityScoped;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
@ActivityScoped
public class TextStepsLayout extends DiscoveryLayout {

    @Inject
    public TextStepsLayout() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_discovery_textsteps, container, false);

        return root;
    }
}
