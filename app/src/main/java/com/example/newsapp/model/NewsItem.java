package com.example.newsapp.model;

import java.io.Serializable;
import java.util.List;

public class NewsItem implements Serializable {
    private final String id;
    private final String title;
    private final String snippet;
    private final int imageRes;           // drawable resource ID
    private final List<NewsItem> related; // for detail screen later

    public NewsItem(String id, String title, String snippet, int imageRes, List<NewsItem> related) {
        this.id       = id;
        this.title    = title;
        this.snippet  = snippet;
        this.imageRes = imageRes;
        this.related  = related;
    }

    // getters
    public String getId()             { return id; }
    public String getTitle()          { return title; }
    public String getSnippet()        { return snippet; }
    public int    getImageRes()       { return imageRes; }
    public List<NewsItem> getRelated(){ return related; }
}
