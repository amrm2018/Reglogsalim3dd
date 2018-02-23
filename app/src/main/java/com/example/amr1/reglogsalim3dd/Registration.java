package com.example.amr1.reglogsalim3dd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;


public class Registration extends AppCompatActivity {
    EditText name, email, password, password2;

   // Spinner Spinner_month ;
   // TextView TV_show_spinner;
    RadioButton rd_male ,rd_female ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = (EditText) findViewById(R.id.ETXT_Name);
        email = (EditText) findViewById(R.id.ETXT_Email);
        password = (EditText) findViewById(R.id.ETXT_Pass);
        password2 = (EditText) findViewById(R.id.ETXT_Pass2);

        //Spinner_month=findViewById(R.id.spinner_month);
       // TV_show_spinner=findViewById(R.id.tv_show_spinner);
        //TV_show_spinner.setText((CharSequence) Spinner_month);

        //استدعاء قائمة منسدلة
        final Spinner spinner=(Spinner)findViewById(R.id.spinner_month);
        //وضع القيم داخل array
        //نوع القيم كلمات وليس اعداد
        final List lisa=new ArrayList();
        lisa.add("2016");
        lisa.add("2015");
        lisa.add("2014");
        lisa.add("2013");
        //تحديد نوع spinner
        ArrayAdapter adap=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,lisa);
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adap);
        final TextView tv=(TextView) findViewById(R.id.tv_show_spinner);
       // final String reg=spinner.getSelectedItem().toString();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectspinner =adapterView.getItemAtPosition(i).toString();
                tv.setText(selectspinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        rd_male = findViewById(R.id.rdo_male);
        rd_female = findViewById(R.id.rdo_female);
    }


    //Send_Data_to_Serveries
    public void btn_Reg(View view) {

//        String valueSpinner = getT.toString();
//        TV_show_spinner.setText(valueSpinner);

        String Ename = name.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Pass = password.getText().toString().trim();
        String Pass2 = password2.getText().toString().trim();
//        String gen ;
//        if (rd_male.isChecked())
//            gen="male";
//        else
//            gen="female";

        if (Ename.equals("")) {
            name.setError("اكتب اسمك");
        }
        else if (!Email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            email.setError("اكتب الايميل صح");
        }
        else if (Pass.equals("")){
            password.setError("أكتب رقم سري كويس ");
        }
        else if (!Pass.equals(Pass2)) {
            Toast.makeText(Registration.this, "اكتب الرقم السري زي اللى كتبته فوق", Toast.LENGTH_LONG).show();
        }
        else{
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
