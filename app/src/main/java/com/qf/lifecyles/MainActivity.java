package com.qf.lifecyles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

/**
 * 实现一个计时器，每次退出后时间保持原来的时间，再次进入后继续计时
 */
public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    private long elapsedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.meter);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    /**
     * 按home键，暂时退出到后台，计时器停止
     */
    @Override
    protected void onPause() {
        super.onPause();
        // 计时器：当前时间 - 计时器启动时候的时间
        elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
        chronometer.stop();
    }

    /**
     * 重新从后台调出来程序
     */
    @Override
    protected void onResume() {
        super.onResume();
        chronometer.setBase(SystemClock.elapsedRealtime() - elapsedTime);
        chronometer.start();
    }
}