package com.example.tourismapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private List<Place> placeList;
    private Context context;
    private OnClickListener listener;

    public PlaceAdapter(List<Place> placeList, Context context, OnClickListener listener) {
        this.placeList = placeList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.places_row, parent, false);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.locationImageView.setImageResource(placeList.get(position).getImage());
        holder.locationNameTextView.setText(placeList.get(position).getName());
        holder.locationDesTextView.setText(placeList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView locationImageView;
        public TextView locationNameTextView, locationDesTextView;
        public OnClickListener onClickListener;

        public ViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);
            locationImageView = itemView.findViewById(R.id.locationImageView);
            locationNameTextView = itemView.findViewById(R.id.locationNameTextView);
            locationDesTextView = itemView.findViewById(R.id.locationDesTextView);
            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onItemClick(placeList.get(getAdapterPosition()).getId());

        }
    }

    public interface OnClickListener {
        void onItemClick(int id);
    }
}
