package com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApiCall {

    //http://run.mocky.io/v3/  90fea295-8b4d-4e7c-be1e-f0788188102f

    @GET("90fea295-8b4d-4e7c-be1e-f0788188102f")
    Call<DataModel> getData();

}
