package com.bliss.csc.sw_helper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bliss.csc.sw_helper.R;

public class BoardListActivity extends AppCompatActivity {

    LinearLayout free, team,room;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_list);

        free = findViewById(R.id.free_board);
        team = findViewById(R.id.team_board);
        room = findViewById(R.id.room_board);

        free.setOnClickListener((view -> {
            Intent intent = new Intent(getApplicationContext(), BoardFreeActivity.class);
            startActivity(intent);
        }));

        team.setOnClickListener((view -> {
            Intent intent = new Intent(getApplicationContext(), BoardTeamActivity.class);
            startActivity(intent);
        }));


        room.setOnClickListener((view -> {
            Intent intent = new Intent(getApplicationContext(), BoardRoomActivity.class);
            startActivity(intent);
        }));
    }
}
