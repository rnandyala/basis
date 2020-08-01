package com.connector;

import androidx.lifecycle.LiveData;

import com.basis.financial.model.TestModel;

import java.util.ArrayList;

// interactor
public interface IRepoConnector {

    LiveData<ArrayList<TestModel.DataModel>> response();

    LiveData<String> failureResponse();

}
