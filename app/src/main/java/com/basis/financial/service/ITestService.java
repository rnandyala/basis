package com.basis.financial.service;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ITestService {
    @GET("/fjaqJ")
    Observable<String> getTestData();
}
