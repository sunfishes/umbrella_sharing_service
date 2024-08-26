package com.example.registerloginexample;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsanList {
    @SerializedName("usan2")
    List<com.example.registerloginexample.UsanResponse> usan2;

    public List<com.example.registerloginexample.UsanResponse> getUsan2() {
        return usan2;
    }

    public void setUsan2(List<com.example.registerloginexample.UsanResponse> usan2) {
        this.usan2 = usan2;
    }
}