package com.bliss.csc.sw_helper.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.bliss.csc.sw_helper.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.annotation.Nullable;

public class study_time extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_reservation);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showPop(View view) {
        switch(view.getId()){
            case R.id.btn:
                pop();
                break;
            case R.id.cancel_reservation:
                Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ReserveActivity.class);
                startActivity(intent);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void pop() {
        Calendar maxDate = Calendar.getInstance();
        Calendar minDate = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(study_time.this, year + "년 " + (month + 1) + "월 " + dayOfMonth + "일 ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), time_reservation.class);

                        intent.putExtra("year", year);
                        intent.putExtra("month", month);
                        intent.putExtra("dayOfMonth", dayOfMonth);

                        startActivity(intent);

                    }
                },
                2021, 5 , 1
        );
        minDate.set(2021, 5, 1);
        dialog.getDatePicker().setMinDate(minDate.getTime().getTime());
        maxDate.set(2021, 11, 31);
        dialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

        dialog.show();

    }

    private void mystartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}