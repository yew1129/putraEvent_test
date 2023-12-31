package com.example.myapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private EditText editTextUsername;
    private EditText editTextPassword;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        MaterialButton submit_btn = findViewById(R.id.submit_btn);
        MaterialButton view_btn = findViewById(R.id.view_btn);
        MaterialButton add_image_btn = findViewById(R.id.add_image_btn);

        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, view_event.class);
                startActivity(intent);
            }
        });

        add_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, UploadImage.class);
                    startActivity(intent);
            }
        });
    }

    // To write data to the database, call the set() method, passing in a Map of key-value pairs.
    public void saveInfo(View v) {
        String username = editTextUsername.getText().toString();
        // For email registration, is the email has ******@student.upm.edu.my ==> No need request mail address from user, just matric number
        String password = editTextPassword.getText().toString();

        Map<String, Object> user = new HashMap<>();
        user.put(KEY_USERNAME, username);
        user.put(KEY_PASSWORD, password);

        // Add user to database, handle both success and failure
        db.collection("users").document(username).set(user)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(MainActivity.this, "User added to database", Toast.LENGTH_SHORT).show();
                    Log.d("SUCCESS", "User added to database");

                    // navigate to another activity
                    Intent i = new Intent(MainActivity.this, event_add.class);
                    startActivity(i);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(MainActivity.this, "Error adding user to database", Toast.LENGTH_SHORT).show();
                    Log.d("FAIL", "Error adding user to database");
                }
            }
        );
    }

    // Show a toast for test
    public void showToast(View v) {
        Toast.makeText(MainActivity.this, "This is a toast", Toast.LENGTH_SHORT).show();
    }
}