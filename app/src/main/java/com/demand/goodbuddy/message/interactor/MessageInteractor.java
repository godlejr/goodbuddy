package com.demand.goodbuddy.message.interactor;

import com.demand.goodbuddy.dto.Message;
import com.demand.goodbuddy.dto.User;

import java.util.List;

/**
 * Created by ㅇㅇ on 2017-05-02.
 */

public interface MessageInteractor {
    void setUserController(User user);

    User getUser();

    void setUser(User user);

    List<Message> getMessages();

    void setMessages(List<Message> messages);

    String getMessage();

    void setMessage(String message);

    void setMessage(int userId, Message message);

    void getMessageByUserIdAndOffset(int userId, int offset);

    void setMessagesAddAll(List<Message> newMessages);

    void setMessageAdd(Message message);
}
