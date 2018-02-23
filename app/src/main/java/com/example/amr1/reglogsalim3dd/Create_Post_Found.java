package com.example.amr1.reglogsalim3dd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Create_Post_Found extends AppCompatActivity {
    EditText ED_day,ED_year,ED_phone,ED_place_the_case , ED_info_the_case ;
    Spinner SP_month ,SP_city;
    RadioButton RD_male , RD_female ;
    TextView TV_show_month , TV_show_City;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post_found);

        ED_day=findViewById(R.id.ed_day);
        ED_year=findViewById(R.id.ed_year);
        ED_phone=findViewById(R.id.ed_phone);
        ED_place_the_case=findViewById(R.id.ed_place_the_case);
        ED_info_the_case=findViewById(R.id.ed_info_the_case);

        SP_month=findViewById(R.id.spinner_month);
        SP_city=findViewById(R.id.sp_city);

        RD_male=findViewById(R.id.rdo_male);
        RD_female=findViewById(R.id.rdo_female);

        TV_show_month=findViewById(R.id.tv_show_spinner_month);
        TV_show_City=findViewById(R.id.tv_show_spinner_city);

        String Sp_Month = SP_month.getSelectedItem().toString();
        TV_show_month.setText(Sp_Month);

        String Sp_City = SP_city.getSelectedItem().toString();
        TV_show_City.setText(Sp_City);

    }

    public void btn_create_post_found (View view){

        String City = TV_show_City.getText().toString().trim();
        String Day = ED_day.getText().toString().trim();
        String Month = TV_show_month.getText().toString();
        String Year = ED_year.getText().toString().trim();
        String Gender ;
        if (RD_male.isChecked())
            Gender="male";
        else
            Gender="female";
        String Phone = ED_phone.getText().toString().trim();
        String Place_thecase = ED_place_the_case.getText().toString();
        String Info_thecase = ED_info_the_case.getText().toString();

        Response.Listener<String> responseLisener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Toast.makeText(Create_Post_Found.this, "تم نشر الاعلان عن الحالة", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Create_Post_Found.this, "يوجد خطأ و لم يتم نشر الاعلان", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Send_data_post_found send_Data = new Send_data_post_found(City,Day, Month, Year,Gender,Phone,Place_thecase,Info_thecase, responseLisener);
        RequestQueue queue = Volley.newRequestQueue(Create_Post_Found.this);
        queue.add(send_Data);






    }//-------------------



}
