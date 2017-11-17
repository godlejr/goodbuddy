package com.demand.goodbuddy.message.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.dto.Message;
import com.demand.goodbuddy.message.adapter.MessageAdapter;
import com.demand.goodbuddy.message.presenter.MessagePresenter;
import com.demand.goodbuddy.message.presenter.impl.MessagePresenterImpl;
import com.demand.goodbuddy.message.view.MessageView;

import java.util.List;

/**
 * Created by ㅇㅇ on 2017-05-02.
 */

public class MessageActivity extends Activity implements MessageView, View.OnClickListener, NestedScrollView.OnScrollChangeListener {
    private MessagePresenter messagePresenter;

    private NestedScrollView nsv_message;
    private RecyclerView rv_message;
    private EditText et_message;
    private Button btn_message;
    private MessageAdapter messageAdapter;
    private LinearLayout ll_message_emptyview;

    // toolbar
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView toolbarBack;
    private View decorView;
    private ProgressDialog progressDialog;
    private Handler progressDialogHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        this.progressDialog = new ProgressDialog(this);
        this.progressDialogHandler = new Handler();

        this.messagePresenter = new MessagePresenterImpl(this);
        this.messagePresenter.onCreate();

    }


    @Override
    public void init() {
        rv_message = (RecyclerView) findViewById(R.id.rv_message);
        et_message = (EditText) findViewById(R.id.et_message);
        btn_message = (Button) findViewById(R.id.btn_message);
        nsv_message = (NestedScrollView) findViewById(R.id.nsv_message);
        ll_message_emptyview = (LinearLayout) findViewById(R.id.ll_message_emptyview);

        btn_message.setOnClickListener(this);
    }

    @Override
    public void showToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }

    @Override
    public void setToolbar() {
        if (decorView == null) {
            decorView = this.getWindow().getDecorView();
        }

        toolbar = (Toolbar) decorView.findViewById(R.id.toolBar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarBack = (ImageView) toolbar.findViewById(R.id.toolbar_back);
        toolbarBack.setOnClickListener(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra("message");
        messagePresenter.onNewIntent(message);
    }

    @Override
    public void navigateToBack() {
        finish();
    }

    @Override
    public void showEmptyView() {
        ll_message_emptyview.setVisibility(View.VISIBLE);
    }

    @Override
    public void goneEmptyView() {
        ll_message_emptyview.setVisibility(View.GONE);
    }

    @Override
    public void setScrollViewChangeListener() {
        nsv_message.setOnScrollChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_message:
                String content = et_message.getText().toString();
                Message message = new Message();
                message.setContent(content);

                messagePresenter.onClickSubmit(message);
                break;

            case R.id.toolbar_back:
                messagePresenter.onClickBack();
                break;
        }
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (nestedScrollView.computeVerticalScrollOffset() == 0) {
            messagePresenter.onScrollChange();
        }
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progress_dialog);
    }

    @Override
    public void goneProgressDialog() {
        progressDialogHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }, 10);
    }

    @Override
    public void clearMessageAdapter() {
        if (messageAdapter != null) {
            messageAdapter = null;
        }
    }

    @Override
    public void setMessageAdapterItem(List<Message> messages) {
        messageAdapter = new MessageAdapter(messagePresenter, messages, this, R.layout.item_message);
        rv_message.setAdapter(messageAdapter);
        rv_message.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void notifyItemRangeInserted(int startPosition, int itemCount) {
        messageAdapter.notifyItemRangeInserted(startPosition, itemCount);
    }

    @Override
    public void notifyItemInserted() {
        messageAdapter.notifyItemInserted(messageAdapter.getItemCount());

    }

    @Override
    public void setScrollDown() {
        nsv_message.post(new Runnable() {
            @Override
            public void run() {
                nsv_message.fullScroll(NestedScrollView.FOCUS_DOWN);
            }
        });
    }

    @Override
    public void setEditTextClear() {
        et_message.setText("");
    }
}
