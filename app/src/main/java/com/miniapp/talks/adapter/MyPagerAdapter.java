package com.miniapp.talks.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.miniapp.talks.base.HeaderViewPagerFragment;

import java.util.List;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private List<HeaderViewPagerFragment> fragments;
    private List<String> typeOnes;

    public MyPagerAdapter(FragmentManager fm, List<HeaderViewPagerFragment> mFragments, List<String> ones) {
        super(fm);
        this.fragments = mFragments;
        this.typeOnes = ones;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return typeOnes.get(position).toString();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}
