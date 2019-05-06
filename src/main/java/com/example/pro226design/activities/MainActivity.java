package com.example.pro226design.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.ImageView;
import android.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.pro226design.R;
import com.example.pro226design.adapters.rvAdapter;
import com.example.pro226design.data_fetch.fetch_news;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements rvAdapter.OnItemClickListener {

    private Toolbar tool1;
    List <fetch_news> news1=new ArrayList<>();
    List <String> news2=new ArrayList<>();

    private RequestQueue mque;



    private RecyclerView rv1;
    private rvAdapter rva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tool1=(Toolbar)findViewById(R.id.tool1);



        setSupportActionBar(tool1);
        mque= Volley.newRequestQueue(this);




        rv1=(RecyclerView)findViewById(R.id.rv1);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(this) );

  //fetch news  data
LoadNews();








    }


    private void LoadNews(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                String url="https://newsapi.org/v2/top-headlines?country=us&apiKey=80edf69b5dac42b58ac07a1011c97410";

                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray ja=response.getJSONArray("articles");

                            for(int i=0;i<ja.length();i++){
                                JSONObject jo=ja.getJSONObject(i);
                                String title1=jo.getString("title");
                                String Desc=jo.getString("description");
                                String articleUrl=jo.getString("url");
                                String author1=jo.getString("author");



                                String imgr=jo.getString("urlToImage");



                                progressDialog.dismiss();
                                fetch_news f1=new fetch_news(title1,Desc,articleUrl,author1,imgr);
                                news1.add(f1);
                               // news2.add( title1 );



                            }


                             rva=new rvAdapter( getApplicationContext(),news1);
                            rv1.setAdapter(rva);
                            rva.setOnItemClickListener(MainActivity.this);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                mque.add(request);


            }
        }).start();


    }


    @Override
    public void OnItemClick(int position) {
        fetch_news Clickeditem=news1.get(position);
        Intent i=new Intent(MainActivity.this,NewsDetail.class);

        i.putExtra("title1",Clickeditem.getTitle1());
        i.putExtra("desc1",Clickeditem.getDesc());
        i.putExtra("arturl1",Clickeditem.getArticleUrl());
        i.putExtra("imgr",Clickeditem.getImgr());
        i.putExtra("author1",Clickeditem.getAuthor());

        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_item,menu);
        MenuItem menuItem=menu.findItem(R.id.searchv);

           SearchView searchView=(SearchView)MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("search news....");






        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                LoadSearchNews(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {


                return false;
            }
        });
        return true;
    }



    public  void LoadSearchNews(String s){
        final String test=s;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                String url="https://newsapi.org/v2/everything?q="+ test+"&from=2019-05-01&sortBy=popularity&apiKey=80edf69b5dac42b58ac07a1011c97410";

                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray ja=response.getJSONArray("articles");

                            for(int i=0;i<ja.length();i++){
                                JSONObject jo=ja.getJSONObject(i);
                                String title1=jo.getString("title");
                                String Desc=jo.getString("description");
                                String articleUrl=jo.getString("url");
                                String author1=jo.getString("author");



                                String imgr=jo.getString("urlToImage");



                                progressDialog.dismiss();
                                fetch_news f1=new fetch_news(title1,Desc,articleUrl,author1,imgr);
                                news1.add(f1);
                                // news2.add( title1 );



                            }


                            rva=new rvAdapter( getApplicationContext(),news1);
                            rv1.setAdapter(rva);
                            rva.setOnItemClickListener(MainActivity.this);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                mque.add(request);


            }
        }).start();


    }
}
