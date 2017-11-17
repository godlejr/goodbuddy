package com.demand.goodbuddy.dto;

/**
 * Created by ㅇㅇ on 2017-08-10.
 */

public class Message {
    private int id;
    private int userId;
    private String content;
    private int receiverId;
    private int receiverCategoryId;
    private int navigationId;
    private int navigationCategoryId;
    private int behaviorId;
    private String createdAt;
    private String updatedAt;

    private String userName;

    public Message() {
        super();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getReceiverCategoryId() {
        return receiverCategoryId;
    }

    public void setReceiverCategoryId(int receiverCategoryId) {
        this.receiverCategoryId = receiverCategoryId;
    }

    public int getNavigationId() {
        return navigationId;
    }

    public void setNavigationId(int navigationId) {
        this.navigationId = navigationId;
    }

    public int getNavigationCategoryId() {
        return navigationCategoryId;
    }

    public void setNavigationCategoryId(int navigationCategoryId) {
        this.navigationCategoryId = navigationCategoryId;
    }

    public int getBehaviorId() {
        return behaviorId;
    }

    public void setBehaviorId(int behaviorId) {
        this.behaviorId = behaviorId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", userId=" + userId + ", content=" + content + ", receiverId=" + receiverId
                + ", receiverCategoryId=" + receiverCategoryId + ", navigationId=" + navigationId
                + ", navigationCategoryId=" + navigationCategoryId + ", behaviorId=" + behaviorId + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt + ", userName=" + userName + "]";
    }

}
