package ch.soreco.android.ui.setup.layout;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import javax.inject.Inject;

import ch.soreco.android.R;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */
public class StepFinishLayout extends SetupStepLayout<StepFinishContract.Presenter> implements StepFinishContract.View {
    private Spinner ssidSpinner;
    private EditText ssidPassword;
    private WifiAdapter ssidAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_setup_step_finish, container, false);
        ssidPassword = view.findViewById(R.id.ssidPassword);
        ssidSpinner = view.findViewById(R.id.ssidSpinner);

        ssidAdapter = new WifiAdapter(getContext(), android.R.layout.simple_list_item_1);
        ssidSpinner.setAdapter(ssidAdapter);

        return view;
    }

    @Override
    public void commit() {
        super.commit();

        final WifiConfiguration wifi = (WifiConfiguration) ssidSpinner.getSelectedItem();
        final String password = String.valueOf(ssidPassword.getText());

        presenter.setConfiguration(wifi, password);
    }

    @Override
    public void onActivated() {
        super.onActivated();
        presenter.initialize();
    }

    @Override
    public boolean isValid() {
        return presenter.isValid();
    }

    @Override
    @Inject
    protected void bindToPresenter(StepFinishContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }

    @Override
    public void setSSID(List<WifiConfiguration> wifiConfigurations) {
        ssidAdapter.setData(wifiConfigurations);
    }

    private class WifiAdapter extends ArrayAdapter<WifiConfiguration> {
        private List<WifiConfiguration> wifiList;
        private LayoutInflater inflater;

        public WifiAdapter(@NonNull Context context, int resource) {
            super(context, resource);
            inflater = LayoutInflater.from(getContext());
            setData(new ArrayList<WifiConfiguration>());
        }

        private void setData(List<WifiConfiguration> list) {
            this.wifiList = list;
            addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return wifiList.size();
        }

        @Nullable
        @Override
        public WifiConfiguration getItem(int position) {
            return wifiList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return createItemView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return createItemView(position, convertView, parent);
        }

        private View createItemView(int position, View view, ViewGroup parent) {
            if(view == null) {
                view = inflater.inflate(android.R.layout.simple_list_item_1, null);
            }

            final TextView ssid = view.findViewById(android.R.id.text1);

            WifiConfiguration config = wifiList.get(position);

            ssid.setText(config.SSID.replace("\"", ""));

            return view;
        }
    }
}
