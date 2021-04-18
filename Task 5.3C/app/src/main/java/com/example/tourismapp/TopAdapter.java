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

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder>{
    private List<Place> topList;
    private Context context;
    private OnClickListener listener;

    public TopAdapter(List<Place> topList, Context context, OnClickListener listener) {
        this.topList = topList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.top, parent, false);
        return new TopAdapter.ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAdapter.ViewHolder holder, int position) {
        holder.topImageView.setImageResource(topList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return topList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView topImageView;
        public OnClickListener onClickListener;

        public ViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);
            topImageView = itemView.findViewById(R.id.topImageView);
            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onItemClick(topList.get(getAdapterPosition()).getId());

        }
    }
    public interface OnClickListener {
        void onItemClick(int id);
    }
}
