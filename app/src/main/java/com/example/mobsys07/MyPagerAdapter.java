package com.example.mobsys07;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private final String[] tabTitles = new String[]{"\uD83D\uDCBD", "\uD83C\uDF9E\uFE0F", "\uD83D\uDDBC\uFE0F"};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new FragmentMusic();
            case 1: return new FragmentVideo();
            case 2: return new FragmentPhoto();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
