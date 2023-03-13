package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText head;
    EditText content;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        head = findViewById(R.id.head);
        content = findViewById(R.id.content);
        btn = findViewById(R.id.btn_save);

    }
        public void saveToFirebase(View v) {
            String head2= head.getText().toString();
            String content1= content.getText().toString();
            Map<String, Object> note = new HashMap<>();
            if (!head2.isEmpty() && !content1.isEmpty()) {
                note.put("head", head2);
                note.put("content",content1);
                db.collection("Users")
                        .add(note)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                  @Override
                                                  public void onSuccess(DocumentReference documentReference) {
                                                      openActivity2();
                                                      Log.e("TAG", "Data added successfully to database");
                                                  }
                                              }
                        )

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("TAG", "Failed to add database");

                            }
                        });

            } else {
                Toast.makeText(this, "Please Fill fields", Toast.LENGTH_SHORT).show();
            }



        }
    public void openActivity2(){
        Intent intent = new Intent(this, HomeActivity2.class);
        startActivity(intent);
    }


}