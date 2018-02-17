package com.petrovvich.webapp.model;

import java.util.ArrayList;

public class Achievement {

    private ArrayList<String> achievements;

    public Achievement(ArrayList<String> achievements) {
        this.achievements = achievements;
    }

    public ArrayList<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(ArrayList<String> achievements) {
        this.achievements = achievements;
    }
}
