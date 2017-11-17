package com.demand.goodbuddy.dto;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public class BloodPressure {
    private int id;
    private int userId;
    private int maxData;
    private int minData;
    private String createdAt;
    private String updatedAt;

    public BloodPressure() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BloodPressure(int id, int userId, int maxData, int minData, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.userId = userId;
        this.maxData = maxData;
        this.minData = minData;
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

    public int getMaxData() {
        return maxData;
    }

    public void setMaxData(int maxData) {
        this.maxData = maxData;
    }

    public int getMinData() {
        return minData;
    }

    public void setMinData(int minData) {
        this.minData = minData;
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
