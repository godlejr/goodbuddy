package com.demand.goodbuddy.message.presenter.impl;

import android.content.Context;

import com.demand.goodbuddy.dto.Message;
import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.MessageFlag;
import com.demand.goodbuddy.message.interactor.MessageInteractor;
import com.demand.goodbuddy.message.interactor.impl.MessageInteractorImpl;
import com.demand.goodbuddy.message.presenter.MessagePresenter;
import com.demand.goodbuddy.message.view.MessageView;
import com.demand.goodbuddy.util.APIErrorUtil;
import com.demand.goodbuddy.util.PreferenceUtil;

import java.util.List;

/**
 * Created by ㅇㅇ on 2017-05-02.
 */

public class MessagePresenterImpl implements MessagePresenter {
    private MessageInteractor messageInteractor;
    private PreferenceUtil preferenceUtil;
    private MessageView messageView;

    public MessagePresenterImpl(Context context) {
        this.messageView = (MessageView) context;
        this.messageInteractor = new MessageInteractorImpl(this);
        this.preferenceUtil = new PreferenceUtil(context);
    }


    @Override
    public void onCreate() {
        messageView.showProgressDialog();

        User user = preferenceUtil.getUserInfo();
        int userId = user.getId();
        messageInteractor.setUser(user);
        messageInteractor.setUserController(user);

        messageView.init();
        messageView.setToolbar();
        messageView.showToolbarTitle("관리자 메세지");
        messageView.setScrollViewChangeListener();

        int offset = 0;
        messageInteractor.getMessageByUserIdAndOffset(userId, offset);
    }


    @Override
    public void onClickSubmit(Message message) {
        String content = message.getContent();
        int contentSize = content.length();

        if (contentSize > 0) {
            User user = messageInteractor.getUser();
            int userId = user.getId();
            int adminId = user.getAdminId();

            message.setUserId(userId);
            message.setReceiverId(adminId);
            message.setReceiverCategoryId(MessageFlag.ADMIN);
            message.setNavigationId(userId);
            message.setNavigationCategoryId(1); // 변경
            message.setBehaviorId(MessageFlag.USER);

            messageInteractor.setMessage(userId, message);
        } else {
            messageView.showMessage("메세지를 입력해주세요");
        }
    }

    @Override
    public void onNetworkError(APIErrorUtil apiErrorUtil) {
        if (apiErrorUtil == null) {
            messageView.showMessage("네트워크 불안정합니다. 다시 시도하세요.");
        } else {
            messageView.showMessage(apiErrorUtil.message());
        }
    }

    @Override
    public void onScrollChange() {
        messageView.showProgressDialog();
        User user = messageInteractor.getUser();
        int userId = user.getId();

        List<Message> messages = messageInteractor.getMessages();
        int offset = messages.size();
        messageInteractor.getMessageByUserIdAndOffset(userId, offset);
    }


    @Override
    public void onSuccessGetMessageByUserIdAndOffset(List<Message> newMessages) {
        int newMessageSize = newMessages.size();


        List<Message> messages = messageInteractor.getMessages();
        int messageSize = messages.size();

        if (newMessageSize > 0) {
            if (messageSize == 0) {
                messageInteractor.setMessages(newMessages);
                messageView.clearMessageAdapter();
                messageView.setMessageAdapterItem(newMessages);
                messageView.setScrollDown();
            } else {
                messageInteractor.setMessagesAddAll(newMessages);
                messageView.notifyItemRangeInserted(0, newMessageSize);
            }
        } else {
            if (messageSize == 0) {
                messageView.showEmptyView();
            }
        }

        messageView.goneProgressDialog();
    }

    @Override
    public void onSuccessInsertMessage(Message message) {
        messageInteractor.setMessageAdd(message);
        List<Message> messages = messageInteractor.getMessages();
        int messageSize = messages.size();

        if (messageSize > 1) {
            messageView.notifyItemInserted();
            messageView.setScrollDown();
        } else {
            messageView.clearMessageAdapter();
            messageView.setMessageAdapterItem(messages);
            messageView.goneEmptyView();
        }

        messageView.setEditTextClear();
    }

    @Override
    public void onClickBack() {
        messageView.navigateToBack();
    }

    @Override
    public void onNewIntent(String message) {
        messageInteractor.setMessage(message);

        Message newMessage = new Message();
        newMessage.setContent(message);
        newMessage.setReceiverCategoryId(MessageFlag.USER);
        messageInteractor.setMessageAdd(newMessage);

        messageView.notifyItemInserted();
        messageView.setScrollDown();
    }
}
