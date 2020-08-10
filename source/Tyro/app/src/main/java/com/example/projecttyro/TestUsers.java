package com.example.projecttyro;

import java.util.ArrayList;
import java.util.List;

public class TestUsers {
    Interest football = new Interest("Football");
    Interest cricket = new Interest("Cricket");
    Interest tennis = new Interest("Tennis");


    Profile user1 = new Profile("Matt", "iOS", "Manchester", "Salford");
    Profile user2 = new Profile("Sam", "Manager", "London", "Surrey");
    Profile user3 = new Profile("Tom", "Manager", "London", "Surrey");
    Profile user4 = new Profile("Kate", "Manager", "London", "Surrey");

    List<Profile> users = new ArrayList<>();

    public TestUsers() {

        user1.addInterest(football);
        user1.addInterest(tennis);
        user1.setPostCode("SM13DY");


        user2.addInterest(cricket);
        user2.addInterest(football);
        user2.setPostCode("SM14DE");

        user3.setPostCode("CR78HQ");

        user4.setPostCode("Sw198UG");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);




    }

    public List<Profile> getUsers() {
        return users;
    }
}
