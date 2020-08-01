package com.example.basissample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.basis.financial.R;
import com.basis.financial.adapter.SwipeStackAdapter;
import com.basis.financial.model.TestModel;
import com.basis.financial.viewmodel.TestViewModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestBasisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestBasisFragment extends Fragment {

    private ProgressBar mProgressbar;
    private TextView mResponseTextView;
    private TestViewModel mTestViewModel;
    private SwipeStackAdapter mSwipeStackAdapter;
    private SwipeStack mSwipeStack;
    private MaterialTextView mCardsCompleted;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuReset:
                mSwipeStack.resetStack();
                return true;
        }


        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuInflater infalter = getActivity().getMenuInflater();
        infalter.inflate(R.menu.menu, menu);

    }

    public TestBasisFragment() {
        // Required empty public constructor
    }

    public static TestBasisFragment newInstance() {
        return new TestBasisFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mTestViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_test_basis, container, false);
        mProgressbar = mView.findViewById(R.id.mProgress_indicator);

        mSwipeStack = mView.findViewById(R.id.swipeStack);
        mCardsCompleted = mView.findViewById(R.id.cards_completed);
        mProgressbar.setVisibility(View.VISIBLE);
        if(isNetworkAvailable()) {
            mTestViewModel.fetchData();
        }
        else{

            Toast.makeText(this.getContext(),"please connect to internet and reopen the application",Toast.LENGTH_SHORT).show();
        }
        return mView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTestViewModel.response().observe(
                this, new Observer<ArrayList<TestModel.DataModel>>() {
                    @Override
                    public void onChanged(ArrayList<TestModel.DataModel> dataModels) {
                        mProgressbar.setVisibility(View.GONE);
                        mSwipeStackAdapter = new SwipeStackAdapter(getContext(), dataModels);
                        mSwipeStack.setAdapter(mSwipeStackAdapter);
                        mSwipeStack.resetStack();

                    }
                });
        mTestViewModel.failureResponse().observe(
                this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        mResponseTextView.setText(s);
                        mProgressbar.setVisibility(View.GONE);
                        mCardsCompleted.setVisibility(View.VISIBLE);
                    }
                });


    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}