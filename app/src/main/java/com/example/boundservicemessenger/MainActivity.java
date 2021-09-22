package com.example.boundservicemessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Messenger nMessenger = null;
    boolean isBind= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bindService(View view){
        Intent intent = new Intent(this,ExampleService.class);
        bindService(intent,mconnection, Context.BIND_AUTO_CREATE);


    }
    public void sayHello(View view){

        if (isBind) {

            String button_text;
            button_text = (String) ((Button) view).getText();
            if (button_text.equals("Say hello")) {
                Message message = Message.obtain(null,ExampleService.Job1,0,0,0);
                try {
                    nMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            } else if (button_text.equals("Again hello")) {

                Message message = Message.obtain(null,ExampleService.Job2,0,0,0);
                try {
                    nMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Bind it first", Toast.LENGTH_LONG).show();
        }



    }

    private ServiceConnection mconnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            nMessenger = new Messenger(iBinder);
            isBind = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            nMessenger=null;
            isBind=false;


        }
    };
    @Override
    protected void onStop() {
        unbindService(mconnection);
        isBind=false;
        nMessenger=null;
        super.onStop();
    }
}