package com.demand.goodbuddy.message.presenter;

import com.demand.goodbuddy.dto.Message;
import com.demand.goodbuddy.util.APIErrorUtil;

import java.util.List;

/**
 * Created by ㅇㅇ on 2017-05-02.
 */

public interface MessagePresenter {
    void onCreate();

    void onClickSubmit(Message message);

    void onNetworkError(APIErrorUtil apiErrorUtil);

    void onScrollChange();



    void onSuccessGetMessageByUserIdAndOffset(List<Message> messages);

    void onSuccessInsertMessage(Message message);

    void onClickBack();

    void onNewIntent(String message);
}
