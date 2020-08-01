package com.basis.financial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.basis.financial.R;
import com.basis.financial.model.TestModel;
import com.basis.financial.utils.Utils;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class SwipeStackAdapter extends BaseAdapter {
    ArrayList<TestModel.DataModel> mListOfData;
    Context mContext;
    ViewGroup mViewGroup;

    // returns total count
    @Override
    public int getCount() {
        return mListOfData.size();
    }

    @Override
    public Object getItem(int i) {
        return mListOfData.get(i);
    }

    // give position of the current visible view
    @Override
    public long getItemId(int i) {
        return i;
    }


    // infalte card view swipestack
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        this.mViewGroup = viewGroup;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.swipe_gesture_layout, viewGroup, false);
        }

        MaterialTextView mDataTv = view.findViewById(R.id.mTestData);
        MaterialTextView mProgressTv = view.findViewById(R.id.mProgress_indicator);
        mDataTv.setText(mListOfData.get(i).getText());
        mProgressTv.setText(Utils.getProgressOfCards(mListOfData.get(i).getId(), mListOfData.size()));
        return view;
    }

    public SwipeStackAdapter(Context mContext, ArrayList<TestModel.DataModel> mListOfData) {
        this.mContext = mContext;
        this.mListOfData = mListOfData;
    }
}
