package com.mobile.cyoa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {

    private Button btnSaveState, btnSynopsis, btnOptionA, btnOptionB;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        init();
    }

    private void init() {
        btnSaveState = findViewById(R.id.btnReadSavestate);
        btnSynopsis = findViewById(R.id.btnReadSynopsis);
        btnOptionA = findViewById(R.id.btnOptionA);
        btnOptionB = findViewById(R.id.btnOptionB);
        text = findViewById(R.id.txtStory);

        btnSaveState.setOnClickListener(v -> {

        });

        btnSynopsis.setOnClickListener(v -> {

        });

        btnOptionA.setOnClickListener(v -> {

        });

        btnOptionB.setOnClickListener(v -> {

        });
    }
}