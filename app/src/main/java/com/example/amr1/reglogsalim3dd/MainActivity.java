package com.example.amr1.reglogsalim3dd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText Login_name , Login_password ;
    LinearLayout ly_login ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ly_login = findViewById(R.id.ly_login);

        Login_name=findViewById(R.id.Login_EIXT_Name);
        Login_password=findViewById(R.id.Login_EIXT_Password);

    }
    public void btn_Go_Registration (View v){
        startActivity(new Intent(getApplicationContext(),Registration.class));
    }

    public void btn_show_lyLogin (View v){
        ly_login.setVisibility(View.VISIBLE);
    }


    //Login
    public void Login_btn_Login (View v){

        String Log_in_name=Login_name.getText().toString().trim();
        String Log_in_password=Login_password.getText().toString().trim();

        if (!Log_in_name.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            Login_name.setError("اكتب الايميل صح");
        }
        else if (Log_in_password.equals("")){
            Login_password.setError("أكتب رقم سرى ");
            Login_password.setHint("أكتب رقم سري ");
        }
        else {
            Response.Listener<String>responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success){
                            Toast.makeText(MainActivity.this, "تم تسجيل الدخول", Toast.LENGTH_SHORT).show();
                            ly_login.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext(),Create_Post_Found.class));

                        }else {
                            Toast.makeText(MainActivity.this, "البيانات غير صحيحة", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };
            Send_Data_Login send_data =new Send_Data_Login(Log_in_name,Log_in_password,responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(send_data);


        }
    }



}
