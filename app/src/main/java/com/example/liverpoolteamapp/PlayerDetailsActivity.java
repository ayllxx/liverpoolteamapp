package com.example.liverpoolteamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.content.Intent;

public class PlayerDetailsActivity extends AppCompatActivity {

    private String wikiUrl;  // Wikipedia URL for the player

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        // Get references to views
        ImageView playerImage = findViewById(R.id.player_image);
        TextView playerName = findViewById(R.id.player_name);
        TextView playerBio = findViewById(R.id.player_bio);
        TextView playerStats = findViewById(R.id.player_stats);

        // Get player details from the Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String playerNameStr = bundle.getString("playerName");
            String playerPosition = bundle.getString("playerPosition");
            String playerBioStr = bundle.getString("playerBio");
            String playerImageStr = bundle.getString("playerImage");
            int playerGoals = bundle.getInt("playerGoals", 0);
            int playerAssists = bundle.getInt("playerAssists", 0);
            int playerCleanSheets = bundle.getInt("playerCleanSheets", 0);
            int playerAppearances = bundle.getInt("playerAppearances");

            // Set the data in the views
            playerName.setText(playerNameStr);
            playerBio.setText(playerBioStr);
            playerImage.setImageResource(getResources().getIdentifier(playerImageStr, "drawable", getPackageName()));

            // Set Wikipedia URL based on player name
            wikiUrl = "https://en.wikipedia.org/wiki/" + playerNameStr.replace(" ", "_");

            // Display stats
            StringBuilder stats = new StringBuilder();
            if (playerPosition.equalsIgnoreCase("Goalkeeper")) {
                stats.append("Clean Sheets: ").append(playerCleanSheets).append("\n");
            } else {
                stats.append("Goals: ").append(playerGoals).append("\n");
                if (playerAssists > 0) {
                    stats.append("Assists: ").append(playerAssists).append("\n");
                }
            }
            stats.append("Appearances: ").append(playerAppearances);
            playerStats.setText(stats.toString());
        }

        // Handle the back button click using FloatingActionButton
        FloatingActionButton fabBack = findViewById(R.id.fab_back);
        fabBack.setOnClickListener(v -> finish());

        // Handle the Wikipedia button click
        FloatingActionButton fabWiki = findViewById(R.id.fab_wiki);
        fabWiki.setOnClickListener(v -> {
            Intent intent = new Intent(PlayerDetailsActivity.this, WebViewActivity.class);
            intent.putExtra("wikiUrl", wikiUrl);  // Pass the Wikipedia URL to WebViewActivity
            startActivity(intent);
        });
    }
}
