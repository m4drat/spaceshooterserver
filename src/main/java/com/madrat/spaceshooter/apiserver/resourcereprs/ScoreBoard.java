package com.madrat.spaceshooter.apiserver.resourcereprs;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreBoard {
    private ArrayList<User> users;

    public ScoreBoard(ArrayList<User> users) {
        this.users = users;
        Collections.sort(this.users);
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
