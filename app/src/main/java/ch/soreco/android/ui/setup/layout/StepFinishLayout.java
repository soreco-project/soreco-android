package ch.soreco.android.ui.setup.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.soreco.android.R;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */
public class StepFinishLayout extends SetupLayout {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_setup_step_finish, container, false);
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
