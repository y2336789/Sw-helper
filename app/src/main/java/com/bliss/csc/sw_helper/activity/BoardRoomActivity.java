package com.bliss.csc.sw_helper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bliss.csc.sw_helper.PostInfo;
import com.bliss.csc.sw_helper.R;
import com.bliss.csc.sw_helper.adapter.BoardFreeAdapter;
import com.bliss.csc.sw_helper.adapter.BoardRoomAdapter;
import com.bliss.csc.sw_helper.listener.OnPostListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class BoardRoomActivity extends BasicActivity {
    private static final String TAG = "MemberInitActivity";
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private BoardRoomAdapter boardRoomAdapter;
    private ArrayList<PostInfo> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        findViewById(R.id.floatingActionButton).setOnClickListener(onClickListener);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseUser.getUid());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        } else {
                            Log.d(TAG, "No such document");
                            mystartActivity(MemberInitActivity.class);
                        }
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        postList = new ArrayList<>();
        boardRoomAdapter = new BoardRoomAdapter(BoardRoomActivity.this, postList);
        boardRoomAdapter.setOnPostListener(onPostListener);

        recyclerView = findViewById(R.id.recyclerView_room);
        findViewById(R.id.floatingActionButton).setOnClickListener(onClickListener);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(BoardRoomActivity.this));
        recyclerView.setAdapter(boardRoomAdapter);


    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.floatingActionButton:
                    mystartActivity(WriteRoomActivity.class);
                    break;
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        postsUpdate();
    }

    OnPostListener onPostListener = new OnPostListener() {
        @Override
        public void onDelete(String id) {
            Log.e("??????", "??????");
            firebaseFirestore.collection("room").document(id)
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            startTost("???????????? ?????????????????????.");
                            postsUpdate();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            startTost("???????????? ????????? ?????????????????????  .");
                        }
                    });
        }

        @Override
        public void onModify(String id) {

            Log.e("??????", "??????");
        }
    };

    private void mystartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    private void postsUpdate() {
        CollectionReference collectionReference = firebaseFirestore.collection("room");
        collectionReference.orderBy("createdAt", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            postList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                postList.add(new PostInfo(
                                        document.getData().get("title").toString(),
                                        (ArrayList<String>) document.getData().get("contents"),
                                        document.getData().get("publisher").toString(),
                                        new Date(document.getDate("createdAt").getTime()),
                                        document.getId().toString()));
                            }
                            boardRoomAdapter.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }

    private void startTost(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}


