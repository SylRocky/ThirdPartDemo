package com.longge.thirdpartdemo.websocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.longge.thirdpartdemo.R;
import com.longge.thirdpartdemo.websocket.bean.ConnectResBean;
import com.longge.thirdpartdemo.websocket.bean.EmptyBean;
import com.longge.thirdpartdemo.websocket.bean.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebSocket2Activity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.btn_connect)
    Button mBtnConnect;
    @BindView(R.id.btn_enter)
    Button mBtnEnter;
    @BindView(R.id.btn_disConnect)
    Button mBtnDisConnect;
    @BindView(R.id.btn_leave)
    Button mBtnLeave;
    @BindView(R.id.et_sendContent)
    EditText mEtSendContent;
    @BindView(R.id.btn_send)
    Button mBtnSend;

    private WebSocketHelper.WebSocketListener mConnectListener = new WebSocketHelper.WebSocketListener<ConnectResBean>() {

        @Override
        public void onResponse(Response<ConnectResBean> text) {
            Log.d(TAG, "connect: " + text.toString());
        }

        @Override
        public void onFailed(int code, Throwable throwable) {

        }
    };

    private WebSocketHelper.WebSocketListener mEnterListener = new WebSocketHelper.WebSocketListener<EmptyBean>() {

        @Override
        public void onResponse(Response<EmptyBean> text) {
            Log.d(TAG, "enter: " + text.toString());
        }

        @Override
        public void onFailed(int code, Throwable throwable) {

        }
    };
    private WebSocketHelper.WebSocketListener mLeaveListener = new WebSocketHelper.WebSocketListener<String>() {

        @Override
        public void onResponse(Response<String> text) {
            Log.d(TAG, "leave: " + text.toString());
        }

        @Override
        public void onFailed(int code, Throwable throwable) {

        }
    };

    private WebSocketHelper.WebSocketListener mAudienceListener = new WebSocketHelper.WebSocketListener<String>() {

        @Override
        public void onResponse(Response<String> text) {
            Log.d(TAG, "audience: " + text.toString());
        }

        @Override
        public void onFailed(int code, Throwable throwable) {

        }
    };
    private WebSocketHelper.WebSocketListener mNewAudienceListener = new WebSocketHelper.WebSocketListener<String>() {

        @Override
        public void onResponse(Response<String> text) {
            Log.d(TAG, "newAudience: " + text.toString());
        }

        @Override
        public void onFailed(int code, Throwable throwable) {

        }
    };
    private WebSocketHelper.WebSocketListener mMsgListener = new WebSocketHelper.WebSocketListener<String>() {

        @Override
        public void onResponse(Response<String> text) {
            Log.d(TAG, "msg: " + text.toString());
        }

        @Override
        public void onFailed(int code, Throwable throwable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket2);
        ButterKnife.bind(this);
        WebSocketHelper.getInstance().addWebSocketListener(RequestType.CONNECT, mConnectListener);
        WebSocketHelper.getInstance().addWebSocketListener(RequestType.WCST_ENTER, mEnterListener);
        WebSocketHelper.getInstance().addWebSocketListener(RequestType.WCST_LEAVE, mLeaveListener);
        WebSocketHelper.getInstance().addWebSocketListener(RequestType.WCST_AUDIENCES, mAudienceListener);
        WebSocketHelper.getInstance().addWebSocketListener(RequestType.WCST_NEW_AUDIENCE, mNewAudienceListener);
        WebSocketHelper.getInstance().addWebSocketListener(RequestType.WCST_MSG, mMsgListener);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebSocketHelper.getInstance().removeWebSocketListener(RequestType.CONNECT, mEnterListener);
        WebSocketHelper.getInstance().removeWebSocketListener(RequestType.WCST_ENTER, mConnectListener);
        WebSocketHelper.getInstance().removeWebSocketListener(RequestType.WCST_LEAVE, mLeaveListener);
        WebSocketHelper.getInstance().removeWebSocketListener(RequestType.WCST_AUDIENCES, mAudienceListener);
        WebSocketHelper.getInstance().removeWebSocketListener(RequestType.WCST_NEW_AUDIENCE, mNewAudienceListener);
        WebSocketHelper.getInstance().removeWebSocketListener(RequestType.WCST_MSG, mMsgListener);
    }

    @OnClick({R.id.btn_connect, R.id.btn_enter, R.id.btn_disConnect, R.id.btn_leave, R.id.btn_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_connect:
                connect();
                break;
            case R.id.btn_enter:
                enter();
                break;
            case R.id.btn_disConnect:
                disConnect();
                break;
            case R.id.btn_leave:
                leave();
                break;
            case R.id.btn_send:
                send(mEtSendContent.getText().toString());
                break;
        }
    }

    private void send(String content) {
        WebSocketHelper.getInstance().send("1", "1", content);
    }

    private void disConnect() {
        WebSocketHelper.getInstance().disConnect("1");
    }

    private void leave() {
        WebSocketHelper.getInstance().leave("1", "1");
    }

    private void enter() {
        WebSocketHelper.getInstance().enter("1", "1");
    }

    private void connect() {
//        WebSocketHelper.getInstance().connect("1", "9f98a7bb-aa81-4315-ad4d-f8fdde89f5b0", "");
        WebSocketHelper.getInstance().connect("1", "1", "");
    }

}
