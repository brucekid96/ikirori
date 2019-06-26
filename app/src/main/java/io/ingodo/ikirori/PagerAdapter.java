package io.ingodo.ikirori;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm , int NumberOfTabs) {

        super(fm);
        this.mNoOfTabs= NumberOfTabs;

    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Live live = new Live();
                return live;
            case 1:
                Past past= new Past();
                return past;
            case 2:
                Draft draft= new Draft();
                return draft;

            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
