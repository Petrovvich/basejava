package com.petrovvich.webapp.model;

import java.util.ArrayList;

public class Qualification {

    private ArrayList<String> achievements;

    public Qualification(ArrayList<String> achievements) {
        this.achievements = achievements;
    }

    public ArrayList<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(ArrayList<String> achievements) {
        this.achievements = achievements;
    }
}
