package com.example.myapptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class view_specific_event extends AppCompatActivity {

    private String eventDocumentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_specific_event);

        // Retrieve data from the intent, including the document ID
        Intent intent = getIntent();
        eventDocumentId = intent.getStringExtra("eventDocumentId");

        // Perform a Firestore query to get detailed event information
        FirebaseFirestore.getInstance().collection("event").document(eventDocumentId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String eventTitle = documentSnapshot.getString("title");
                        String eventDate = documentSnapshot.getString("date");
                        String eventStartTime = documentSnapshot.getString("start_time");
                        String eventEndTime = documentSnapshot.getString("end_time");
                        String eventVenue = documentSnapshot.getString("venue");
                        String eventDescription = documentSnapshot.getString("description");
                        String eventSpeaker = documentSnapshot.getString("speaker_name");
                        String eventOrganizer = documentSnapshot.getString("organizer");
                        String eventSeat = String.valueOf(documentSnapshot.getLong("seat"));
                        String eventImageUrl = documentSnapshot.getString("image");

                        // Set the values to display in view_specific_event.xml
                        TextView titleTextView = findViewById(R.id.titleTextView);
                        TextView dateTextView = findViewById(R.id.dateTextView);
                        TextView startTimeTextView = findViewById(R.id.startTimeTextView);
                        TextView venueTextView = findViewById(R.id.venueTextView);
                        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
                        TextView speaker_nameTextView = findViewById(R.id.speaker_nameTextView);
                        TextView organizerTextView = findViewById(R.id.organizerTextView);
                        TextView seatTextView = findViewById(R.id.seatTextView);

                        ImageView imageView = findViewById(R.id.imageView);

                        titleTextView.setText(eventTitle);
                        dateTextView.setText(eventDate);
                        descriptionTextView.setText(eventDescription);
                        speaker_nameTextView.setText(eventSpeaker);
                        startTimeTextView.setText(eventStartTime);
                        venueTextView.setText(eventVenue);
                        speaker_nameTextView.setText(eventSpeaker);
                        organizerTextView.setText(eventOrganizer);
                        seatTextView.setText(eventSeat);

                        Picasso.get().load(eventImageUrl).into(imageView);

                        // Set up the SwipeButton to handle registration
                        SwipeButton swipeButton = findViewById(R.id.swipe_btn_reg);
                        swipeButton.setOnStateChangeListener(active -> {
                            // Display a message or perform additional actions before registration
                            Toast.makeText(view_specific_event.this, "Swipe button clicked", Toast.LENGTH_SHORT).show();

                            // Optionally, you can start the registration process here
                            // Start the register_event activity and pass the document ID
                            Intent registerIntent = new Intent(view_specific_event.this, register_event.class);
                            registerIntent.putExtra("eventDocumentId", eventDocumentId);
                            startActivity(registerIntent);
                        });
                    } else {
                        // Handle the case where the document doesn't exist
                        Toast.makeText(view_specific_event.this, "Event details not found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle failures in Firestore query
                    Toast.makeText(view_specific_event.this, "Failed to retrieve event details", Toast.LENGTH_SHORT).show();
                });
    }
}

