package com.example.myapptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventRVAdapter extends RecyclerView.Adapter<EventRVAdapter.ViewHolder> {
    // creating variables for our ArrayList and context
    private ArrayList<event> eventArrayList;
    private Context context;

    // creating constructor for our adapter class
    public EventRVAdapter(ArrayList<event> eventArrayList, Context context) {
        this.eventArrayList = eventArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.event_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventRVAdapter.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        event events = eventArrayList.get(position);
        holder.eventTitleTV.setText(events.getTitle());
//        holder.eventVenueTV.setText(events.getVenue());
        holder.eventDescriptionTV.setText(events.getDescription());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return eventArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView eventTitleTV;
//        private final TextView eventVenueTV;
        private final TextView eventDescriptionTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            eventTitleTV = itemView.findViewById(R.id.idTVTitle);
//            eventVenueTV = itemView.findViewById(R.id.idTVVenue);
            eventDescriptionTV = itemView.findViewById(R.id.idTVDescription);
        }
    }
}
