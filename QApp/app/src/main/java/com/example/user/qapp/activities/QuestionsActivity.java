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
import com.example.user.qapp.Singleton;
import com.example.user.qapp.fragments.MCQFragment;
import com.example.user.qapp.fragments.SenCountQueFragment;
import com.example.user.qapp.model.Question;
import com.example.user.qapp.utils.NonSwipeableViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionsActivity extends AppCompatActivity {

    List<Question> questions;

    @BindView(R.id.view_pager)
    NonSwipeableViewPager mPager;

    @BindView(R.id.btCheck)
    Button btCheck;

    @OnClick(R.id.btCheck)
    public void next(View view) {
        btCheck.setText(R.string.submit);
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
        btCheck.setText(R.string.next);
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
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            Question question = questions.get(position);
            Fragment frag;
            if (question.getQuestionType().equals("1")) {
                frag = new SenCountQueFragment();
            } else {
                frag = new MCQFragment();
            }
            frag.setArguments(bundle);
            return frag;

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



