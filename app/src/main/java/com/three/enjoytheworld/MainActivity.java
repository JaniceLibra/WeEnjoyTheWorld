package com.three.enjoytheworld;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import fragment.AuthorFragment;
import fragment.FindFragment;
import fragment.HandpickFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private RadioButton rb_handpick;
    private RadioButton rb_find;
    private RadioButton rb_author;

    private List<Fragment> mFragments;
    private FindFragment mFindFragment;
    private HandpickFragment mHandpickFragment;
    private AuthorFragment mAuthorFragment;
    private FragmentManager mSupportFragmentManager;

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        rb_handpick = (RadioButton) findViewById(R.id.rb0);
        rb_find = (RadioButton) findViewById(R.id.rb1);
        rb_author = (RadioButton) findViewById(R.id.rb2);

        mFragments = new ArrayList<>();
        mHandpickFragment = new HandpickFragment();
        mFragments.add(mHandpickFragment);
        mFindFragment = new FindFragment();
        mFragments.add(mFindFragment);
        mAuthorFragment = new AuthorFragment();
        mFragments.add(mAuthorFragment);

        mSupportFragmentManager = getSupportFragmentManager();


        transaction = mSupportFragmentManager.beginTransaction();
        transaction.add(R.id.ll_fragment,mHandpickFragment).show(mHandpickFragment);
        transaction.add(R.id.ll_fragment,mFindFragment).hide(mFindFragment);
        transaction.add(R.id.ll_fragment,mAuthorFragment).hide(mAuthorFragment);
        transaction.commit();

        mRadioGroup.setOnCheckedChangeListener(this);
    }

    FragmentTransaction transaction;
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        transaction = mSupportFragmentManager.beginTransaction();
        switch (i){
            case R.id.rb0:
                transaction.show(mFragments.get(0));
                currentIndex = 0;
                break;
            case R.id.rb1:
                transaction.show(mFragments.get(1));
                currentIndex = 1;
                break;
            case R.id.rb2:
                transaction.show(mFragments.get(2));
                currentIndex = 2;
                break;
        }
        for (int j = 0; j < mFragments.size(); j++) {

            if (j==currentIndex){
                continue;
            }
            transaction.hide(mFragments.get(j));
        }

        transaction.commit();
    }
}
