package com.example.user.qapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.user.qapp.R;
import com.example.user.qapp.fragments.MCQFragment;
import com.example.user.qapp.utils.NonSwipeableViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionsActivity extends AppCompatActivity {

  int NUM_PAGES = 5;


    @BindView(R.id.view_pager)
    NonSwipeableViewPager mPager;
    @BindView(R.id.btn_next)
    Button btnNext;


     PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        ButterKnife.bind(this);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @OnClick(R.id.btn_next)
    public void next(View view) {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {

            super.onBackPressed();
        } else {

            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return new MCQFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}



