package com.mobile.cyoa.Models;

import org.w3c.dom.Text;

public class Book {
    private int id;
    private String title, cover;
    private Text synopsis;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Text getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(Text synopsis) {
        this.synopsis = synopsis;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
