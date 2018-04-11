package com.bdwater.yyc;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by hegang on 18/4/10.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    PayFragment.Arguments payArguments;
    String[] titles;
    FragmentManager fragmentManager;
    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        fragmentManager = fm;
        // get title of tabs from 'src/values/string.xml'
        titles = context.getResources().getStringArray(R.array.tab_titles);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0: fragment = QueryFragment.newInstance(); break;
            case 1: fragment = PayFragment.newInstance(); break;
            case 2: fragment = AboutFragment.newInstance();break;
            default: fragment = null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public void setPayArguments(PayFragment.Arguments args) {
        this.payArguments = args;
    }

    @Override
    // To update fragment in ViewPager, we should override getItemPosition() method,
    // in this method, we call the fragment's public updating method.
    public int getItemPosition(Object object) {
        if (object instanceof PayFragment) {
            ((PayFragment) object).update(payArguments);
        }
        return super.getItemPosition(object);
    }
}
