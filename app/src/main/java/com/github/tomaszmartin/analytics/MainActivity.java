package com.github.tomaszmartin.analytics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Analytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.analytics = Analytics.getInstance(this);
        findViewById(R.id.sendEventButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
        sendData();
    }

    private void sendData() {
       if (analytics != null) {
           analytics.sendScreen(MainActivity.class.getSimpleName());
           analytics.sendEvent("category", "action", "label", 5);
       }
    }

    private void log(String message) {
        Log.d(MainActivity.class.getSimpleName(), message);
    }

}
