package com.fredroid.mathsymbol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {


    private long delay = 3000;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Timer timer = new Timer();
        timer.schedule(task, delay);

    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            Intent in = new Intent().setClass(WelcomeActivity.this,
                    MainActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();
        }
        };
}



