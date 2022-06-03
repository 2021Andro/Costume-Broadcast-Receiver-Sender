package com.example.broadcastsender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvSenderMassageShow;

    private Button btnSender;

    private BroadcastReceiver myBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String massage = intent.getStringExtra("com.example.costumebroadcastreceiverdemo.EXTRA_TEXT");

            tvSenderMassageShow.setText("Sender Massage Show "+massage);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSenderMassageShow = findViewById(R.id.tvSenderMassageShow);
        btnSender = findViewById(R.id.btnSender);

        btnSender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("com.example.costumebroadcastreceiverdemo.EXAMPLE_ACTION");

                intent.putExtra("com.example.costumebroadcastreceiverdemo.EXTRA_TEXT", "Sender Massage");

                sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter("com.example.costumebroadcastreceiverdemo.EXAMPLE_ACTION");

        registerReceiver(myBroadcast, filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(myBroadcast);

    }
}