package com.liaoda.yunzan.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class YzHomeFragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    private Fragment[] fragments;
    private String[] titles;

    public YzHomeFragmentAdapter(FragmentManager fm, Context context, Fragment[] fragments, String[] titles) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
    }

    public Fragment getItem(int position) {
        return this.fragments[position];
    }

    public int getCount() {
        return this.titles.length;
    }

    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}