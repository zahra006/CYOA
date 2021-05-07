package com.mobile.cyoa.Models;

public class Content {
    private int id;
    private String id_content, content, option;

    public Content() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_content() {
        return id_content;
    }

    public void setId_content(String id_content) {
        this.id_content = id_content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
