package com.example.liverpoolteamapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Player> players;
    private Context context;

    public PlayerAdapter(List<Player> players, Context context) {
        this.players = players;
        this.context = context;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.name.setText(player.getName());
        holder.position.setText(player.getPosition());
        holder.image.setImageResource(context.getResources().getIdentifier(player.getImage(), "drawable", context.getPackageName()));

        // Set an OnClickListener to open PlayerDetailsActivity when a player is clicked
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayerDetailsActivity.class);
            // Pass player details to PlayerDetailsActivity
            intent.putExtra("playerId", player.getId());
            intent.putExtra("playerName", player.getName());
            intent.putExtra("playerPosition", player.getPosition());
            intent.putExtra("playerBio", player.getBio());
            intent.putExtra("playerImage", player.getImage());
            intent.putExtra("playerGoals", player.getGoals());
            intent.putExtra("playerAssists", player.getAssists());
            intent.putExtra("playerCleanSheets", player.getCleanSheets());
            intent.putExtra("playerAppearances", player.getAppearances());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView name, position;
        ImageView image;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.player_name);
            position = itemView.findViewById(R.id.player_position);
            image = itemView.findViewById(R.id.player_image);
        }
    }
}


