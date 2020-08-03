package com.example.projecttyro;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO: Singleton pattern, not the greatest design - testing purposes ok

public class InterestStore {
    private static InterestStore INSTANCE;
    private Set<Interest> interestList;

    private InterestStore() {
        interestList = new HashSet<>();
    }

    public static InterestStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InterestStore();
        }

        return INSTANCE;
    }

    public Set<Interest> getInterestList() {
        return interestList;
    }


}
