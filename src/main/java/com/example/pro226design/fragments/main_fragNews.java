package com.example.pro226design.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.pro226design.R;
import com.example.pro226design.data_fetch.Likedata;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class main_fragNews extends Fragment {

    Toolbar tool1;
    ToggleButton likeArticle ;
    boolean toggle_like=true;


    ImageView imgdetail;
    String title1,arturl1,desc1,author,imgr;
    TextView t1,t2,t3;

    Likedata likedb;

    public main_fragNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_main_frag_news, container, false);

        tool1=(Toolbar)v.findViewById(R.id.tool1);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tool1);

        likeArticle=(ToggleButton)v.findViewById(R.id.likeArticle);

        imgdetail=(ImageView)v.findViewById(R.id.imgdetail);
        t1=(TextView)v.findViewById(R.id.sourcedetail);
        t2=(TextView)v.findViewById(R.id.titledetail);
        t3=(TextView)v.findViewById(R.id.textdetail);

        //database reference initializing
        likedb=new Likedata(getContext());


        if(getArguments()!=null){
            title1=getArguments().getString("title1");
            desc1=getArguments().getString("desc1");
            arturl1=getArguments().getString("arturl1");
            author=getArguments().getString("author1");
            imgr=getArguments().getString("imgr");
        }

        t1.setText("By "+author );
        t2.setText(title1);
        t3.setText(desc1);

        Glide.with(this).load(imgr).placeholder(R.drawable.p1).into(imgdetail);

        likeArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=title1;
                String artUrl=arturl1;

                //id which we get from push

                if(toggle_like) {
                    toggle_like=false;

                  boolean b=  likedb.insertData(title,artUrl);
                  if(b==true){
                      Toast.makeText(getContext(), "Article liked", Toast.LENGTH_SHORT).show();
                  }else{
                      Toast.makeText(getContext(), "Article not liked", Toast.LENGTH_SHORT).show();
                  }


                }else{
                    toggle_like=true;
                    //unliking article removing from sqlitedatabase
                    Integer deletedRows =likedb.deletData(title);

                    if(deletedRows>0){
                        Toast.makeText(getContext(), "Article Unliked", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Article is still liked", Toast.LENGTH_SHORT).show();
                    }




                }





            }
        });


        return v;
    }

}
