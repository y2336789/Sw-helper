package com.bliss.csc.sw_helper.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bliss.csc.sw_helper.MemberInfo;
import com.bliss.csc.sw_helper.R;
import com.bliss.csc.sw_helper.activity.CameraActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MemberInitActivity extends AppCompatActivity {
    private static final String TAG = "MemberInitActivity";
    private ImageView profileImageView;
    private String profilePath;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_init);

        profileImageView = findViewById(R.id.profileimageView);
        profileImageView.setOnClickListener(onClickListener);

        findViewById(R.id.btn_check).setOnClickListener(onClickListener);
        findViewById(R.id.picture_btn).setOnClickListener(onClickListener);
        findViewById(R.id.gallery_btn).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0 : {
                if (resultCode == Activity.RESULT_OK) {
                    profilePath = data.getStringExtra("profilePath");
                    Bitmap bmp = BitmapFactory.decodeFile(profilePath);
                    profileImageView.setImageBitmap(bmp);
                }
                break;
            }
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_check:
                    profileUpdate();
                    break;
                case R.id.profileimageView:
                    CardView cardView = findViewById(R.id.buttonsCardView);
                    if(cardView.getVisibility() == View.VISIBLE){
                        cardView.setVisibility(View.GONE);
                    }else{
                        cardView.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.picture_btn:
                    mystartActivity(CameraActivity.class);
                    break;
                case R.id.gallery_btn:
                    if (ContextCompat.checkSelfPermission(MemberInitActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MemberInitActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                1);
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MemberInitActivity.this,
                                Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        } else {
                            startToast("권한을 허용해 주세요");
                        }
                    }else{
                        mystartActivity(GalleryActivity.class);
                    }
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mystartActivity(GalleryActivity.class);
                } else {
                    startToast("권한을 허용해 주세요");
                }
            }
        }
    }


    private void profileUpdate(){
        final String name = ((EditText) findViewById(R.id.et_name)).getText().toString();
        final String hakbun = ((EditText) findViewById(R.id.et_hakbun)).getText().toString();
        final String phoneNumber = ((EditText) findViewById(R.id.et_phonenumber)).getText().toString();
        final String birthday = ((EditText) findViewById(R.id.et_birthday)).getText().toString();

        if(name.length() > 0 && phoneNumber.length() > 9 && birthday.length() > 5 && hakbun.length() > 9) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            user = FirebaseAuth.getInstance().getCurrentUser();
            final StorageReference mountainImagesRef = storageRef.child("images/"+user.getUid()+"/profileImage.jpg");

            try {
                InputStream stream = new FileInputStream(new File(profilePath));
                UploadTask uploadTask = mountainImagesRef.putStream(stream);
                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return mountainImagesRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();


                            MemberInfo memberInfo = new MemberInfo(name,hakbun,phoneNumber,birthday,downloadUri.toString());
                            uploader(memberInfo);
                        } else {
                            startToast("회원정보를 보내는데 실패하였습니다.");
                        }
                    }
                });
            } catch (FileNotFoundException e) {
                Log.e("로그", "에러: " + e.toString());
            }
        } else {
            startToast("회원정보를 입력해주세요.");
        }
    }

    private void uploader(MemberInfo memberInfo) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(user.getUid()).set(memberInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startToast("회원정보 등록 성공하였습니다");
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("회원정보를 입력해주세요");
                    }
                });
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void mystartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivityForResult(intent, 0);
    }
}
