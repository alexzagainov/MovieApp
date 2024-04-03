package com.projectapps.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projectapps.movieapp.models.ActorModel;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ActorsListAdapter extends RecyclerView.Adapter<ActorsListAdapter.ActorsViewHolder> {

    Context context;
    ArrayList<ActorModel> actors;

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
        loadImage(holder.actorImage, actor.getProfile_path());
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

    private static void loadImage(ImageView imageView, String imageUrl){
        // Basic Url: "https://image.tmdb.org/t/p/w500"
        if(imageUrl== null){
            imageView.setImageResource(R.drawable.no_image_available);
        }else {
            String imagePath = "https://image.tmdb.org/t/p/w500" + imageUrl;

            Glide.with(imageView.getContext())
                    .load(imagePath)
                    .into(imageView);
        }
    }
}
