package com.demand.goodbuddy.dto;

/**
 * Created by ㅇㅇ on 2017-05-02.
 */

public class DietSelfDiagnosisScore {
    private int userId;
    private int score;
    private String createdAt;

    public DietSelfDiagnosisScore(int userId, int score, String createdAt) {
        super();
        this.userId = userId;
        this.score = score;
        this.createdAt = createdAt;
    }

    public DietSelfDiagnosisScore() {
        super();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}