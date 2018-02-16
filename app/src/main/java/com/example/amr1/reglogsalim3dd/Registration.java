package com.example.amr1.reglogsalim3dd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Registration extends AppCompatActivity {
    EditText name, email, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = (EditText) findViewById(R.id.ETXT_Name);
        email = (EditText) findViewById(R.id.ETXT_Email);
        password = (EditText) findViewById(R.id.ETXT_Pass);
        password2 = (EditText) findViewById(R.id.ETXT_Pass2);

    }

    //Send_Data_to_Serveries
    public void btn_Reg(View view) {


        String Ename = name.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Pass = password.getText().toString().trim();
        String Pass2 = password2.getText().toString().trim();

        if (!Pass.equals(Pass2)) {

            Toast.makeText(Registration.this, "اكتب الرقم السري بشكل صحيح", Toast.LENGTH_LONG).show();
        }else{

            Response.Listener<String> responseLisener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            Toast.makeText(Registration.this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Registration.this, "يوجد خطأ و لم يتم التسجيل", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            Send_Data_Registration send_Data = new Send_Data_Registration(Ename, Email, Pass, responseLisener);
            RequestQueue queue = Volley.newRequestQueue(Registration.this);
            queue.add(send_Data);

        }
    }
}
