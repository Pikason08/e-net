package com.jp.projectone.e_net;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by keigo on 2017/06/10.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter{

    public MainFragmentPagerAdapter(FragmentManager manager){
        super(manager);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NewArrivalsFragment();
            case 1:
                return new PaddyFragment();
            case 2:
                return new WeatherFragment();
            case 3:
                return new CrawlRecordFragment();
            case 4:
                return new WorkRecordFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Arrival";
            case 1:
                return "水田情報";
            case 2:
                return "天気情報";
            case 3:
                return "見回り記録";
            case 4:
                return "作業記録";
        }
        return null;
    }
}
