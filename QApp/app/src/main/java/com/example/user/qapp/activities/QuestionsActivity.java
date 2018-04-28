package com.example.user.qapp.activities;

import android.content.Intent;
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
import com.example.user.qapp.model.Question;
import com.example.user.qapp.utils.NonSwipeableViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionsActivity extends AppCompatActivity {

  int NUM_PAGES = 5;
  int flag=0;
  ArrayList<Question> questions;


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
        Intent intent = getIntent();
        questions=(ArrayList<Question>)intent.getSerializableExtra("questions");
        //NUM_PAGES=questions.size();
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }
    public void changeButton(){
        btnNext.setText("Next");
        flag=1;
    }

    @OnClick(R.id.btn_next)
    public void next(View view) {
        if(flag==0)
        changeButton();
        else{
            submit();
            btnNext.setText("Submit");
            flag=0;
            }



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
            Bundle bundle=new Bundle();
            bundle.putSerializable("question",questions);
            Fragment frag;
        //    switch(position){
        //        case 0:
                    frag=new MCQFragment();
                    frag.setArguments(bundle);
                    return frag;
          /*      case 1:

                    frag=new TrueFalseFragment();
                    frag.setArguments(bundle);
                    return frag;
                case 2:

                    frag=new MCQFragment();
                    frag.setArguments(bundle);
                    return frag;
                case 3:

                    frag=new MCQFragment();
                    frag.setArguments(bundle);
                    return frag;
                case 4:

                    frag=new MCQFragment();
                    frag.setArguments(bundle);
                    return frag;
                default:

                    frag=new MCQFragment();
                    frag.setArguments(bundle);
                    return frag;

            }*/

        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    public void submit(){
        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
    }
}



