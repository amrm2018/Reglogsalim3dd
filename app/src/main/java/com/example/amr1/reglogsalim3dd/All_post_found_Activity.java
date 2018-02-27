package com.example.amr1.reglogsalim3dd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class All_post_found_Activity extends AppCompatActivity {

    RequestQueue requestQueue;
    String url = "http://192.168.1.3/app_salim_3dd/show_all_post_found.php";
    ArrayList<listitme> listpostfound =new ArrayList<listitme>();
    ListView listView ;
    TextView text_total ,text_email_user;
    GloablV gloablV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_post_found);

        listView = findViewById(R.id.list_view_post);

        text_total=findViewById(R.id.text_total);

        text_email_user =findViewById(R.id.tv_email_user);

        gloablV =(GloablV)getApplicationContext();

        assert  text_email_user !=null;
        text_email_user.setText("مرحبا : "  +gloablV.getEmail_user());

        requestQueue= Volley.newRequestQueue(this);
       JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url,
               new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {

                       try {

                           JSONArray jsonArray = response.getJSONArray("allpost_found");
                           text_total.setText(" مجموع الحالات التى تم رؤيتها" + jsonArray.length());
                           for (int i = 0; i < jsonArray.length(); i++) {
                               JSONObject res = jsonArray.getJSONObject(i);
                               String code_post_found = res.getString("code_post");
                               String date_time = res.getString("date_time_post");
                               String img = res.getString("img");
                               String city = res.getString("city");
                               String day = res.getString("day");
                               String month = res.getString("month");
                               String year = res.getString("year");
                               String gender = res.getString("gender");
                               String phone = res.getString("phone");
                               String place_thecase = res.getString("place_the_case");
                               String info_thecase = res.getString("info_the_case");
                               String email_user = res.getString("email_user");

                               //listPost.add(new listitme());
                           }

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                       ListDate();
                   }
               }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.e("Volley","Error");
           }
       });requestQueue.add(jsonObjectRequest);

    }

    public void  ListDate(){
     //   ListAdapter ad = new ListAdapter(ListMovis);
      //  listView.setAdapter(ad);
    }

    class  ListAdapter extends BaseAdapter{

        ArrayList<listitme> Listitme = new ArrayList<listitme>();

        ListAdapter(ArrayList<listitme>listitme){
            this.Listitme=listitme;
        }

        @Override
        public int getCount() {
            return Listitme.size();
        }

        @Override
        public Object getItem(int i) {
            return Listitme.get(i).palace_thecase;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View v1 =layoutInflater.inflate(R.layout.row_itme_found,null );

            TextView email_user=v1.findViewById(R.id.tv_email_userList);
            TextView code_post=v1.findViewById(R.id.tv_code_post);
            TextView date_time=v1.findViewById(R.id.tv_date_time);
            TextView city=v1.findViewById(R.id.tv_city);
            TextView day=v1.findViewById(R.id.tv_day);
            TextView month=v1.findViewById(R.id.tv_month);
            TextView year=v1.findViewById(R.id.tv_year);
            TextView gender=v1.findViewById(R.id.tv_gender);
            TextView phone=v1.findViewById(R.id.tv_phone1);
            TextView place=v1.findViewById(R.id.tv_place_thecase);
            TextView info=v1.findViewById(R.id.tv_info_thecase);
            ImageView img = v1.findViewById(R.id.img);

              email_user.setText(Listitme.get(i).email_user);
              code_post.setText(Listitme.get(i).code_p_found);
              date_time.setText(Listitme.get(i).date_time);
              city.setText(Listitme.get(i).city);
              day.setText(Listitme.get(i).day);
              month.setText(Listitme.get(i).month);
              year.setText(Listitme.get(i).year);
              gender.setText(Listitme.get(i).gender);
              phone.setText(Listitme.get(i).phone);
              place.setText(Listitme.get(i).palace_thecase);
              info.setText(Listitme.get(i).info_thecase);

            Picasso.with(getApplicationContext()).load("http://192.168.1.3/app_salim_3dd/image_app_salim_3dd/"+Listitme.get(i).img).into(img);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   Intent openPost= new Intent(getApplicationContext(),Post_Found_Activity.class);
                   openPost.putExtra("text_info",Listitme.get(i).info_thecase);
                   openPost.putExtra("text_code",Listitme.get(i).code_p_found);

                   startActivity(openPost);


                }
            });
            return v1;
        }
    }



}






