package com.example.ctianju.ormlite.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ctianju on 2018/1/2.
 */
@DatabaseTable(tableName = "tab_article")
public class Article {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "title")
    private String title;
    //如何值传一个Article的Id，然后能够拿到Article对象，且内部的user属性直接赋值呢？
    //添加foreignAutoRefresh =true，这样；当调用queryForId时，拿到Article对象则直接携带了user
    @DatabaseField(canBeNull = true,foreign = true,columnName = "user_id" ,foreignAutoRefresh = true)
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Article [id=" + id + ", title=" + title + ", user=" + user.getName()
                + "]";
    }
}
