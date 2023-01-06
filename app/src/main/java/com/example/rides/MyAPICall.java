package com.example.rides;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyAPICall {

@GET("vehicle/random_vehicle")
    Call<List<DataModel>> responseModel(@Query("size") int val) ;
}
