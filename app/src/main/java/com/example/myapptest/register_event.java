package com.example.myapptest;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class register_event extends AppCompatActivity {

    private FirebaseFirestore db;
    private String userId = "tW6IG391zBUMl1DM7T2D2xBbus33";
    private String eventDocumentId; // Updated to get the document ID from the intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_event);
        db = FirebaseFirestore.getInstance();

        // Retrieve the event document ID from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            eventDocumentId = extras.getString("eventDocumentId");
        }

        if (eventDocumentId != null) {
            accessSpecificEventAndStoreUserId();
        } else {
            // Handle the case where the eventDocumentId is not provided
            Toast.makeText(register_event.this, "Event document ID not provided", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if the document ID is not available
        }
    }

    private void updateFirestoreArray(DocumentReference docRef, String arrayName, String itemId, String successMessage) {
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                ArrayList<String> array = (ArrayList<String>) documentSnapshot.get(arrayName);

                if (array == null) {
                    array = new ArrayList<>();
                }

                array.add(itemId);

                Map<String, Object> updateData = new HashMap<>();
                updateData.put(arrayName, array);

                docRef.update(updateData)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(register_event.this, successMessage, Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(register_event.this, "Failed to update " + arrayName, Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }

    private void accessSpecificEventAndStoreUserId() {
        // Go to specific event document to store participant ID
        DocumentReference eventRef = db.collection("event").document(eventDocumentId);

        eventRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                int totalSeats = documentSnapshot.getLong("seat").intValue();
                ArrayList<String> userRegister = (ArrayList<String>) documentSnapshot.get("user_register");

                // If user_register is null or not initialized, create a new ArrayList
                if (userRegister == null) {
                    userRegister = new ArrayList<>();
                }

                // Check if the user ID is already present in the user_register array
                if (!userRegister.contains(userId)) {
                    // Check if there are available seats
                    int availableSeats = totalSeats - userRegister.size();

                    if (availableSeats > 0) {
                        // User ID not found, proceed with the update
                        userRegister.add(userId);

                        // Update the Firestore document with the new user_register array
                        Map<String, Object> updateData = new HashMap<>();
                        updateData.put("user_register", userRegister);

                        eventRef.update(updateData)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(register_event.this, "User ID added to user_register", Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(register_event.this, "Failed to update user_register", Toast.LENGTH_SHORT).show();
                                });
                    } else {
                        // No available seats, show a message
                        Toast.makeText(register_event.this, "Quota Full", Toast.LENGTH_SHORT).show();
                        return; // Exit the method if there are no available seats
                    }
                } else {
                    // User ID is already present, show a message
                    Toast.makeText(register_event.this, "You have already registered for this event", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if the user has already registered for the event
                }
            }

            // User ID not found in user_register, proceed to update user and event profiles
            // Go to student profile
            DocumentReference registerRef = db.collection("users").document(userId);
            updateFirestoreArray(registerRef, "event_register", eventDocumentId, "User ID added to event_register");
        });
    }
}
