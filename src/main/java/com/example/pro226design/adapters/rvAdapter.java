package com.example.pro226design.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pro226design.R;
import com.example.pro226design.activities.Login_act;
import com.example.pro226design.activities.MainActivity;
import com.example.pro226design.data_fetch.fetch_news;

import java.util.ArrayList;
import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.MyViewHolder> {
 private List<fetch_news> list1;
 private List<String> list2=new ArrayList<>();
 Context mcontext;
 private OnItemClickListener mListener;//it will be our main activity listener


 public interface OnItemClickListener {
     void OnItemClick(int position);


    }


    public void setOnItemClickListener(OnItemClickListener listener){
     mListener=listener;
    }

    public rvAdapter(Context context,List<fetch_news> listnews ) {

        this.list1=listnews;
        mcontext=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardrv,viewGroup,false);
        return new MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        //anim binding
        myViewHolder.linearLayout.setAnimation(AnimationUtils.loadAnimation( mcontext,R.anim.fade_anim));
        Log.d("msg",""+list1.get(i).getTitle1());
        myViewHolder.t1.setText(list1.get(i).getTitle1()  );
    Glide.with(mcontext).load(list1.get(i).getImgr()).placeholder(R.drawable.p1).into(myViewHolder.img1);








    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout newsClickLinear;

        TextView t1;
        LinearLayout linearLayout;
        ImageView img1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.t1);
            img1=(ImageView) itemView.findViewById(R.id.img1);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linear_main);
            newsClickLinear=(LinearLayout)itemView.findViewById(R.id.newsLinear);


            //catching the click on item

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
