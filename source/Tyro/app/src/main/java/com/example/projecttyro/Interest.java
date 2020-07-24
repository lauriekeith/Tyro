package com.example.projecttyro;

import java.util.ArrayList;
import java.util.List;

public class Interest implements Comparable<Interest> {
    private String name;
    private List<Profile> peopleWithInterest;

    public Interest(String nameOfInterest){
        name = nameOfInterest;
        peopleWithInterest = new ArrayList<Profile>();
    }

    public String getName(){
        return name;
    }

    public void setUserToInterest(Profile user){
        peopleWithInterest.add(user);
    }

    public List<Profile> getPeopleWithInterest(){
        return peopleWithInterest;
    }


    public boolean equals(Interest otherInterest){
        return (name.equals(otherInterest.getName()) && peopleWithInterest.size() == otherInterest.getPeopleWithInterest().size());
    }

    @Override
    public int compareTo(Interest otherInterest) {
        return name.compareTo(otherInterest.getName());
    }

}
