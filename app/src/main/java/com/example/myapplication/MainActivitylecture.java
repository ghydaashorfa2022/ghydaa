package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivitylecture extends AppCompatActivity {
    Uri imgeuri;
    ImageView imgeview;
    Button addimge;
    Button uploadimge;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitylecture);
        imgeview=findViewById(R.id.imageview);
        addimge=findViewById(R.id.btn_add_image);
        uploadimge=findViewById(R.id.btn_upload);


       addimge.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
         selectImge();
           }
       });
        uploadimge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ProgressBar simpleProgressBar=(ProgressBar) findViewById(R.id.determinateBar); // initiate the progress bar
                int maxValue=simpleProgressBar.getMax();
                simpleProgressBar.setVisibility(View.VISIBLE);*/
                Uploadimge();

             }
        });

    }
    public void selectImge(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && data!=null && data.getData()!=null);
        imgeuri=data.getData();
        imgeview.setImageURI(imgeuri);
    }
    public void Uploadimge(){
        storageReference=FirebaseStorage.getInstance().getReference("imges/");
        storageReference.putFile(imgeuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imgeview.setImageURI(null);
                Toast.makeText(MainActivitylecture.this, "upload sucsse",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivitylecture.this, "upload faild", Toast.LENGTH_SHORT).show();
            }
        });
    }

}