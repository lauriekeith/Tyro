package com.example.projecttyro;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import static com.example.projecttyro.Profile.UserInfo.CAR_SHARING;
import static com.example.projecttyro.Profile.UserInfo.HOME_LOCATION;
import static com.example.projecttyro.Profile.UserInfo.INTERESTS;
import static com.example.projecttyro.Profile.UserInfo.JOB_TITLE;
import static com.example.projecttyro.Profile.UserInfo.WORK_LOCATION;

public class ProfileActivity extends AppCompatActivity {

    // sets up a dummy profile for testing, this will be removed during roll out
    protected Profile testingProfile = new Profile("Sam", "Tester", "London", "Birmingham");
//    protected Profile requesterProfile = new Profile("Grant", "Debugger", "Halifax", "Glasgow");
//    protected Profile requesterProfile2 = new Profile("Hugh", "Tester", "Cardiff", "London");
//    protected Profile requesterProfile3 = new Profile("Sarah", "HR Consultant", "Manchester", "Radbroke");
//    protected Profile requesterProfile4 = new Profile("Amelia", "Cyber Security", "Shrewbury", "Manchester");

    public void onClick(Button button, final Interest interest) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, InterestTagUsers.class);
                intent.putExtra("interest", interest);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        /* If first time loading, no extras so will be working with test data */
        if (intent.hasExtra("profile")) {
            testingProfile = intent.getParcelableExtra("profile");
        }

        //dummy connections
//        testingProfile.requestToConnect(requesterProfile);
//        testingProfile.requestToConnect(requesterProfile2);
//        testingProfile.requestToConnect(requesterProfile3);
//        testingProfile.requestToConnect(requesterProfile4);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.navigation_profile_page);


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

        List<Interest> interests = testingProfile.getInterests();
        if (!testingProfile.isHidden(INTERESTS)) {
            ScrollView scrollView = findViewById(R.id.interestScrollView);
            LinearLayout linearLayout = findViewById(R.id.interestLayoutView);
            for (Interest interest: interests) {
                Button button = new Button(this);
                button.setText(interest.toString());
                linearLayout.addView(button);
                onClick(button, interest);
//                interest.setUserToInterest(testingProfile);
            }
            interestsTitle.setText("Interests");
        }


        // logic to display if the user is willing to car share
        TextView carSharingTitle = (TextView) findViewById(R.id.carSharingTitle);
        Button carSharingStatus = findViewById(R.id.carSharingStatus);
        // hides the possibility if checked
        if (testingProfile.isHidden(CAR_SHARING)) {
            carSharingTitle.setVisibility(View.GONE);
            carSharingStatus.setVisibility(View.GONE);
        }
        // if not hidden
        else {
            carSharingTitle.setVisibility(View.VISIBLE);
            carSharingStatus.setVisibility(View.VISIBLE);
            // display title
            carSharingTitle.setText("Car Sharing");
            // display yes or no
            carSharingStatus.setText(testingProfile.isCarSharing() ? "Yes" : "No");
//            if (!testingProfile.isHidden(CAR_SHARING)) carSharingBody.setText("Yes");
//            else carSharingBody.setText("No");
        }

        // functionality to load profile picture
        ImageView profilePicture = (ImageView) findViewById(R.id.profilePicture);
        // if user has a custom profile picture
        if (testingProfile.hasUserSetProfilePicture())
            profilePicture.setImageBitmap(BitmapFactory.decodeFile(testingProfile.getProfilePicture()));
            // else make the profile picture the default one
        else profilePicture.setImageResource(R.drawable.ic_default_profile_picture);

        Button editProfile = findViewById(R.id.editProfile);
        editProfile.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                        intent.putExtra("profile", testingProfile);
                        startActivity(intent);
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
                            intent = new Intent(ProfileActivity.this, SearchUsersActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_connection_requests:
                            intent = new Intent(ProfileActivity.this, ConnectionRequests.class);
                            intent.putExtra("profile", testingProfile);
                            startActivity(intent);
                            break;

                        case R.id.navigation_profile_page: //do nothing, already there
                            break;

                        case R.id.navigation_car_sharing: //TODO when car sharing implimented
                            intent = new Intent(ProfileActivity.this, CarSharing.class);
                            startActivity(intent);
                            break;
                    }
                    return true;
                }
            };
}
