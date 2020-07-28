package com.example.projecttyro;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;
import java.util.List;

public class Interest implements Comparable<Interest>, Parcelable {

    private String name;
    private List<Profile> peopleWithInterest = new ArrayList<>();

    public Interest(String nameOfInterest){
        name = nameOfInterest;
    }

    protected Interest(Parcel in) {
        name = in.readString();
        peopleWithInterest = new ArrayList<>();
        in.readTypedList(peopleWithInterest, Profile.CREATOR);
    }

    public static final Creator<Interest> CREATOR = new Creator<Interest>() {
        @Override
        public Interest createFromParcel(Parcel in) {
            return new Interest(in);
        }

        @Override
        public Interest[] newArray(int size) {
            return new Interest[size];
        }
    };

    public String getName(){
        return name;
    }


    @Override
    public String toString() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeTypedList(peopleWithInterest);
    }

}
