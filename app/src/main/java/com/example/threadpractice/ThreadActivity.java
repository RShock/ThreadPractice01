package com.example.threadpractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class ThreadActivity extends AppCompatActivity {

    Button startBtn;
    TextView num;

    private static final int DISABLE_BUTTON = 0;
    private static final int CHANGE_TEXT = 1;
    private static final int ENABLE_BUTTON = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        startBtn = findViewById(R.id.start);
        num = findViewById(R.id.num);

        Handler handler = new CountHandler(this);

        startBtn.setOnClickListener(view ->
            new Thread(() -> {
                handler.sendEmptyMessage(DISABLE_BUTTON);
                for(int times = 0; times <= 10; times++){
                    Message msg = new Message();
                    msg.obj = String.valueOf(times);
                    msg.what = CHANGE_TEXT;
                    handler.sendMessage(msg);
                    SystemClock.sleep(1000);
                }
                handler.sendEmptyMessage(ENABLE_BUTTON);
            }).start()
        );
    }

    public void handleMessage(@NonNull Message msg) {
        switch (msg.what){
            case DISABLE_BUTTON:
                startBtn.setEnabled(false);
                break;
            case ENABLE_BUTTON:
                startBtn.setEnabled(true);
                break;
            case CHANGE_TEXT:
                num.setText(msg.obj.toString());
                break;
        }
    }

    static class CountHandler extends Handler{
        private WeakReference<Activity> mActivity;

        CountHandler(Activity activity){
            super();
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            ThreadActivity activity = (ThreadActivity) mActivity.get();
            if (activity != null) {
                activity.handleMessage(msg);
            }
        }
    }
}