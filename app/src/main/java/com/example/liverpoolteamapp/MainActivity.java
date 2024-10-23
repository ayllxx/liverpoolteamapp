package com.example.liverpoolteamapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Enable the back button in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Back button enabled
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp); // Custom back arrow icon

        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load players from the JSON file
        List<Player> players = loadPlayersFromJson();
        PlayerAdapter adapter = new PlayerAdapter(players, this);
        recyclerView.setAdapter(adapter);
    }

    // Handle the back button press in the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // When the back button is pressed, finish the activity (exit)
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Method to load player data from the JSON file
    private List<Player> loadPlayersFromJson() {
        List<Player> players = new ArrayList<>();

        try {
            // Read the JSON file from assets
            InputStream is = getAssets().open("players.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            // Parse the JSON
            JSONObject jsonObject = new JSONObject(json);
            JSONArray playersArray = jsonObject.getJSONArray("players");

            // Loop through each player in the JSON array
            for (int i = 0; i < playersArray.length(); i++) {
                JSONObject playerObject = playersArray.getJSONObject(i);

                // Check if the player is a goalkeeper or outfield player
                String position = playerObject.getString("position");
                if (position.equalsIgnoreCase("Goalkeeper")) {
                    // Create a Player object for a goalkeeper
                    Player player = new Player(
                            playerObject.getInt("id"),
                            playerObject.getString("name"),
                            position,
                            playerObject.getInt("number"),
                            playerObject.getString("description"),
                            playerObject.getString("image"),
                            playerObject.getString("bio"),
                            playerObject.getInt("clean_sheets"),
                            playerObject.getInt("appearances")
                    );
                    players.add(player);
                } else {
                    // Create a Player object for outfield players
                    Player player = new Player(
                            playerObject.getInt("id"),
                            playerObject.getString("name"),
                            position,
                            playerObject.getInt("number"),
                            playerObject.getString("description"),
                            playerObject.getString("image"),
                            playerObject.getString("bio"),
                            playerObject.getInt("goals"),
                            playerObject.has("assists") ? playerObject.getInt("assists") : 0, // Check if assists exist
                            playerObject.getInt("appearances")

                    );
                    players.add(player);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }
}
