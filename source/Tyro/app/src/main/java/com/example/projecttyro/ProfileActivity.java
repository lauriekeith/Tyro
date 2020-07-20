package com.example.projecttyro;

import android.graphics.BitmapFactory;
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

        //the following views are only available if not hidden
        TextView profileLocation = (TextView) findViewById(R.id.profileLocation);
        if (testingProfile.homeLocationIsHidden()){
            profileLocation.setText(" ");
        }
        else
            profileLocation.setText(testingProfile.getHomeLocation());

        //the above is copied below
        TextView profileJobTitle = (TextView) findViewById(R.id.profileJobTitle);
        if (testingProfile.jobTitleIsHidden())
            profileJobTitle.setText(" ");
        else
            profileJobTitle.setText(testingProfile.getJobTitle());

        TextView profileWorkLocation = (TextView) findViewById(R.id.profileWorkLocation);
        if (testingProfile.workLocationIsHidden())
            profileWorkLocation.setText(" ");
        else
            profileWorkLocation.setText(testingProfile.getWorkLocation());

        TextView interestsTitle = (TextView) findViewById(R.id.interestsTitle);
        TextView interestsBody = (TextView) findViewById(R.id.interestsBody);
        if (testingProfile.interestsAreHidden()) {
            interestsBody.setText(" ");
            interestsTitle.setText(" ");
        }
        //if the user has interests, the first 4 will be displayed (if they have this many) - the
        //rest will be displayed in a "more" pop up, this will be added later
        else{
            //string to store the interests
            String interestsToShow;
            //gets the new line operator for the given system
            String newLine = System.getProperty("line.separator");

            //switch to ensure no array out of bounds if the user has less than 4 interests
            switch (testingProfile.getInterests().length){
                case 0:
                    interestsToShow = "No interests recorded";
                    break;
                case 1:
                    interestsToShow = testingProfile.getInterests()[0] + newLine;
                    break;
                case 2:
                    interestsToShow = testingProfile.getInterests()[0] + newLine
                            + testingProfile.getInterests()[1] + newLine;
                    break;
                case 3:
                    interestsToShow = testingProfile.getInterests()[0] + newLine
                            + testingProfile.getInterests()[1] + newLine
                            + testingProfile.getInterests()[2] + newLine;
                    break;
                default:
                    interestsToShow = testingProfile.getInterests()[0] + newLine
                            + testingProfile.getInterests()[1] + newLine
                            + testingProfile.getInterests()[2] + newLine
                            + testingProfile.getInterests()[3] + newLine;
                    break;
            }
            interestsBody.setText(interestsToShow);
            interestsTitle.setText("Interests");
        }

        //logic to display if the user is willing to car share
        TextView carSharingTitle = (TextView) findViewById(R.id.carSharingTitle);
        TextView carSharingBody = (TextView) findViewById(R.id.carSharingBody);
        //hides the possibility if checked
        if(testingProfile.carSharingIsHidden()){
            carSharingTitle.setText(" ");
            carSharingBody.setText(" ");
        }
        //if not hidden
        else{
            //display title
            carSharingTitle.setText("Car Sharing");
            //display yes or no
            if(testingProfile.isCarSharing())
                carSharingBody.setText("Yes");
            else
                carSharingBody.setText("No");
        }


        //functionality to load profile picture
        ImageView profilePicture = (ImageView) findViewById(R.id.profilePicture);
        //if user has a custom profile picture
        if(testingProfile.hasUserSetProfilePicture())
            profilePicture.setImageBitmap(BitmapFactory.decodeFile(testingProfile.getProfilePicture()));
        //else make the profile picture the default one
        else
            profilePicture.setImageResource(R.drawable.ic_default_profile_picture);

    }

}
