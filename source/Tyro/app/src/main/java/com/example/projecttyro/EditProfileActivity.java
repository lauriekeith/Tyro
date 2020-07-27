package com.example.projecttyro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.projecttyro.Profile.UserInfo.CAR_SHARING;
import static com.example.projecttyro.Profile.UserInfo.HOME_LOCATION;
import static com.example.projecttyro.Profile.UserInfo.INTERESTS;
import static com.example.projecttyro.Profile.UserInfo.JOB_TITLE;
import static com.example.projecttyro.Profile.UserInfo.WORK_LOCATION;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_editprofilepage);
        Intent intent = getIntent();
        Profile profile = intent.getParcelableExtra("profile");
        assert profile != null;
        populateProfileInfo(profile);
        getPermissionInfo(profile);


        Button button = findViewById(R.id.completeProfile);

        // set up text view for connectionNumber on the profile
        TextView connectionsNumber = (TextView) findViewById(R.id.noOfConnections);
        // set that text to display the connection count
        connectionsNumber.setText(Integer.toString(profile.getNumberOfConnections()));

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);

                /* Updated Profile Info to return user to Profile Page */

                /* Creating all fields necessary for a profile */

                String name = ((EditText) findViewById(R.id.editName)).getText().toString();
                String jobTitle = ((EditText) findViewById(R.id.editJobTitle)).getText().toString();
                List<Interest> interests = new ArrayList<>();
                Interest testInterest = new Interest("Empty Interest");
                interests.add(testInterest);
//                String[] interest = new String[]{((TextView) findViewById(R.id.editInterest)).getText().toString()};
                String workLocation = ((EditText) findViewById(R.id.editWorkLocation)).getText().toString();
                String homeLocation = ((EditText) findViewById(R.id.editHomeLocation)).getText().toString();
                Boolean carSharing = ((EditText) findViewById(R.id.editCarSharing)).getText().toString().equals("Yes");

                Boolean jobVisible = ((Switch) findViewById(R.id.jobTitleSwitch)).isChecked();
                Boolean homeLocationVisible = ((Switch) findViewById(R.id.homeLocationSwitch)).isChecked();
                Boolean workLocationVisible = ((Switch) findViewById(R.id.workLocationSwitch)).isChecked();
                Boolean carSharingVisible = ((Switch) findViewById(R.id.carSharingSwitch)).isChecked();
                Boolean interestsVisible = ((Switch) findViewById(R.id.interestSwitch)).isChecked();
                Map<Profile.UserInfo, Boolean> hidingInfo = new HashMap<>();
                hidingInfo.put(JOB_TITLE, jobVisible);
                hidingInfo.put(HOME_LOCATION, homeLocationVisible);
                hidingInfo.put(WORK_LOCATION, workLocationVisible);
                hidingInfo.put(CAR_SHARING, carSharingVisible);
                hidingInfo.put(INTERESTS, interestsVisible);

                Profile profile = new Profile(name, jobTitle, homeLocation, workLocation, interests, hidingInfo, carSharing);
                intent.putExtra("profile", profile);
                startActivity(intent);
            }
        });

    }

    /* Two helpers to populate the screen with the appropriate Profile info */

    private void populateProfileInfo(Profile profile) {
        ((TextView) findViewById(R.id.editName)).setText(profile.getName());
        ((TextView) findViewById(R.id.editJobTitle)).setText(profile.getJobTitle());
        Interest test = new Interest("test Interest");
        ((TextView) findViewById(R.id.editInterest)).setText(test.toString());
        ((TextView) findViewById(R.id.editWorkLocation)).setText(profile.getWorkLocation());
        ((TextView) findViewById(R.id.editHomeLocation)).setText(profile.getHomeLocation());
        ((TextView) findViewById(R.id.editCarSharing)).setText(profile.isCarSharing() ? "Yes" : "No");
    }

    private void getPermissionInfo(Profile profile) {
        ((Switch) findViewById(R.id.jobTitleSwitch)).setChecked(profile.isHidden(JOB_TITLE));
        ((Switch) findViewById(R.id.homeLocationSwitch)).setChecked(profile.isHidden(HOME_LOCATION));
        ((Switch) findViewById(R.id.workLocationSwitch)).setChecked(profile.isHidden(WORK_LOCATION));
        ((Switch) findViewById(R.id.interestSwitch)).setChecked(profile.isHidden(INTERESTS));
        ((Switch) findViewById(R.id.carSharingSwitch)).setChecked(profile.isHidden(CAR_SHARING));
    }


}
