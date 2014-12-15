package com.mt178.viewpager;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    private FragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager viewPager;
    private List<Fragment> data = null;

    private TextView tv_address;
    private TextView tv_chat;
    private TextView tv_me;

    private BadgeView badgeView;
    private LinearLayout ll_layout;

    private ImageView iv_line;
    private int mScreen1_3;
    private int mCurrentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        initTab();

    }

    private void initTab() {
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_chat = (TextView) findViewById(R.id.tv_chat);
        tv_me = (TextView) findViewById(R.id.tv_my);
        ll_layout = (LinearLayout) findViewById(R.id.ll_layout);
        iv_line = (ImageView) findViewById(R.id.iv_line);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 3;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_line.getLayoutParams();
        lp.width = mScreen1_3;
        iv_line.setLayoutParams(lp);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {

                Log.v("MainActivity", "position---->" + position + "positionOffset---->" + positionOffset + "mcurrent---->" + mCurrentPageIndex);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) iv_line.getLayoutParams();
                if (mCurrentPageIndex == 0 && position == 0) {//1-->2
                    layoutParams.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex *mScreen1_3);
                }else  if(mCurrentPageIndex==1&&position==0)//2-->1
                {
                    layoutParams.leftMargin= (int) (mCurrentPageIndex*mScreen1_3+(positionOffset-1)*mScreen1_3);
                }
                else  if(mCurrentPageIndex==1&&position==1)//2-->3
                {
                    layoutParams.leftMargin= (int) (mCurrentPageIndex*mScreen1_3+(positionOffset)*mScreen1_3);
                }
                else  if(mCurrentPageIndex==2&&position==1)//3-->2
                {
                    layoutParams.leftMargin= (int) (mCurrentPageIndex*mScreen1_3+(positionOffset-1)*mScreen1_3);
                }

                iv_line.setLayoutParams(layoutParams);
            }

            private void resetTextView() {
                tv_address.setTextColor(Color.BLACK);
                tv_chat.setTextColor(Color.BLACK);
                tv_me.setTextColor(Color.BLACK);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        if (badgeView != null) {
                            ll_layout.removeView(badgeView);
                        }
                        badgeView = new BadgeView(MainActivity.this);
                        badgeView.setBadgeCount(100);
                        ll_layout.addView(badgeView);
                        tv_address.setTextColor(Color.GREEN);
                        break;
                    case 1:
                        tv_chat.setTextColor(Color.GREEN);
                        break;
                    case 2:
                        tv_me.setTextColor(Color.GREEN);
                        break;
                }
                mCurrentPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        data = new ArrayList<Fragment>();
        Frag1 frag1 = new Frag1();
        data.add(frag1);
        Frag2 frag2 = new Frag2();
        data.add(frag2);
        Frag3 frag3 = new Frag3();
        data.add(frag3);

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return data.get(i);
            }

            @Override
            public int getCount() {
                return data.size();
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
    }

}
