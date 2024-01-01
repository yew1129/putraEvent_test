package com.example.myapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class TestNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);

        EditText editTextUsername = findViewById(R.id.edit_notif_msg);
        MaterialButton test_notification_btn = findViewById(R.id.submit_btn);

        test_notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send the notification via Firebase Messaging with the message in the EditText
                // field
                String message = editTextUsername.getText().toString();
                Intent intent = new Intent(TestNotification.this, NotificationService.class);
                intent.putExtra("message", message);
                startService(intent);
            }
        });
    }
}