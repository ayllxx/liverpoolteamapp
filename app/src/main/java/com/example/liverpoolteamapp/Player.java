package com.example.liverpoolteamapp;


public class Player {
    private int id;
    private String name;
    private String position;
    private int number;
    private String description;
    private String image;
    private String bio;
    private int goals;
    private int assists;
    private int appearances;
    private int cleanSheets;

    // Constructor for outfield players
    public Player(int id, String name, String position, int number, String description, String image, String bio, int goals, int assists, int appearances) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.number = number;
        this.description = description;
        this.image = image;
        this.bio = bio;
        this.goals = goals;
        this.assists = assists;
        this.appearances = appearances;
    }

    // Constructor for goalkeepers
    public Player(int id, String name, String position, int number, String description, String image, String bio, int cleanSheets, int appearances) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.number = number;
        this.description = description;
        this.image = image;
        this.bio = bio;
        this.cleanSheets = cleanSheets;
        this.appearances = appearances;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public int getNumber() { return number; }
    public String getDescription() { return description; }
    public String getImage() { return image; }
    public String getBio() { return bio; }
    public int getGoals() { return goals; }
    public int getAssists() { return assists; }
    public int getAppearances() { return appearances; }
    public int getCleanSheets() { return cleanSheets; }
}
