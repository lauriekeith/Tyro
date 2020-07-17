package com.example.projecttyro;

import java.util.Arrays;
import java.util.Date;

public class Profile {


    /*
    * empty variables to store the profile information
    * These will all be initialised by the constructor or by functions
    * all are private and will be accessed by accessor functions
    * */
    private String nameOfUser;
    private String userEmail;
    private String userPassword;
    private String jobTitle;
    private String homeLocation;
    private String workLocation;
    private String profilePicture;
    private String [] interests;
    private Date dateOfBirth;
    private int noOfGroups;
    private int noOfConnections;
    private Boolean carSharing;
    private Boolean jobTitleHidden;
    private Boolean homeLocationHidden;
    private Boolean workLocationHidden;
    private Boolean interestsHidden;
    private Boolean carSharingHidden;

    //init profile, stores personal info that cant be empty, and sets other to standard
    public Profile(String name, String email, String password, String job, String home,
                   String work, Date dob){
        nameOfUser = name;
        userEmail = email;
        userPassword = password;
        jobTitle = job;
        homeLocation = home;
        workLocation = work;
        profilePicture = "default.png";
        dateOfBirth = dob;
        noOfGroups = 0;
        noOfConnections = 0;
        carSharing = true;
        jobTitleHidden = false;
        homeLocationHidden = false;
        workLocationHidden = false;
        interestsHidden = false;
        carSharingHidden = false;
    }

    //following are get and set functions for each variable
    public String getName(){
        return nameOfUser;
    }

    public void setName(String newName){
        nameOfUser = newName;
    }

    public String getEmail(){
        return userEmail;
    }

    public void setEmail(String newEmail){
        userEmail = newEmail;
    }

    //password doesn't have a get function as it will not be displayed and may lead to data breach
    public void setNewPassword(String newPassword){
        userPassword = newPassword;
    }

    public String getJobTitle(){
        return jobTitle;
    }

    public void setJobTitle(String newTitle){
        jobTitle = newTitle;
    }

    public String getHomeLocation(){
        return homeLocation;
    }

    public void setHomeLocation(String newHome){
        homeLocation = newHome;
    }

    public String getWorkLocation(){
        return workLocation;
    }

    public void setWorkLocation(String newWork){
        workLocation = newWork;
    }

    public String getProfilePicture() {return profilePicture;}

    public void setProfilePicture(String newPhoto) {profilePicture = newPhoto;}

    public String [] getInterests(){
        return interests;
    }

    //add interest to the interests array --- will probably change when interest tags implemented
    public void addInterest(String interest){
        interests = Arrays.copyOf(interests, interests.length + 1);
        interests[interests.length - 1] = interest;

    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(Date newDOB){
        dateOfBirth = newDOB;
    }

    public int getNoOfGroups(){
        return noOfGroups;
    }

    public void addedToGroup(){
        noOfGroups ++;
        //will add items to some data set to store the groups
    }

    public int getNoOfConnections(){
        return noOfConnections;
    }

    public void increaseConnections(){
        noOfConnections ++;
        //will add items to some data set to store the connections
    }

    public Boolean isCarSharing(){
        return carSharing;
    }

    public void setCarSharing(Boolean setStatus){
        carSharing = setStatus;
    }

    public Boolean jobTitleIsHidden(){ return jobTitleHidden; }

    public void setJobTitleHidden(Boolean trueOrFalse) { jobTitleHidden = trueOrFalse; }

    public Boolean homeLocationIsHidden(){ return homeLocationHidden; }

    public void setHomeLocationHidden(Boolean trueOrFalse) { homeLocationHidden = trueOrFalse; }

    public Boolean workLocationIsHidden(){ return workLocationHidden; }

    public void setWorkLocationHidden(Boolean trueOrFalse) { workLocationHidden = trueOrFalse; }

    public Boolean interestsAreHidden(){ return interestsHidden; }

    public void setInterestsHidden(Boolean trueOrFalse) { interestsHidden = trueOrFalse; }

    public Boolean carSharingIsHidden(){ return carSharingHidden; }

    public void setCarSharingHidden(Boolean trueOrFalse) { carSharingHidden = trueOrFalse; }

}
