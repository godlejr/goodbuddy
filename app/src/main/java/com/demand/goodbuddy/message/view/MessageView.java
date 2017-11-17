package com.demand.goodbuddy.message.view;

import android.view.View;

import com.demand.goodbuddy.dto.Message;

import java.util.List;

/**
 * Created by ㅇㅇ on 2017-05-02.
 */

public interface MessageView {
    void init();

    void showToolbarTitle(String message);
    void setToolbar();


    void showMessage(String message);

    void navigateToBack();

    void showEmptyView();

    void goneEmptyView();

    void setScrollViewChangeListener();

    void showProgressDialog();

    void goneProgressDialog();

    void clearMessageAdapter();

    void setMessageAdapterItem(List<Message> messages);

    void notifyItemRangeInserted(int messageSize, int newMessageSize);

    void notifyItemInserted();

    void setScrollDown();

    void setEditTextClear();
}
