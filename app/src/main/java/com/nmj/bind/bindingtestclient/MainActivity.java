package com.nmj.bind.bindingtestclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nmj.bind.bindingtestserver.ICountService;

public class MainActivity extends AppCompatActivity {

    Messenger mService = null;
    ICountService mBinder = null;

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
//        bindService(new Intent(this, MessengerService.class), mConnection,
//                Context.BIND_AUTO_CREATE);

//        Intent i = new Intent("com.nmj.bind.bindingtestserver.service.CountService");
//        Intent i = new Intent(IRemoteService.class.getName());
        Intent i = new Intent().setAction("com.nmj.bind.bindingtestserver.service.CountService");
//        i.setPackage("com.nmj.bind.bindingtestserver");
        boolean bb = bindService(i, mConnection, Context.BIND_AUTO_CREATE);
        Log.i("nmj7", "bindService ret : " + bb);

    }

    public void btn3() {

    }

    public void btn4() {

    }

    public void btn5() {

    }


    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.i("nmj7", "mConnection.onServiceConnected");
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
//            mService = new Messenger(service);
//            IRemoteService.Stub.asInterface(service);

            mBinder = ICountService.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.i("nmj7", "mConnection.onServiceDisconnected");
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null;
        }
    };
}
