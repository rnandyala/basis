package com.basis.financial.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {


    public static String getProgressOfCards(String mCurrentCardId, int mTotalCount) {
        String mTotalCount2 = Integer.toString(mTotalCount);
        return mCurrentCardId + "\\" + mTotalCount2;

    }



}
