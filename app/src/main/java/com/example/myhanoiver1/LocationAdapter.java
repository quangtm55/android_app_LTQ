package com.example.myhanoiver1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    private Context context;
    private List<Location> mListLocation;

    public LocationAdapter(Context mContext) {
        this.context = mContext;
    }

    public void setData(List<Location> list) {
        this.mListLocation = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("ResourceType") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        Location location = mListLocation.get(position);
        if (location == null) {
            return;
        }

        holder.imgLocation.setImageResource(location.getResourceId());
        holder.txtLocation.setText(location.getName());

    }

    @Override
    public int getItemCount() {
        if (mListLocation != null) {
            return mListLocation.size();
        }
        return 0;
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLocation;
        private TextView txtLocation;
        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);

            imgLocation = itemView.findViewById(R.id.img_location);
            txtLocation = itemView.findViewById(R.id.txt_location);
        }
    }
}
