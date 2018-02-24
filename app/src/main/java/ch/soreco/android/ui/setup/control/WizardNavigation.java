package ch.soreco.android.ui.setup.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.Button;

import java.util.ArrayList;

import ch.soreco.android.R;
import ch.soreco.android.ui.setup.layout.SetupStepLayout;

/**
 * Created by sandro.pedrett on 27.01.2018.
 */

public class WizardNavigation extends Fragment implements ViewPager.OnPageChangeListener {
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private ArrayList<SetupStepLayout> pages;

    private boolean isCancelMode;
    private boolean isLastPage;

    // UI
    private ViewPager viewPager;
    private Button nextButton;
    private Button prevButton;
    private Button cancelButton;

    public static WizardNavigation newInstance(ArrayList<SetupStepLayout> pages, ViewPager.OnPageChangeListener listener) {
        Bundle bundle = new Bundle();
        WizardNavigation fragment = new WizardNavigation();
        fragment.setArguments(bundle);
        fragment.onPageChangeListener = listener;
        fragment.pages = pages;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view_pager, container, false);

        PagerAdapter pagerAdapter = new Adapter(getFragmentManager());
        nextButton = root.findViewById(R.id.nextButton);
        prevButton = root.findViewById(R.id.prevButton);
        cancelButton = root.findViewById(R.id.cancelButton);
        viewPager = root.findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        viewPager.addOnPageChangeListener(this);

        TabLayout tabs = root.findViewById(R.id.tabLayout);
        tabs.setupWithViewPager(viewPager, true);
        tabs.clearOnTabSelectedListeners(); // disable click
        tabs.setEnabled(false);
        tabs.setSelected(false);
        tabs.setClickable(false);

        return root;
    }

    public SetupStepLayout getPage(int position) {
        if (position < 0) {
            position = 0;
        } else if (position > pages.size() - 1) {
            position = pages.size() - 1;
        }

        return pages.get(position);
    }

    public void enableCancelMode() {
        isCancelMode = true;

        nextButton.setVisibility(View.GONE);
        prevButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.VISIBLE);
    }

    public void disableCancelMode() {
        isCancelMode = false;

        nextButton.setVisibility(View.VISIBLE);
        prevButton.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.GONE);
        cancelButton.setOnClickListener(null);
    }

    public void setCancelAction(View.OnClickListener listener) {
        cancelButton.setOnClickListener(listener);
    }

    public void setNextAction(View.OnClickListener listener) {
        nextButton.setOnClickListener(listener);
    }

    public void setPrevAction(View.OnClickListener listener) {
        prevButton.setOnClickListener(listener);
    }

    public void setPage(int position) {
        viewPager.setCurrentItem(position);
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0 && !isCancelMode) {
            prevButton.setVisibility(View.GONE);
        } else if (!isCancelMode) {
            prevButton.setVisibility(View.VISIBLE);
        }

        isLastPage = position  == size() - 1;
        if (isLastPage) {
            nextButton.setText(R.string.setup_wizard_finish);
        } else {
            nextButton.setText(R.string.setup_wizard_next);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public int size() {
        return pages.size();
    }

    private class Adapter extends FragmentStatePagerAdapter {
        private Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return pages.get(position);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }
}
