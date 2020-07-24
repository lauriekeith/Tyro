package com.example.projecttyro;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/* Note: implement Parcelable interface, allows Profile object to be exchanged between activities */
public class Profile implements Parcelable {


    /* Parcelable interface methods - simply just serializing and deserializing a profile */
    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
    private Interest[] interests;
    private Map<UserInfo, Boolean> hidingInfo = new HashMap<>();
    private String homeLocation;
    private String workLocation;



    /*
     * empty variables to store the profile information
     * These will all be initialised by the constructor or by functions
     * all are private and will be accessed by accessor functions
     * */

    private String nameOfUser;
    private String jobTitle;
    private String profilePicture;
    private Boolean carSharing;

    /* How a profile object is created when it is exchanged between activities */
    protected Profile(Parcel in) {
        nameOfUser = in.readString();
        jobTitle = in.readString();
        homeLocation = in.readString();
        workLocation = in.readString();
        interests = in.();
        in.readMap(hidingInfo, UserInfo.class.getClassLoader());
        byte tmpCarSharing = in.readByte();
        carSharing = tmpCarSharing == 0 ? null : tmpCarSharing == 1;
        addedProfilePicture = false;
        profilePicture = "default.png";
    }
    public Profile(String nameOfUser, String jobTitle, String homeLocation, String workLocation,
                   Interest[] interests, Map<UserInfo, Boolean> hidingInfo, Boolean carSharing) {
        this.nameOfUser = nameOfUser;
        this.jobTitle = jobTitle;
        this.homeLocation = homeLocation;
        this.workLocation = workLocation;
        this.interests = interests;
        this.hidingInfo = hidingInfo;
        this.carSharing = carSharing;
        profilePicture = "default.png";
        addedProfilePicture = false;
    }
    public Profile(String nameOfUser, String jobTitle, String homeLocation, String workLocation) {

        this.nameOfUser = nameOfUser;
        this.jobTitle = jobTitle;
        this.homeLocation = homeLocation;
        this.workLocation = workLocation;
        interests = new Interest[];
        profilePicture = "default.png"; //this must be the PATH to the image
        addedProfilePicture = false;
        carSharing = true;
        hidingInfo = getDefaultPermissions();
    }

    @Override
    public int describeContents() {
        return 0;
    }
    private Boolean addedProfilePicture;

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameOfUser);
        parcel.writeString(jobTitle);
        parcel.writeString(homeLocation);
        parcel.writeString(workLocation);
        parcel.writeStringArray(interests);
        parcel.writeMap(hidingInfo);
        parcel.writeByte((byte) (carSharing == null ? 0 : carSharing ? 1 : 2));
    }

    private Map<UserInfo, Boolean> getDefaultPermissions() {
        Map<UserInfo, Boolean> permissions = new HashMap<>();
        UserInfo[] infos = UserInfo.values();
        for (UserInfo info : infos) {
            permissions.put(info, false);
        }
        return permissions;
    }

    public Boolean isHidden(UserInfo info) {
        return hidingInfo.get(info);
    }

    //following are get and set functions for each variable
    public String getName() {
        return nameOfUser;
    }

    public void setName(String newName) {
        nameOfUser = newName;
    }

    public String getWorkLocation() {
        return workLocation;
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

    public void setWorkLocation(String newWork) {
        workLocation = newWork;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public Boolean hasUserSetProfilePicture() {
        return addedProfilePicture;
    }

    public Interest[] getInterests() {
        return interests;
    }

    public void addInterest(Interest interstToBeAdded){
        //check if user already has said interest
        boolean notAdded = true;
        for (Interest currentInterest: interests) {
            if (interstToBeAdded.equals(currentInterest)){
                notAdded = false;
            }
        }
        //if the user doesnt have said interest
        if (notAdded) {
            //add interest to array
            Interest newInterests[] = new Interest[interests.length + 1];
            newInterests = interests.clone();
            newInterests[newInterests.length - 1] = interstToBeAdded;
            interests = newInterests;
            //set in the interest that the user has it
            interstToBeAdded.setUserToInterest(this);
        }
    }

    public void setProfilePicture(String newPhoto) {
        profilePicture = newPhoto;
        addedProfilePicture = true;
    }

    enum UserInfo {
        NAME,
        JOB_TITLE,
        HOME_LOCATION,
        WORK_LOCATION,
        INTERESTS,
        CAR_SHARING
    }

    public Boolean isCarSharing(){
        return carSharing;
    }

    public void setCarSharing(Boolean setStatus){
        carSharing = setStatus;
    }


}
