package com.example.projecttyro;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import static com.example.projecttyro.Profile.UserInfo.CAR_SHARING;
import static com.example.projecttyro.Profile.UserInfo.HOME_LOCATION;
import static com.example.projecttyro.Profile.UserInfo.INTERESTS;
import static com.example.projecttyro.Profile.UserInfo.JOB_TITLE;
import static com.example.projecttyro.Profile.UserInfo.WORK_LOCATION;

public class OtherUserProfile extends AppCompatActivity {

    // sets up a dummy profile for testing, this will be removed during roll out
    protected Profile testingProfile = new Profile("Sam", "Tester", "London", "Birmingham");
    protected Profile userProfile = new Profile("Grant", "Debugger", "Halifax", "Glasgow");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_profile);

        Intent intent = getIntent();
        /* If first time loading, no extras so will be working with test data */
        if (intent.hasExtra("profile")) {
            testingProfile = intent.getParcelableExtra("profile");
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
//        bottomNav.setSelectedItemId(R.id.navigation_connection_requests);

        // set up text view for name on the profile
        TextView profileName = (TextView) findViewById(R.id.profileName);
        // set that text to display the profile name
        profileName.setText(testingProfile.getName());

        // set up text view for connectionNumber on the profile
        TextView connectionsNumber = (TextView) findViewById(R.id.noOfConnections);
        // set that text to display the connection count
        connectionsNumber.setText(Integer.toString(testingProfile.getNumberOfConnections()));


        // the following views are only available if not hidden
        TextView profileLocation = (TextView) findViewById(R.id.profileLocation);
        profileLocation.setText(
                testingProfile.isHidden(HOME_LOCATION) ? " " : testingProfile.getHomeLocation());

        // the above is copied below
        TextView profileJobTitle = (TextView) findViewById(R.id.profileJobTitle);
        profileJobTitle.setText(
                testingProfile.isHidden(JOB_TITLE) ? " " : testingProfile.getJobTitle());

        TextView profileWorkLocation = (TextView) findViewById(R.id.profileWorkLocation);
        profileWorkLocation.setText(
                testingProfile.isHidden(WORK_LOCATION) ? " " : testingProfile.getWorkLocation());

        TextView interestsTitle = (TextView) findViewById(R.id.interestsTitle);
        TextView interestsBody = (TextView) findViewById(R.id.interestsBody);

        if (testingProfile.isHidden(INTERESTS)) {
            interestsBody.setText(" ");
            interestsTitle.setText(" ");
        }
        // if the user has interests, the first 4 will be displayed (if they have this many) - the
        // rest will be displayed in a "more" pop up, this will be added later
        else {
            // string to store the interests
            StringBuilder interestsToShow = new StringBuilder();
            // gets the new line operator for the given system
            String newLine = System.getProperty("line.separator");

            // switch to ensure no array out of bounds if the user has less than 4 interests
            List<Interest> interests = testingProfile.getInterests();

            if (interests == null) {
                interestsToShow = new StringBuilder("No interests recorded");
            }
            else {

                for (Interest interest: interests) {
                    interestsToShow.append(interest);
                    interestsToShow.append(newLine);
                }

//                switch (testingProfile.getInterests().length) {
//                    case 0:
//                        interestsToShow = "No interests recorded";
//                        break;
//                    case 1:
//                        interestsToShow = testingProfile.getInterests()[0] + newLine;
//                        break;
//                    case 2:
//                        interestsToShow =
//                                testingProfile.getInterests()[0]
//                                        + newLine
//                                        + testingProfile.getInterests()[1]
//                                        + newLine;
//                        break;
//                    case 3:
//                        interestsToShow =
//                                testingProfile.getInterests()[0]
//                                        + newLine
//                                        + testingProfile.getInterests()[1]
//                                        + newLine
//                                        + testingProfile.getInterests()[2]
//                                        + newLine;
//                        break;
//                    default:
//                        interestsToShow =
//                                testingProfile.getInterests()[0]
//                                        + newLine
//                                        + testingProfile.getInterests()[1]
//                                        + newLine
//                                        + testingProfile.getInterests()[2]
//                                        + newLine
//                                        + testingProfile.getInterests()[3]
//                                        + newLine;
//                        break;
//                }
            }
            interestsBody.setText(interestsToShow.toString());
            interestsTitle.setText("Interests");
        }

        // logic to display if the user is willing to car share
        TextView carSharingTitle = (TextView) findViewById(R.id.carSharingTitle);
        TextView carSharingBody = (TextView) findViewById(R.id.carSharingBody);
        // hides the possibility if checked
        if (testingProfile.isHidden(CAR_SHARING)) {
            carSharingTitle.setText(" ");
            carSharingBody.setText(" ");
        }
        // if not hidden
        else {
            // display title
            carSharingTitle.setText("Car Sharing");
            // display yes or no
            if (!testingProfile.isHidden(CAR_SHARING)) carSharingBody.setText("Yes");
            else carSharingBody.setText("No");
        }

        // functionality to load profile picture
        ImageView profilePicture = (ImageView) findViewById(R.id.profilePicture);
        // if user has a custom profile picture
        if (testingProfile.hasUserSetProfilePicture())
            profilePicture.setImageBitmap(BitmapFactory.decodeFile(testingProfile.getProfilePicture()));
            // else make the profile picture the default one
        else profilePicture.setImageResource(R.drawable.ic_default_profile_picture);

        final Button makeConnection = findViewById(R.id.makeConnection);

        //if user has a connection
        if (userProfile.hasConnection(testingProfile)){
            makeConnection.setText("Disconnect");

        }
        //else if requested say this
        else if (testingProfile.hasRequestFrom(userProfile)){
            makeConnection.setText("Requested");
        }
        //else say connect
        else{
            makeConnection.setText("Connect");
        }
        makeConnection.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        TextView connectionsNumber = (TextView) findViewById(R.id.noOfConnections);
                        //if user already has connection, disconnect on press
                        if (userProfile.hasConnection(testingProfile)){
                            userProfile.disconnect(testingProfile);
                            makeConnection.setText("Connect");

                        }
                        //else if requested and pressed, remove request
                        else if (testingProfile.hasRequestFrom(userProfile)){
                            testingProfile.declineRequest(userProfile);
                            makeConnection.setText("Connect");
                        }
                        //else make connection
                        else{
                            userProfile.requestToConnect(testingProfile);
                            makeConnection.setText("Requested");
                        }
                        connectionsNumber.setText(Integer.toString(testingProfile.getNumberOfConnections()));
                    }
                });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()){
                        case R.id.navigation_home: //TODO decide what home is
                            break;

                        case R.id.navigation_connection_requests:
                            intent = new Intent(OtherUserProfile.this, ConnectionRequests.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_profile_page: //do nothing, already there
                            intent = new Intent(OtherUserProfile.this, ProfileActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_car_sharing: //TODO when car sharing implimented
                            break;
                    }
                    return true;
                }
            };
}