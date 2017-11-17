package com.demand.goodbuddy.message.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.dto.Message;
import com.demand.goodbuddy.flag.MessageFlag;
import com.demand.goodbuddy.message.presenter.MessagePresenter;
import com.demand.goodbuddy.util.CalculateDateUtil;

import java.util.List;

/**
 * Created by ㅇㅇ on 2017-05-02.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private MessagePresenter messagePresenter;
    private List<Message> messages;
    private Context context;
    private int layout;

    public MessageAdapter(MessagePresenter messagePresenter, List<Message> messages, Context context, int layout) {
        this.messagePresenter = messagePresenter;
        this.messages = messages;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageViewHolder messageViewHolder =  new MessageViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        String content = message.getContent();
        String createdAt = message.getCreatedAt();
        int receiverCategoryId = message.getReceiverCategoryId();


        if(receiverCategoryId == MessageFlag.ADMIN){
            holder.ll_message_send.setVisibility(View.VISIBLE);
            holder.tv_message_sendcontent.setText(content);

            if(createdAt != null) {
                holder.tv_message_senddate.setText(CalculateDateUtil.calculateDate(createdAt));
            } else {
                holder.tv_message_senddate.setText("방금");
            }
        }

        if(receiverCategoryId == MessageFlag.USER){
            holder.ll_message_receive.setVisibility(View.VISIBLE);
            holder.tv_message_receivecontent.setText(content);

            if(createdAt != null){
                holder.tv_message_receivedate.setText(CalculateDateUtil.calculateDate(createdAt));
            } else {
                holder.tv_message_receivedate.setText("방금");
            }
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_message_receivecontent;
        private TextView tv_message_receivedate;
        private TextView tv_message_sendcontent;
        private TextView tv_message_senddate;
        private LinearLayout ll_message_receive;
        private LinearLayout ll_message_send;


        public MessageViewHolder(View itemView) {
            super(itemView);

            tv_message_receivecontent = (TextView)itemView.findViewById(R.id.tv_message_receivecontent);
            tv_message_receivedate = (TextView)itemView.findViewById(R.id.tv_message_receivedate);
            tv_message_sendcontent = (TextView)itemView.findViewById(R.id.tv_message_sendcontent);
            tv_message_senddate = (TextView)itemView.findViewById(R.id.tv_message_senddate);

            ll_message_receive = (LinearLayout)itemView.findViewById(R.id.ll_message_receive);
            ll_message_send = (LinearLayout)itemView.findViewById(R.id.ll_message_send);
        }
    }
}
