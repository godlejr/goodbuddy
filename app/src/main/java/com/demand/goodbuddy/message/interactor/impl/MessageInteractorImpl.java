package com.demand.goodbuddy.message.interactor.impl;

import com.demand.goodbuddy.dto.Message;
import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.LogFlag;
import com.demand.goodbuddy.message.interactor.MessageInteractor;
import com.demand.goodbuddy.message.presenter.MessagePresenter;
import com.demand.goodbuddy.repository.UserController;
import com.demand.goodbuddy.repository.interceptor.HeaderInterceptor;
import com.demand.goodbuddy.util.ErrorUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ㅇㅇ on 2017-05-02.
 */

public class MessageInteractorImpl implements MessageInteractor {
    private MessagePresenter messagePresenter;
    private User user;
    private String message;
    private List<Message> messages;
    private UserController userController;
    private static final Logger logger = LoggerFactory.getLogger(MessageInteractorImpl.class);

    public MessageInteractorImpl(MessagePresenter messagePresenter) {
        this.messagePresenter = messagePresenter;
        this.messages = new ArrayList<>();

    }

    @Override
    public void setUserController(User user) {
        String accessToken = user.getAccessToken();
        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setMessage(int userId, final Message message) {
        Call<Message> callInsertMessage = userController.insertMessage(userId, message);
        callInsertMessage.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful()) {
                    messagePresenter.onSuccessInsertMessage(message);
                } else {
                    messagePresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                log(t);
                messagePresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public void getMessageByUserIdAndOffset(int userId, int offset) {
        Call<List<Message>> callSelectMessageByUserIdAndOffset = userController.selectMessageByUserIdAndOffset(userId, offset);
        callSelectMessageByUserIdAndOffset.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    List<Message> messages = response.body();
                    messagePresenter.onSuccessGetMessageByUserIdAndOffset(messages);
                } else {
                    messagePresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                log(t);
                messagePresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public void setMessagesAddAll(List<Message> newMessages) {
        this.messages.addAll(0, newMessages);
    }

    @Override
    public void setMessageAdd(Message message) {
        this.messages.add(message);
    }

    private static void log(Throwable throwable) {
        StackTraceElement[] ste = throwable.getStackTrace();
        String className = ste[0].getClassName();
        String methodName = ste[0].getMethodName();
        int lineNumber = ste[0].getLineNumber();
        String fileName = ste[0].getFileName();

        if (LogFlag.printFlag) {
            if (logger.isInfoEnabled()) {
                logger.info("Exception: " + throwable.getMessage());
                logger.info(className + "." + methodName + " " + fileName + " " + lineNumber + " " + "line");
            }
        }
    }
}
