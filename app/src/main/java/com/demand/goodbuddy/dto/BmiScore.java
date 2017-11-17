package com.demand.goodbuddy.dto;

/**
 * Created by ㅇㅇ on 2017-05-02.
 */

public class BmiScore {
    private int userId;
    private float score;
    private String createdAt;

    public BmiScore(int userId, float score, String createdAt) {
        super();
        this.userId = userId;
        this.score = score;
        this.createdAt = createdAt;
    }

    public BmiScore() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}