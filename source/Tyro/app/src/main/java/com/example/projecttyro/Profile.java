package com.example.projecttyro;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    private List<Interest> interests;
    private List<Profile> connections;
    private List<Profile> pendingConnections;
    private Map<UserInfo, Boolean> hidingInfo = new HashMap<>();
    private String homeLocation;
    private String workLocation;
    private String postCode;



    /*
     * empty variables to store the profile information
     * These will all be initialised by the constructor or by functions
     * all are private and will be accessed by accessor functions
     * */

    private String nameOfUser;
    private String jobTitle;
    private String profilePicture;
    private Boolean carSharing;
    private int numberOfConnections;
    private int numberOfRequests;

    /* How a profile object is created when it is exchanged between activities */
    protected Profile(Parcel in) {
        nameOfUser = in.readString();
        jobTitle = in.readString();
        homeLocation = in.readString();
        workLocation = in.readString();
        interests = new ArrayList<Interest>();
        in.readTypedList(interests, Interest.CREATOR);

        connections = new ArrayList<Profile>();
        in.readTypedList(connections, Profile.CREATOR);

        //TODO doesnt seem to be working right... is this correct?
        pendingConnections = new ArrayList<Profile>();
        in.readTypedList(pendingConnections, Profile.CREATOR);

        in.readMap(hidingInfo, UserInfo.class.getClassLoader());
        byte tmpCarSharing = in.readByte();
        carSharing = tmpCarSharing == 0 ? null : tmpCarSharing == 1;
        numberOfConnections = in.readInt();
        numberOfRequests = in.readInt();
        addedProfilePicture = false;
        profilePicture = "default.png";
    }
    public Profile(String nameOfUser, String jobTitle, String homeLocation, String workLocation,
                   List<Interest> interests, Map<UserInfo, Boolean> hidingInfo, Boolean carSharing) {
        this.nameOfUser = nameOfUser;
        this.jobTitle = jobTitle;
        this.homeLocation = homeLocation;
        this.workLocation = workLocation;
        this.interests = interests;
        this.hidingInfo = hidingInfo;
        this.carSharing = carSharing;
        profilePicture = "default.png";
        addedProfilePicture = false;
        numberOfConnections = 0;
        numberOfRequests = 0;
        connections = new ArrayList<Profile>();
        pendingConnections = new ArrayList<Profile>();
    }
    public Profile(String nameOfUser, String jobTitle, String homeLocation, String workLocation) {

        this.nameOfUser = nameOfUser;
        this.jobTitle = jobTitle;
        this.homeLocation = homeLocation;
        this.workLocation = workLocation;
        interests = new ArrayList<>();
        profilePicture = "default.png"; //this must be the PATH to the image
        addedProfilePicture = false;
        carSharing = true;
        numberOfConnections = 0;
        numberOfRequests = 0;
        connections = new ArrayList<Profile>();
        pendingConnections = new ArrayList<Profile>();
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
        parcel.writeTypedList(interests);
        parcel.writeTypedList(connections);
        parcel.writeTypedList(pendingConnections);
        parcel.writeMap(hidingInfo);
        parcel.writeByte((byte) (carSharing == null ? 0 : carSharing ? 1 : 2));
        parcel.writeInt(numberOfConnections);
        parcel.writeInt(numberOfRequests);
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

    public int getNumberOfConnections(){
        return numberOfConnections;
    }

    public int getNumberOfRequests(){return numberOfRequests;}

    //adds user to requests list
    public void requestToConnect(Profile requester) {
        if (!connections.contains(requester) && !pendingConnections.contains(requester)) {
            pendingConnections.add(requester);
            numberOfRequests++;
        }
    }

    public void removeConnectionRequest(Profile requester){
        if (pendingConnections.contains(requester)) {
            pendingConnections.remove(requester);
            numberOfRequests--;
        }
    }

    //removes user from request list to display on pop up
    public Profile getNextRequest(int index){
        if (numberOfRequests > index){
            return pendingConnections.get(index);
        }
        else
            return null;
    }

    public boolean hasRequestFrom(Profile otherUser){
        return (pendingConnections.contains(otherUser));
    }

    public void acceptRequest(Profile otherUser){
        pendingConnections.remove(otherUser);
        numberOfRequests --;
        connect(otherUser);
    }

    public void declineRequest(Profile otherUser){
        pendingConnections.remove(otherUser);
        numberOfRequests --;
    }

    //connects both users on both ends in one call
    private void connect(Profile otherUser){
        addConnection(otherUser);
        otherUser.addConnection(this);
    }

    //disconnects both users on both ends in one call
    public void disconnect(Profile otherUser){
        removeConnection(otherUser);
        otherUser.removeConnection(this);
    }

    public boolean hasConnection(Profile otherUser){

        return connections.contains(otherUser);
    }

    //adds connection to the arraylist and increments numberOfConnections if the connection isnt already there
    public void addConnection(Profile otherUser){
        if (!hasConnection(otherUser)) {
            connections.add(numberOfConnections, otherUser);
            numberOfConnections++;
        }
    }

    //removes connection from the arraylist and decrements numberOfConnections if the connection exists
    public void removeConnection(Profile otherUser){
        if (numberOfConnections > 0 && hasConnection(otherUser)) {
            numberOfConnections--;
            connections.remove(otherUser);
        }
        //else do nothing
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

    public List<Interest> getInterests() {
        return interests;
    }

    public void addInterest(Interest interestToBeAdded){
        if (!interests.contains(interestToBeAdded)) {
            interests.add(interestToBeAdded);
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

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }
}
