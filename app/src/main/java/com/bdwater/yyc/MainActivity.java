package com.bdwater.yyc;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final Integer QUERY = 0;
    public static final Integer PAY = 1;
    public static final Integer ABOUT = 2;

    MainFragmentPagerAdapter adapter;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();
    }

    void initializeUI() {
        // use butter knife for @BindView
        ButterKnife.bind(this);

        // creates fragment view pager adapter
        adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setOffscreenPageLimit(3);

        // set adapter of viewpager
        viewPager.setAdapter(adapter);

        // creates relationship of tab layout and viewpager
        tabLayout.setupWithViewPager(viewPager);
    }

    // call by query fragment of interface of android js
    public void payByNumber(final String customerNo) {
        // caution: thread
        // switch to Pay fragment via main thread
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                PayFragment.Arguments args = new PayFragment.Arguments();
                args.customerNo = customerNo;
                adapter.setPayArguments(args);
                adapter.notifyDataSetChanged();

                viewPager.setCurrentItem(PAY, true);
            }
        });

    }
}
