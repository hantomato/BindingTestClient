package com.nmj.bind.bindingtestclient;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aaa();
            }
        });
    }

    public void aaa() {
        Log.i("nmj7", "startService. my package : " + getPackageName());
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.nmj.bind.bindingtestserver",
                "com.nmj.bind.bindingtestserver.MyIntentService"));
        intent.setAction("com.nmj.bind.bindingtest.action.STOP_MUSIC");
        ComponentName res = startService(intent);   // 서버에서 exported false 라면.. without permission not exported from uid 10470
        Log.i("nmj7", "res : " + res);

    }
}
