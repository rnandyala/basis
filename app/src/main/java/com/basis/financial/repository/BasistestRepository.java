package com.basis.financial.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.basis.financial.model.TestModel;
import com.basis.financial.service.APIClient;
import com.basis.financial.service.ITestService;
import com.connector.IRepoConnector;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BasistestRepository {
    IRepoConnector mIRepoConnector;
    private MutableLiveData<ArrayList<TestModel.DataModel>> mBasisTest = new MutableLiveData<>();
    private MutableLiveData<String> mFailureResponse = new MutableLiveData<>();
    public BasistestRepository(IRepoConnector mIRepoConnector) {
        this.mIRepoConnector = mIRepoConnector;
    }
    public void fetchData() {
        APIClient mApiClient = APIClient.getInstance();
        ITestService mITestService = mApiClient.getRetrofitInstance().create(ITestService.class);
        Observable<String> mTestServiceResult = mITestService.getTestData();
        Observable<TestModel> mSuc = mTestServiceResult.map(s -> {
            String mSuccessResponse = s.replaceAll("/", "").replaceAll("\\n", "");
            Gson mGson = APIClient.getInstance().getGsonInstance();
            return mGson.fromJson(mSuccessResponse, TestModel.class);
        });
        mSuc.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCaptureResponse);
    }


    public LiveData<ArrayList<TestModel.DataModel>> getBasisTest() {
        return mBasisTest;
    }


    public LiveData<String> getBasisError() {
        return mFailureResponse;
    }


    Observer<TestModel> mCaptureResponse = new Observer<TestModel>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(TestModel testModel) {
            ArrayList<TestModel.DataModel> mDataList = testModel.getData();
            mBasisTest.postValue(mDataList);
        }

        @Override
        public void onError(Throwable e) {
            e.getCause();
            mFailureResponse.postValue(e.getMessage());
            mIRepoConnector.failureResponse();
        }

        @Override
        public void onComplete() {
            mIRepoConnector.response();
        }
    };
}


