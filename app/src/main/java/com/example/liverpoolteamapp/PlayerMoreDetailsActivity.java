package com.example.liverpoolteamapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlayerMoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_more_details);

        // Get the player details passed from PlayerDetailsActivity
        String playerName = getIntent().getStringExtra("playerName");
        String playerAchievements = getIntent().getStringExtra("playerAchievements");

        // Ensure playerAchievements is not null
        if (playerAchievements == null || playerAchievements.isEmpty()) {
            playerAchievements = "1x Champions League, 2 Premier League titles";  // Default message for null or empty achievements
        }

        // Set the player details in the TextViews
        TextView nameTextView = findViewById(R.id.player_name);
        TextView achievementsTextView = findViewById(R.id.player_achievements);

        nameTextView.setText(playerName);
        achievementsTextView.setText("Achievements: " + playerAchievements);
    }}
