package com.demand.goodbuddy.dto;

/**
 * Created by ㅇㅇ on 2017-05-02.
 */

public class ActiveMass {
    private int id;
    private int userId;
    private int data;
    private String createdAt;
    private String updatedAt;


    public ActiveMass() {
        super();
    }
    public ActiveMass(int id, int userId, int data, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.userId = userId;
        this.data = data;
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
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
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
