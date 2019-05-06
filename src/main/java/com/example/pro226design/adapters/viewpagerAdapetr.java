package com.example.pro226design.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.pro226design.data_fetch.fetch_news;
import com.example.pro226design.fragments.main_fragNews;
import com.example.pro226design.fragments.webV;

import java.util.List;

public class viewpagerAdapetr extends FragmentStatePagerAdapter {

    List<fetch_news> news;
    public viewpagerAdapetr(FragmentManager fm) {
        super(fm);
    }

    public viewpagerAdapetr(FragmentManager fm, List<fetch_news> news) {
        super(fm);
        this.news = news;
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0){
            main_fragNews fm=new main_fragNews();
            Bundle bundle=new Bundle();
            bundle.putString("title1",news.get(i).getTitle1());
            bundle.putString("desc1",news.get(i).getDesc());
            bundle.putString("arturl1",news.get(i).getArticleUrl());
            bundle.putString("imgr",news.get(i).getImgr());
            bundle.putString("author1",news.get(i).getAuthor());
            fm.setArguments(bundle);
            return fm;

        }
        else {
            webV weburl=new webV();
            Bundle bundle1=new Bundle();
            bundle1.putString("arturl1",news.get(0).getArticleUrl());
            weburl.setArguments(bundle1);
            return weburl;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
