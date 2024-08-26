package com.example.registerloginexample;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://ci2022kingsman.dongyangmirae.kr/ksj/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String id, String psword, String name, String email, int phone,
                           Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("id",id);
        map.put("psword",psword);
        map.put("name", name);
        map.put("email", email);
        map.put("phone", phone + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
