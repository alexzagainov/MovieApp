package com.projectapps.movieapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projectapps.movieapp.R;
import com.projectapps.movieapp.models.ActorModel;
import com.projectapps.movieapp.utils.ImageLoader;

import java.util.ArrayList;

public class ActorsListAdapter extends RecyclerView.Adapter<ActorsListAdapter.ActorsViewHolder> {

    private Context context;
    private ArrayList<ActorModel> actors;

    private ImageLoader imageLoader = new ImageLoader();

    public ActorsListAdapter(Context context, ArrayList<ActorModel> actors) {
        this.context = context;
        this.actors = actors;
    }

    @NonNull
    @Override
    public ActorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actors_item_list,parent,false);
        return new ActorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorsViewHolder holder, int position) {
        ActorModel actor = actors.get(position);
        holder.actorName.setText(actor.getName());
        imageLoader.loadImage(holder.actorImage, actor.getProfile_path());
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public class ActorsViewHolder extends RecyclerView.ViewHolder{
        ImageView actorImage;
        TextView actorName;

        public ActorsViewHolder(@NonNull View itemView) {
            super(itemView);
            actorImage = itemView.findViewById(R.id.actorImage);
            actorName = itemView.findViewById(R.id.actorName);
        }
    }
}
