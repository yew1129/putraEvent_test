package com.example.myapptest;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        event events = eventArrayList.get(position);
        holder.eventTitleTV.setText(events.getTitle());
//        holder.eventVenueTV.setText(events.getVenue());
        holder.eventDescriptionTV.setText(events.getDescription());
        Picasso.get().setLoggingEnabled(true);

        String imageUrl = events.getImageUrl();
        String quotedImageUrl = "https://firebasestorage.googleapis.com/v0/b/putraevent-b6349.appspot.com/o/PutraEVENT%2FConference%20on%20Future.png?alt=media&token=ac7d3e0b-2d6d-4313-89c0-fcb3bbc49061";
        Picasso.get()
                .load(quotedImageUrl)
//                .placeholder(R.drawable.resource_default) // Add your placeholder image resource
                .into(holder.eventImageView);
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
        private final ImageView eventImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            eventTitleTV = itemView.findViewById(R.id.idTVTitle);
//            eventVenueTV = itemView.findViewById(R.id.idTVVenue);
            eventDescriptionTV = itemView.findViewById(R.id.idTVDescription);
            eventImageView = itemView.findViewById(R.id.idEventImageView);

        }
    }
}
