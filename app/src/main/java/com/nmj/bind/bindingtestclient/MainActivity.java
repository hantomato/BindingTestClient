package com.nmj.bind.bindingtestclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nmj.bind.bindingtestserver.INmgGame;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Messenger mService = null;
    INmgGame mBinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                btn1();
                break;
            case R.id.button2:
                btn2();
                break;
            case R.id.button3:
                btn3();
                break;
            case R.id.button4:
                btn4();
                break;
            case R.id.button5:
                btn5();
                break;
        }
    }

    public void btn1() {
        Log.i("nmj7", "startService. my package : " + getPackageName());
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.nmj.bind.bindingtestserver",
                "com.nmj.bind.bindingtestserver.service.MyIntentService"));
        intent.setAction("com.nmj.bind.bindingtest.action.STOP_MUSIC");
        ComponentName res = startService(intent);   // 서버에서 exported false 라면.. without permission not exported from uid 10470
        Log.i("nmj7", "res : " + res);

    }

    public void btn2() {

    }

    public void btn3() {

    }

    public void btn4() {
        // bind to NmgGameService
        Intent i = new Intent();
        i.setComponent(new ComponentName("com.nmj.bind.bindingtestserver", "com.nmj.bind.bindingtestserver.service.NmgGameService"));
        boolean bb = bindService(i, mConnection, Context.BIND_AUTO_CREATE);
        Log.i("nmj7", "bindService ret : " + bb);

    }

    public void btn5() {
        if (mBinder == null)
            return;

        try {
            int nCnt = mBinder.getGameCount();
            List<String> gameList = mBinder.getGameList();
            String text = String.format("총 %d개의 게임 서비스\n" + gameList.toString(), nCnt, gameList);
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.i("nmj7", "mConnection.onServiceConnected");
            mBinder = INmgGame.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.i("nmj7", "mConnection.onServiceDisconnected");
            mBinder = null;
        }
    };
}
