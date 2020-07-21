package com.example.projecttyro;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/* Note: implement Parcelable interface, allows Profile object to be exchanged between activities */
public class Profile {


    enum UserInfo {
        NAME,
        JOB_TITLE,
        HOME_LOCATION,
        WORK_LOCATION,
        INTERESTS,
        CAR_SHARING
    }
    /*
    * empty variables to store the profile information
    * These will all be initialised by the constructor or by functions
    * all are private and will be accessed by accessor functions
    * */
    private String nameOfUser;
    private String userEmail;
    private String jobTitle;
    private String homeLocation;
    private String workLocation;
    private String profilePicture;
    private String [] interests;
    private Date dateOfBirth;
    private int noOfGroups;
    private int noOfConnections;
    private Boolean carSharing;
    private Map<UserInfo, Boolean> visibilityPermissions;
    private Boolean jobTitleHidden;
    private Boolean homeLocationHidden;
    private Boolean workLocationHidden;
    private Boolean interestsHidden;
    private Boolean carSharingHidden;
    private Boolean addedProfilePicture;

    public Map<UserInfo, Boolean> getVisibilityPermissions() {
        return visibilityPermissions;
    }

    public Profile(String nameOfUser, String jobTitle, String homeLocation, String[] interests,
                   Boolean carSharing, Boolean jobTitleHidden, Boolean homeLocationHidden,
                   Boolean interestsHidden, Boolean carSharingHidden) {
        this.nameOfUser = nameOfUser;
        this.jobTitle = jobTitle;
        this.homeLocation = homeLocation;
        this.interests = interests;
        this.carSharing = carSharing;
        this.jobTitleHidden = jobTitleHidden;
        this.homeLocationHidden = homeLocationHidden;
        this.interestsHidden = interestsHidden;
        this.carSharingHidden = carSharingHidden;
    }

    //init profile, stores personal info that cant be empty, and sets other to standard
    public Profile(String name, String email, String password, String job, String home,
                   String work, Date dob){
        nameOfUser = name;
        userEmail = email;
        jobTitle = job;
        homeLocation = home;
        workLocation = work;
        profilePicture = "default.png"; //this must be the PATH to the image
        dateOfBirth = dob;
        noOfGroups = 0;
        noOfConnections = 0;
        carSharing = true;
        jobTitleHidden = false;
        homeLocationHidden = false;
        workLocationHidden = false;
        interestsHidden = false;
        carSharingHidden = false;
        addedProfilePicture = false;
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

    public Boolean hasUserSetProfilePicture(){return addedProfilePicture;}

    public void setProfilePicture(String newPhoto) {
        profilePicture = newPhoto;
        addedProfilePicture = true;
    }

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
