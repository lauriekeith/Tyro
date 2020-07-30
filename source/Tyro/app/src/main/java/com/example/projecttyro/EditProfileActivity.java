package com.example.projecttyro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

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

    private static final int INTEREST_REQUEST_CODE = 0;
    private List<Interest> interests = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_editprofilepage);
        Intent intent = getIntent();
        final Profile profile = intent.getParcelableExtra("profile");
        assert profile != null;
        populateProfileInfo(profile);
        getPermissionInfo(profile);

        onAddingInterest();
        onCompleteProfile();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTEREST_REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            Interest newInterest = data.getParcelableExtra("interest");
            interests.add(newInterest);
            LinearLayout linearLayout = findViewById(R.id.interestsHolder);
            Button button = new Button(this);
            assert newInterest != null;
            button.setText(newInterest.toString());
            linearLayout.addView(button);

        }


//        onAddingInterest();
//        onCompleteProfile();

    }

    public void onAddingInterest() {
        Button addInterest = findViewById(R.id.addInterest);
        addInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this, InterestPopup.class);
                startActivityForResult(intent, INTEREST_REQUEST_CODE);
            }
        });
    }

    public void onCompleteProfile() {
        Button button = findViewById(R.id.completeProfile);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);

                /* Updated Profile Info to return user to Profile Page */

                /* Creating all fields necessary for a profile */

                String name = ((EditText) findViewById(R.id.editName)).getText().toString();
                String jobTitle = ((EditText) findViewById(R.id.editJobTitle)).getText().toString();
//                List<Interest> interests = new ArrayList<>();
//                Interest testInterest = new Interest("Empty Interest");
//                interests.add(testInterest);
//                String[] interest = new String[]{((TextView) findViewById(R.id.editInterest)).getText().toString()};
                String workLocation = ((EditText) findViewById(R.id.editWorkLocation)).getText().toString();
                String homeLocation = ((EditText) findViewById(R.id.editHomeLocation)).getText().toString();
                Boolean carSharing = ( (ToggleButton) findViewById(R.id.carSharingStatus)).isChecked();


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
                // TODO: profile should be connected to the Interest tags it has.
                intent.putExtra("profile", profile);
                startActivity(intent);
            }
        });
    }

    /* Two helpers to populate the screen with the appropriate Profile info */

    private void populateProfileInfo(Profile profile) {
        ((TextView) findViewById(R.id.editName)).setText(profile.getName());
        ((TextView) findViewById(R.id.editJobTitle)).setText(profile.getJobTitle());
        interests = profile.getInterests();
        LinearLayout linearLayout = findViewById(R.id.interestsHolder);
        for (Interest interest : interests) {
            Button button = new Button(this);
            button.setText(interest.toString());
            linearLayout.addView(button);
        }
//        Interest test = new Interest("test Interest");
//        ((TextView) findViewById(R.id.editInterest)).setText(interests.size() > 0 ? interests.get(0).toString() : test.toString());
        ((TextView) findViewById(R.id.editWorkLocation)).setText(profile.getWorkLocation());
        ((TextView) findViewById(R.id.editHomeLocation)).setText(profile.getHomeLocation());
        ((ToggleButton) findViewById(R.id.carSharingStatus)).setChecked(profile.isCarSharing());
    }


    private void getPermissionInfo(Profile profile) {
        ((Switch) findViewById(R.id.jobTitleSwitch)).setChecked(profile.isHidden(JOB_TITLE));
        ((Switch) findViewById(R.id.homeLocationSwitch)).setChecked(profile.isHidden(HOME_LOCATION));
        ((Switch) findViewById(R.id.workLocationSwitch)).setChecked(profile.isHidden(WORK_LOCATION));
        ((Switch) findViewById(R.id.interestSwitch)).setChecked(profile.isHidden(INTERESTS));
        ((Switch) findViewById(R.id.carSharingSwitch)).setChecked(profile.isHidden(CAR_SHARING));
    }


}
