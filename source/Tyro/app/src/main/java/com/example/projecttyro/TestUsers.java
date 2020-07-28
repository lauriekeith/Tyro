package com.example.projecttyro;

import java.util.ArrayList;
import java.util.List;

public class TestUsers {
    Interest football = new Interest("Football");
    Interest cricket = new Interest("Cricket");
    Interest tennis = new Interest("Tennis");

    Profile user1 = new Profile("Matt", "iOS", "Manchester", "Salford");
    Profile user2 = new Profile("Sam", "Manager", "London", "Surrey");

    List<Profile> users = new ArrayList<>();

    public TestUsers() {

        user1.addInterest(football);
        user1.addInterest(tennis);


        user2.addInterest(cricket);
        user2.addInterest(football);

        users.add(user1);
        users.add(user2);


    }

    public List<Profile> getUsers() {
        return users;
    }
}
