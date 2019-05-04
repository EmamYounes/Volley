package com.example.smartec.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static final String GET_API="http://api.themoviedb.org/3/movie/top_rated?api_key=3c3d662f3dfecdd191da7642c1b1e2af";
    ArrayList<Result> resultArrayList=new ArrayList<Result>();
    ListView listView;
    //ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list_view);
        getData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Result result=(Result) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),result.getTitle(),Toast.LENGTH_LONG).show();
            }
        });






    }


    public void getData(){

        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, GET_API, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray object=response.getJSONArray("results");
                    Result result;
                    for(int i=0;i<object.length();i++){
                        result=new Result();
                        result.setId(Integer.parseInt(object.getJSONObject(i).getString("id")));
                        result.setTitle(object.getJSONObject(i).getString("title"));
                        result.setOverview(object.getJSONObject(i).getString("overview"));
                        result.setVote_average(Float.parseFloat(object.getJSONObject(i).getString("vote_average")));
                        resultArrayList.add(result);
                    }
                    MyAdapter myAdapter=new MyAdapter(MainActivity.this,resultArrayList);
                    listView.setAdapter(myAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"exception error",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
            }
        });


        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}
