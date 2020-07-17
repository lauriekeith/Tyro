package com.example.projecttyro;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Date;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //sets up a dummy profile for testing, this will be removed during roll out
        Profile testingProfile = new Profile("test profile", "test@barclays.com",
                "1234", "tester", "knutsford",
                "radbroke", new Date(1999, 9, 18));

        //set up text view for name on the profile
        TextView profileName = (TextView) findViewById(R.id.profileName);
        //set that text to display the profile name
        profileName.setText(testingProfile.getName());

        //the above is copied below
        TextView profileLocation = (TextView) findViewById(R.id.profileLocation);
        profileLocation.setText(testingProfile.getHomeLocation());

        TextView profileJobTitle = (TextView) findViewById(R.id.profileJobTitle);
        profileJobTitle.setText(testingProfile.getJobTitle());

        TextView profileWorkLocation = (TextView) findViewById(R.id.profileWorkLocation);
        profileWorkLocation.setText(testingProfile.getWorkLocation());


        //functionality to load profile picture
       // ImageView profilePicture = (ImageView) findViewById(R.id.profilePicture);
        //profilePicture.set

    }

}
