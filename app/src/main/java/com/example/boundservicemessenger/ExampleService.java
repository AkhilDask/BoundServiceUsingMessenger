package com.example.boundservicemessenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ExampleService extends Service {
    Messenger nMessenger= new Messenger(new Incoming()); //making the messenger an instance of handler
    // messenger is returned by the onBind, by using messenger object the client can send messagecommands to the service

    static final int Job1=1;
    static final int Job2=2;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "Service on binding", Toast.LENGTH_LONG).show();
        return nMessenger.getBinder();
    }

    class Incoming extends Handler
    {
        @Override
        public void handleMessage(Message msg) {  //client communicates by sending commands so use witch
            switch (msg.what)
            {
                case Job1:
                    Toast.makeText(getApplicationContext(), "Hello job 1", Toast.LENGTH_LONG).show();
                    break;
                case Job2:
                    Toast.makeText(getApplicationContext(), "hello from job2", Toast.LENGTH_LONG).show();
                    break;
                default:
                    super.handleMessage(msg);

            }




        }
    }
}
