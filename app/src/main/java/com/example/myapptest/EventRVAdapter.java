package com.example.myapptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventRVAdapter extends RecyclerView.Adapter<EventRVAdapter.ViewHolder> {

    private ArrayList<event> eventArrayList;
    private Context context;
    private OnItemClickListener onItemClickListener; // Add this member variable

    // Constructor
    public EventRVAdapter(ArrayList<event> eventArrayList, Context context) {
        this.eventArrayList = eventArrayList;
        this.context = context;
    }

    // Interface to handle item click events
    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    // Method to set the click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.event_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        event events = eventArrayList.get(position);
        holder.eventTitleTV.setText(events.getTitle());
        holder.eventDateTV.setText(events.getDate());
        holder.eventDescriptionTV.setText(events.getDescription());

        String imageUrl = events.getImage();
        Picasso.get()
                .load(imageUrl)
                .into(holder.eventImageView);

        // Set click listener on the item view
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView eventTitleTV;
        private final TextView eventDateTV;
        private final TextView eventDescriptionTV;
        private final ImageView eventImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTitleTV = itemView.findViewById(R.id.idTVTitle);
            eventDateTV = itemView.findViewById(R.id.idTVDate);
            eventDescriptionTV = itemView.findViewById(R.id.idTVDescription);
            eventImageView = itemView.findViewById(R.id.idEventImageView);
        }
    }
}
