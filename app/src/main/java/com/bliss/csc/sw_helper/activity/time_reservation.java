package com.bliss.csc.sw_helper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.bliss.csc.sw_helper.Dateinfo;
import com.bliss.csc.sw_helper.PostInfo;
import com.bliss.csc.sw_helper.R;
import com.bliss.csc.sw_helper.Dateinfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class time_reservation extends BasicActivity {

    private static final String TAG = "time_reservation";

    private FirebaseUser user;

    private int count = 0;
    public int time = 0;

    private String dayOfMonth_db;
    private String year_db;
    private String month_db;
    private String time_db;
    private String room_number;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        findViewById(R.id.time1).setOnClickListener(onClickListener);
        findViewById(R.id.time2).setOnClickListener(onClickListener);
        findViewById(R.id.time3).setOnClickListener(onClickListener);
        findViewById(R.id.time4).setOnClickListener(onClickListener);
        findViewById(R.id.time5).setOnClickListener(onClickListener);
        findViewById(R.id.time6).setOnClickListener(onClickListener);
        findViewById(R.id.time7).setOnClickListener(onClickListener);

        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    Button.OnClickListener onClickListener = new Button.OnClickListener() {
        public void onClick(View v) {

            Intent intent = getIntent();

            int dayOfMonth = intent.getExtras().getInt("dayOfMonth");
            int year= intent.getExtras().getInt("year");
            int month = intent.getExtras().getInt("month");

            switch (v.getId()) {
                case R.id.time1:
                    if(count == 0) {
                        Toast.makeText(getApplicationContext(), "08 : 00 ~ 10 : 00 예약 완료", Toast.LENGTH_LONG).show();
                        time = 8;
                        count++;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "예약할 수 없습니다..", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.time2:
                    if(count == 0) {
                        Toast.makeText(getApplicationContext(), "10 : 00 ~ 12 : 00 예약 완료", Toast.LENGTH_LONG).show();
                        time = 10;
                        count++;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "이미 예약된 시간입니다.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.time3:
                    if(count == 0) {
                        Toast.makeText(getApplicationContext(), "12 : 00 ~ 14 : 00 예약 완료", Toast.LENGTH_LONG).show();
                        time = 12;
                        count++;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "이미 예약된 시간입니다.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.time4:
                    if(count == 0) {
                        Toast.makeText(getApplicationContext(), "14 : 00 ~ 16 : 00 예약 완료", Toast.LENGTH_LONG).show();
                        time = 14;
                        count++;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "이미 예약된 시간입니다.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.time5:
                    if(count == 0) {
                        Toast.makeText(getApplicationContext(), "16 : 00 ~ 18 : 00 예약 완료", Toast.LENGTH_LONG).show();
                        time = 16;
                        count++;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "이미 예약된 시간입니다.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.time6:
                    if(count == 0) {
                        Toast.makeText(getApplicationContext(), "18 : 00 ~ 20 : 00 예약 완료", Toast.LENGTH_LONG).show();
                        time = 18;
                        count++;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "이미 예약된 시간입니다.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.time7:
                    if(count == 0) {
                        Toast.makeText(getApplicationContext(), "20 : 00 ~ 22 : 00 예약 완료", Toast.LENGTH_LONG).show();
                        time = 20;
                        count++;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "이미 예약된 시간입니다.", Toast.LENGTH_LONG).show();
                    }
                    break;
            }

            dayOfMonth_db = String.valueOf(dayOfMonth);
            year_db = String.valueOf(year);
            month_db = String.valueOf(month + 1);
            time_db = String.valueOf(time);
            room_number = String.valueOf(room_number);

            Dateinfo dateinfo = new Dateinfo(year_db, month_db, dayOfMonth_db, time_db, room_number);
            storeUploader(dateinfo);

            mystartActivity(MainActivity.class);
        }
    };

    private void mystartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    private void storeUploader(Dateinfo date_info) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("studyroom").document(user.getUid()).set(date_info)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

}