package com.demand.goodbuddy.dto;

/**
 * Created by ㅇㅇ on 2017-04-27.
 */

public class DietSelfDiagnosis {
    private int id;
    private int userId; //
    private int dietDiagnosisCategoryId; //
    private int diagnosisCategoryId; //
    private String createdAt;
    private String updatedAt;


    public DietSelfDiagnosis() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DietSelfDiagnosis(int id, int userId, int dietDiagnosisCategoryId, int diagnosisCategoryId, String createdAt,
                             String updatedAt) {
        super();
        this.id = id;
        this.userId = userId;
        this.dietDiagnosisCategoryId = dietDiagnosisCategoryId;
        this.diagnosisCategoryId = diagnosisCategoryId;
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

    public int getDietDiagnosisCategoryId() {
        return dietDiagnosisCategoryId;
    }

    public void setDietDiagnosisCategoryId(int dietDiagnosisCategoryId) {
        this.dietDiagnosisCategoryId = dietDiagnosisCategoryId;
    }

    public int getDiagnosisCategoryId() {
        return diagnosisCategoryId;
    }

    public void setDiagnosisCategoryId(int diagnosisCategoryId) {
        this.diagnosisCategoryId = diagnosisCategoryId;
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