package com.example.user.qapp.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    private Context context;
    List<Question> questions;

    @BindView(R.id.view_pager)
    NonSwipeableViewPager mPager;

    @BindView(R.id.btCheck)
    Button btCheck;

    @OnClick(R.id.btCheck)
    public void next(View view) {
        onBtClick();
    }

    @BindView(R.id.tvResult)
    TextView tvResult;

    @BindView(R.id.tvExplain)
    TextView tvExplain;

    @BindView(R.id.llResult)
    LinearLayout llResult;


    PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        ButterKnife.bind(this);
        context = QuestionsActivity.this;

        questions = Singleton.getInstance().getQuestionList();
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                initView();
            }
        });
    }


    public void onBtClick() {
        if (btCheck.getText().toString().equalsIgnoreCase(getResources().getString(R.string.next))) {
            submit();
            return;
        }

        Question question = Singleton.getInstance().getQuestionList().get(mPager.getCurrentItem());
        String answer = Singleton.getInstance().getAnswer(mPager.getCurrentItem());

        llResult.setVisibility(View.VISIBLE);
        if (answer.equals(question.getAnswer())) {
            correctAnswer(question);
        } else {
            wrongAnswer(question);
        }
        tvExplain.setText(question.getExplanation());
        btCheck.setText(R.string.next);
        btCheck.setBackgroundColor(Color.BLUE);
    }

    private void correctAnswer(Question question) {
        tvResult.setText(R.string.correct_answer);
        tvResult.setTextColor(Color.GREEN);
    }

    private void wrongAnswer(Question question) {
        tvResult.setText(R.string.wrong_answer);
        tvResult.setTextColor(Color.RED);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            initView();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            Question question = questions.get(position);
            Fragment frag;
            if (question.getQuestionType().equals("1")) {
                frag = new SenCountQueFragment();
            } else if (question.getQuestionType().equals("1")) {
                frag = new MCQFragment();
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

    private void initView() {
        llResult.setVisibility(View.INVISIBLE);

        btCheck.setText(R.string.check);
        btCheck.setBackgroundColor(Color.MAGENTA);
    }

    public void submit() {
        if (mPager.getCurrentItem() == Singleton.getInstance().getQuestionList().size() - 1) {
            Toast.makeText(context, "Completed!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
    }
}



