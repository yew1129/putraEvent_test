package com.example.myapptest;


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
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details); // Set the layout resource here

    eventRV = findViewById(R.id.idRVEvents);
    loadingPB = findViewById(R.id.idProgressBar);
    db = FirebaseFirestore.getInstance();
    eventArrayList = new ArrayList<>();
    eventRV.setHasFixedSize(true);
    eventRV.setLayoutManager(new LinearLayoutManager(this));

    eventRVAdapter = new EventRVAdapter(eventArrayList, this);
    eventRV.setAdapter(eventRVAdapter);

    db.collection("event").get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    if(!queryDocumentSnapshots.isEmpty()) {
                        loadingPB.setVisibility(View.GONE);
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            event c = d.toObject(event.class);
                            eventArrayList.add(c);

                        }
                        eventRVAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(view_event.this, "No data found in databse", Toast.LENGTH_SHORT).show();

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(view_event.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                }
            });
    }
}
