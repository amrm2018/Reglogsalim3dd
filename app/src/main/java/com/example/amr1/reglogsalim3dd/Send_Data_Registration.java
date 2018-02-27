package com.example.amr1.reglogsalim3dd;

/**
 * Created by amr1 on 2/16/2018.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Send_Data_Registration extends StringRequest {

    private static final String SEND_DATA_URL = "http://192.168.1.3/app_salim_3dd/registration_3dd.php";
    private Map<String, String> MapData;

    public Send_Data_Registration(String name, String email, String password, Response.Listener<String> listener) {
        super(Method.POST, SEND_DATA_URL, listener, null);
        MapData = new HashMap<>();
        MapData.put("ename", name);
        MapData.put("email", email);
        MapData.put("epassword", password);
    }

    @Override
    public Map<String, String> getParams() {
        return MapData;
    }
}
