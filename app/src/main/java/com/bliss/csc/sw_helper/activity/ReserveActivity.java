package com.bliss.csc.sw_helper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bliss.csc.sw_helper.R;

public class ReserveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        Button bookButton1 =(Button) findViewById(R.id.bookButton1);
        bookButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), study_time.class);
                startActivity(intent);
            }
        });
        Button bookButton2 =(Button) findViewById(R.id.bookButton2);
        bookButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), study_time.class);
                startActivity(intent);
            }
        });
    }
}
