package com.zeng.ezsh.wy.entity;


public class NewsShow {
    private Integer id;

    private String title;

    private String time;

    private String text;

    private Integer newsPlotType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getNewsPlotType() {
        return newsPlotType;
    }

    public void setNewsPlotType(Integer newsPlotType) {
        this.newsPlotType = newsPlotType;
    }
}