package com.bliss.csc.sw_helper.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bliss.csc.sw_helper.R;

public class study_time extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
    }

    SharedPreferences pref = getSharedPreferences("sFile", Activity.MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();

    Button time1 = (Button) findViewById(R.id.time1);
    Button time2 = (Button) findViewById(R.id.time2);
    Button time3 = (Button) findViewById(R.id.time3);
    Button time4 = (Button) findViewById(R.id.time4);
    Button time5 = (Button) findViewById(R.id.time5);
    Button time6 = (Button) findViewById(R.id.time6);
    Button time7 = (Button) findViewById(R.id.time7);

    String strColor;

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time1:
                Toast.makeText(getApplicationContext(), " 예약 완료", Toast.LENGTH_LONG).show();
                editor.putString("on1", "#B363C6C6");
                strColor = pref.getString("on1", "#AA2162");
                time1.setBackgroundColor(Color.parseColor((strColor)));
                editor.commit();
                break;
            case R.id.time2:
                Toast.makeText(getApplicationContext(), " 예약 완료", Toast.LENGTH_LONG).show();
                editor.putString("on2", "#B363C6C6");
                strColor = pref.getString("on2", "#AA2162");
                time2.setBackgroundColor(Color.parseColor((strColor)));
                editor.commit();
                break;
            case R.id.time3:
                Toast.makeText(getApplicationContext(), " 예약 완료", Toast.LENGTH_LONG).show();
                editor.putString("on3", "#B363C6C6");
                strColor = pref.getString("on3", "#AA2162");
                time3.setBackgroundColor(Color.parseColor((strColor)));
                editor.commit();
                break;
            case R.id.time4:
                Toast.makeText(getApplicationContext(), " 예약 완료", Toast.LENGTH_LONG).show();
                editor.putString("on4", "#B363C6C6");
                strColor = pref.getString("on4", "#AA2162");
                time4.setBackgroundColor(Color.parseColor((strColor)));
                editor.commit();
                break;
            case R.id.time5:
                Toast.makeText(getApplicationContext(), " 예약 완료", Toast.LENGTH_LONG).show();
                editor.putString("on5", "#B363C6C6");
                strColor = pref.getString("on5", "#AA2162");
                time5.setBackgroundColor(Color.parseColor((strColor)));
                editor.commit();
                break;
            case R.id.time6:
                Toast.makeText(getApplicationContext(), " 예약 완료", Toast.LENGTH_LONG).show();
                editor.putString("on6", "#B363C6C6");
                strColor = pref.getString("on6", "#AA2162");
                time6.setBackgroundColor(Color.parseColor((strColor)));
                editor.commit();
                break;
            case R.id.time7:
                Toast.makeText(getApplicationContext(), " 예약 완료", Toast.LENGTH_LONG).show();
                editor.putString("on7", "#B363C6C6");
                strColor = pref.getString("on7", "#AA2162");
                time7.setBackgroundColor(Color.parseColor((strColor)));
                editor.commit();
                break;
        }
    }
}