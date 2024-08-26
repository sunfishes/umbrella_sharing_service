package com.example.registerloginexample;

import com.google.gson.annotations.SerializedName;

public class UsanResponse {
    @SerializedName("id")
    String id;
    @SerializedName("u_status1")
    String u_status1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getU_status1() {
        return u_status1;
    }

    public void setU_status1(String u_status1) {
        this.u_status1 = u_status1;
    }
}
