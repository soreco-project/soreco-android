package ch.soreco.android.ui.discovery;

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

import java.util.ArrayList;

import ch.soreco.android.R;
import ch.soreco.android.ui.discovery.layout.DiscoveryLayout;

/**
 * Created by sandro.pedrett on 25.11.2017.
 */
public class ViewPagerContainerFragment extends Fragment {
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private ArrayList<DiscoveryLayout> pages;

    // UI
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    public static ViewPagerContainerFragment newInstance(ArrayList<DiscoveryLayout> pages, ViewPager.OnPageChangeListener listener) {
        Bundle bundle = new Bundle();
        ViewPagerContainerFragment fragment = new ViewPagerContainerFragment();
        fragment.setArguments(bundle);
        fragment.onPageChangeListener = listener;
        fragment.pages = pages;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view_pager, container, false);

        pagerAdapter = new Adapter(getFragmentManager());
        viewPager = root.findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);

        TabLayout tabs = root.findViewById(R.id.tabLayout);
        tabs.setupWithViewPager(viewPager, true);

        return root;
    }

    public DiscoveryLayout getPage(int position) {
        return pages.get(position);
    }

    public void setPage(int position) {
        viewPager.setCurrentItem(position);
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
        public int getCount() {
            return pages.size();
        }
    }
}
