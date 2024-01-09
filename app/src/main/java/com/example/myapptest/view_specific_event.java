package com.example.myapptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        String eventTitle = intent.getStringExtra("eventTitle");
        String eventDate = intent.getStringExtra("eventDate");
        String eventStartTime = intent.getStringExtra("eventStartTime");
        String eventEndTime = intent.getStringExtra("eventEndTime");
        String eventVenue = intent.getStringExtra("eventVenue");
        String eventDescription = intent.getStringExtra("eventDescription");
        String eventSpeaker = intent.getStringExtra("eventSpeaker");
        String eventOrganizer = intent.getStringExtra("eventOrganizer");
        String eventSeat = intent.getStringExtra("eventSeat");
        String eventAvailableSeat = intent.getStringExtra("eventAvailableSeat");
        String eventImageUrl = intent.getStringExtra("eventImageUrl");

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
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                // Display a message or perform additional actions before registration
                Toast.makeText(view_specific_event.this, "Swipe button clicked", Toast.LENGTH_SHORT).show();

                // Optionally, you can start the registration process here
                // Start the register_event activity and pass the document ID
                Intent registerIntent = new Intent(view_specific_event.this, register_event.class);
                registerIntent.putExtra("eventDocumentId", eventDocumentId);
                startActivity(registerIntent);
            }
        });
    }
}