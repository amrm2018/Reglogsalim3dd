package com.example.amr1.reglogsalim3dd;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amr1 on 2/23/2018.
 */

public class Send_data_post_found extends StringRequest {
    private static final String SEND_DATA_URL = "http://192.168.1.3/app_salim_3dd/create_post_found.php";
    private Map<String, String> MapData;

    public Send_data_post_found(String city, String day, String month,String year,String gender,String phone,String place_thecase ,
                                String info_thecase , Response.Listener<String> listener) {
        super(Method.POST, SEND_DATA_URL, listener, null);
        MapData = new HashMap<>();
        MapData.put("City", city);
        MapData.put("Day", day);
        MapData.put("Month", month);
        MapData.put("Year", year);
        MapData.put("Gender", gender);
        MapData.put("Phone", phone);
        MapData.put("Place_the_case", place_thecase);
        MapData.put("Info_the_case", info_thecase);

    }

    @Override
    public Map<String, String> getParams() {
        return MapData;
    }

}
