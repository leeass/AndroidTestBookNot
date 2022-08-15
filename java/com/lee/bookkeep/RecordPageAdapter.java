package com.lee.bookkeep;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class RecordPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> myList;
    private String[] title = {"支出","收入"};
    public RecordPageAdapter(@NonNull FragmentManager fm,List mList) {
        super(fm);
        myList = mList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return myList.get(position);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
