package com.example.registerloginexample;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsanApi {
    @GET("/ksj/ex.php")
    Call<com.example.registerloginexample.UsanList> UsanGet(
    );
}