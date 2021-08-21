package com.example.epidemicbigdata.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChinaTabAdapter extends FragmentStatePagerAdapter {

    private String[] titles;
    private List<Fragment> fragments;

    public ChinaTabAdapter(@NonNull @NotNull FragmentManager fm, String[] titles, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }


    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return fragments == null ? null : fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
