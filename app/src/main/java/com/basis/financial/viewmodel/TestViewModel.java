package com.basis.financial.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.basis.financial.model.TestModel;
import com.basis.financial.repository.BasistestRepository;
import com.connector.IRepoConnector;

import java.util.ArrayList;

public class TestViewModel extends AndroidViewModel implements IRepoConnector {

    BasistestRepository mBasistestRepository;

    public TestViewModel(@NonNull Application application) {
        super(application);
        mBasistestRepository = new BasistestRepository(this);
    }


    public void fetchData() {
        mBasistestRepository.fetchData();
    }


    @Override
    public LiveData<ArrayList<TestModel.DataModel>> response() {
        return mBasistestRepository.getBasisTest();
    }

    @Override
    public LiveData<String> failureResponse() {
        return mBasistestRepository.getBasisError();
    }
}





