package com.example.myapptest;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.material.button.MaterialButton;  // Import for MaterialButton, if not already imported
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;


public class event_add extends AppCompatActivity {
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_START_TIME = "start_time";
    private static final String KEY_END_TIME = "end_time";
    private static final String KEY_VENUE = "venue";
    private static final String KEY_SPEAKER_COUNT = "speaker_count";
    private static final String KEY_SPEAKER_NAME = "speaker_name";
    private static final String KEY_ORGANIZER = "organizer";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_SEAT = "seat";
    private static final String KEY_AVAILABLE_SEAT = "available_seat";

    private EditText editTextId;
    private EditText editTextTitle;
    private EditText editTextDate;
    private EditText editTextStartTime;
    private EditText editTextEndTime;
    private EditText editTextVenue;
    private EditText editTextSpeakerCount;
    private EditText editTextSpeakerName;
    private EditText editTextOrganizer;
    private EditText editTextDescription;
    private EditText editTextSeat;
    private EditText editTextAvailableSeat;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_add);

        // Initialize your EditText fields
        editTextId = findViewById(R.id.id);
        editTextTitle = findViewById(R.id.title);
        editTextDate = findViewById(R.id.date);
        editTextStartTime = findViewById(R.id.start_time);
        editTextEndTime = findViewById(R.id.end_time);
        editTextVenue = findViewById(R.id.venue);
        editTextSpeakerCount = findViewById(R.id.speaker_count);
        editTextSpeakerName = findViewById(R.id.speaker_name);
        editTextOrganizer = findViewById(R.id.organizer);
        editTextDescription = findViewById(R.id.description);
        editTextSeat = findViewById(R.id.seat);
        editTextAvailableSeat = findViewById(R.id.available_seat);

        MaterialButton submitEventBtn = findViewById(R.id.submit_event_btn);

        submitEventBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveEventInfo(); // Call saveEventInfo when the button is clicked
            }
        });
    }

    // Write event data to the database
    public void saveEventInfo() {
        String id = editTextId.getText().toString();
        String title = editTextTitle.getText().toString();
        String date = editTextDate.getText().toString();
        String startTime = editTextStartTime.getText().toString();
        String endTime = editTextEndTime.getText().toString();
        String venue = editTextVenue.getText().toString();
        String speakerCount = editTextSpeakerCount.getText().toString();
        String speakerName = editTextSpeakerName.getText().toString();
        String organizer = editTextOrganizer.getText().toString();
        String description = editTextDescription.getText().toString();
        String seat = editTextSeat.getText().toString();
        String availableSeat = editTextAvailableSeat.getText().toString();

        Map<String, Object> event = new HashMap<>();
        event.put(KEY_ID, id);
        event.put(KEY_TITLE, title);
        event.put(KEY_DATE, date);
        event.put(KEY_START_TIME, startTime);
        event.put(KEY_END_TIME, endTime);
        event.put(KEY_VENUE, venue);
        event.put(KEY_SPEAKER_COUNT, speakerCount);
        event.put(KEY_SPEAKER_NAME, speakerName);
        event.put(KEY_ORGANIZER, organizer);
        event.put(KEY_DESCRIPTION, description);
        event.put(KEY_SEAT, seat);
        event.put(KEY_AVAILABLE_SEAT, availableSeat);

        // Add event to the "events" collection in the database
        db.collection("event").document(id).set(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(event_add.this, "Event added to database", Toast.LENGTH_SHORT).show();
                        Log.d("SUCCESS", "Event added to database");

                        // Optionally, you can navigate to another activity or perform additional actions here
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(event_add.this, "Error adding event to database", Toast.LENGTH_SHORT).show();
                        Log.d("FAIL", "Error adding event to database");
                    }
                });
    }
}
