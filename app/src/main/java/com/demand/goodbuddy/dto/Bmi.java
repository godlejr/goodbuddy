package com.demand.goodbuddy.dto;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public class Bmi {
    private int id;
    private int userId;
    private float height;
    private float weight;
    private String createdAt;
    private String updatedAt;

    public Bmi() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Bmi(int id, int userId, float height, float weight, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.userId = userId;
        this.height = height;
        this.weight = weight;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}