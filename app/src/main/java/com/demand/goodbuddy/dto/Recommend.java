package com.demand.goodbuddy.dto;

import java.io.Serializable;

/**
 * Created by ㅇㅇ on 2017-10-30.
 */

public class Recommend implements Serializable{
    private String title;
    private String subTitle;
    private String content;

    public Recommend() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
