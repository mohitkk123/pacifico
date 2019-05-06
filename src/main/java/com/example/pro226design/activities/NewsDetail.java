package com.example.pro226design.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pro226design.R;
import com.example.pro226design.adapters.viewpagerAdapetr;
import com.example.pro226design.data_fetch.fetch_news;

import java.util.ArrayList;
import java.util.List;

public class NewsDetail extends AppCompatActivity {
    Toolbar tool1;
    List<fetch_news> news1=new ArrayList<>();

    ViewPager viewPager;

    ImageView imgdetail;
    TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
// setting the toolbar
        tool1=(Toolbar)findViewById(R.id.tool1);
         setSupportActionBar(tool1);

        Intent i= getIntent();
        String title1=i.getStringExtra("title1");
        String desc1=i.getStringExtra("desc1");
        String art1=i.getStringExtra("arturl1");
        String imgr=i.getStringExtra("imgr");
        String author1=i.getStringExtra("author1");

        imgdetail=(ImageView)findViewById(R.id.imgdetail);

         viewPager=(ViewPager)findViewById(R.id.viewP);
         news1.add(new fetch_news(title1,desc1,art1,author1,imgr ));

        viewpagerAdapetr adapetr=new viewpagerAdapetr(getSupportFragmentManager(),news1);

        viewPager.setAdapter(adapetr);

  /*      Intent i= getIntent();

        String title1=i.getStringExtra("title1");
        String desc1=i.getStringExtra("desc1");
        String art1=i.getStringExtra("arturl1");
        String imgr=i.getStringExtra("imgr");
        String author1=i.getStringExtra("author1");

        imgdetail=(ImageView)findViewById(R.id.imgdetail);
        t1=(TextView)findViewById(R.id.sourcedetail);
        t2=(TextView)findViewById(R.id.titledetail);
        t3=(TextView)findViewById(R.id.textdetail);

        t1.setText("By "+author1);
        t2.setText(title1);
        t3.setText(desc1);

        Glide.with(this).load(imgr).placeholder(R.drawable.p1).into(imgdetail);


        */





    }
}
