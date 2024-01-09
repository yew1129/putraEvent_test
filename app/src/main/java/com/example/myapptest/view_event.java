package com.example.myapptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class view_event extends AppCompatActivity {

    private RecyclerView eventRV;
    private ArrayList<event> eventArrayList;
    private EventRVAdapter eventRVAdapter;
    private FirebaseFirestore db;
    ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);

        eventRV = findViewById(R.id.idRVEvents);
        loadingPB = findViewById(R.id.idProgressBar);
        db = FirebaseFirestore.getInstance();
        eventArrayList = new ArrayList<>();
        eventRV.setHasFixedSize(true);
        eventRV.setLayoutManager(new LinearLayoutManager(this));

        eventRVAdapter = new EventRVAdapter(eventArrayList, this);
        eventRV.setAdapter(eventRVAdapter);

        db.collection("event").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        loadingPB.setVisibility(View.GONE);
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            event c = d.toObject(event.class);
                            eventArrayList.add(c);
                        }
                        eventRVAdapter.notifyDataSetChanged();

                        // Set click listener for each item in the RecyclerView
                        eventRVAdapter.setOnItemClickListener((position, v) -> {
                            // Retrieve the document ID of the clicked event
                            String selectedEventDocumentId = list.get(position).getId();
                            // Call the method to handle the click event
                            onEventClick(selectedEventDocumentId);
                        });
                    } else {
                        Toast.makeText(view_event.this, "No data found in database", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(view_event.this, "Failed to get data.", Toast.LENGTH_SHORT).show();
                });
    }

    // Method to handle the click event on a specific event
    private void onEventClick(String selectedEventDocumentId) {
        // Intent to navigate to the register_event activity
        Intent intent = new Intent(view_event.this, register_event.class);
        // Pass the selected event's document ID to the register_event activity
        intent.putExtra("eventDocumentId", selectedEventDocumentId);
        // Start the register_event activity
        startActivity(intent);
    }
}
