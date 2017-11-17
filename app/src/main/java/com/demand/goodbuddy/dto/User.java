package com.demand.goodbuddy.dto;

/**
 * Created by Dev-0 on 2017-01-19.
 */

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private int gender;
    private String birth;
    private String avatar;
    private int level;
    private String fcmToken;
    private String deviceId;
    private String createdAt;
    private String updatedAt;
    private int companyId;
    private int adminId;

    private String accessToken;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(int id, String email, String password, String name, int gender, String birth, String avatar, int level,
                String fcmToken, String deviceId, String createdAt, String updatedAt, int companyId, int adminId,
                String accessToken) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.avatar = avatar;
        this.level = level;
        this.fcmToken = fcmToken;
        this.deviceId = deviceId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.companyId = companyId;
        this.adminId = adminId;
        this.accessToken = accessToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

}