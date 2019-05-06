package com.example.pro226design.data_fetch;

public class fetch_news {

    String titlea ;
    String desc;
    String arturl;
    String author;



    String imgr1;

    public fetch_news(String title1, String desc, String articleUrl, String author, String imgr) {
        this.titlea = title1;
        this.desc = desc;
        this.arturl = articleUrl;
        this.author = author;
        this.imgr1 = imgr;
    }

    public String getTitle1() {
        return titlea;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getArticleUrl() {
        return arturl;
    }

    public String getAuthor() {
        return author;
    }

    public String getImgr() {
        return imgr1;
    }

}
