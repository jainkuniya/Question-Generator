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
import com.example.user.qapp.Singleton;
import com.example.user.qapp.fragments.MCQFragment;
import com.example.user.qapp.model.Question;
import com.example.user.qapp.utils.NonSwipeableViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionsActivity extends AppCompatActivity {

    int flag = 0;
    List<Question> questions;


    @BindView(R.id.view_pager)
    NonSwipeableViewPager mPager;

    @BindView(R.id.btn_next)
    Button btnNext;

    @OnClick(R.id.btn_next)
    public void next(View view) {
        if (flag == 0)
            changeButton();
        else {
            submit();
            btnNext.setText(R.string.submit);
            flag = 0;
        }
    }


    PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        ButterKnife.bind(this);

        questions = Singleton.getInstance().getQuestionList();
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    public void changeButton() {
        btnNext.setText(R.string.next);
        flag = 1;
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
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag;
            //    switch(position){
            //        case 0:
            frag = new MCQFragment();

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
            return questions.size();
        }
    }

    public void submit() {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
    }
}



