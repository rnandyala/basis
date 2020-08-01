package com.basis.financial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.basissample.TestBasisFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // adding fragment dynamically
        initFragment();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
    }

    private void initFragment() {

        if (getSupportFragmentManager().findFragmentByTag("basisFragment") == null) {
            Fragment mTestBasisFragment = TestBasisFragment.newInstance();
            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.mContainer, mTestBasisFragment, "basisFragment");
            mFragmentTransaction.commit();
        } else {
            Fragment mTestBasisFragment = getSupportFragmentManager().findFragmentByTag("basisFragment");
            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.mContainer, mTestBasisFragment);
            mFragmentTransaction.commit();


        }
    }


}