package com.example.ito5046_assignment3.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetrofitInterface {
    @Headers({"Accept: application/json"})
    @GET("leaderboard.json")
    Call<response> customSearch();
    @PUT("leaderboard.json")
    Call<response> updateData(@Body response res);
}