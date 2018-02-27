package com.example.amr1.reglogsalim3dd;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


import static com.android.volley.Request.Method.POST;

/**
 * Created by amr1 on 2/16/2018.
 */

class Send_Data_Login extends StringRequest  {



    private static final String SEND_DATA_URL = "http://192.168.1.3/app_salim_3dd/login_3dd.php";
    private Map<String,String> MapData;

    public Send_Data_Login(String Login_name, String Login_password, Response.Listener<String> listener) {
        super(Method.POST, SEND_DATA_URL, listener, null);
        MapData = new HashMap<>();
        MapData.put("Login_name", Login_name);
        MapData.put("Login_password", Login_password);
    }

    @Override
    public Map<String, String> getParams() {
        return MapData;
    }
}
