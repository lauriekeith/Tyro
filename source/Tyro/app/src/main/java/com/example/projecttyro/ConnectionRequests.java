package com.example.projecttyro;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class ConnectionRequests extends AppCompatActivity {
    // sets up a dummy profile for testing, this will be removed during roll out
    protected Profile testingProfile = new Profile("Sam", "Tester", "London", "Birmingham");
    protected Profile requesterProfile = new Profile("Grant", "Debugger", "Halifax", "Glasgow");
    protected Profile requesterProfile2 = new Profile("Hugh", "Tester", "Cardiff", "London");
    protected Profile requesterProfile3 = new Profile("Sarah", "HR Consultant", "Manchester", "Radbroke");
    protected Profile requesterProfile4 = new Profile("Amelia", "Cyber Security", "Shrewbury", "Manchester");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_requests);

        testingProfile.requestToConnect(requesterProfile);
        testingProfile.requestToConnect(requesterProfile2);
        testingProfile.requestToConnect(requesterProfile3);
        testingProfile.requestToConnect(requesterProfile4);

        //set up a table layout
        final TableLayout tableLayout = findViewById(R.id.connectionRequestTable);

        //for loop to dynamically add requests -- will never be null
        for (int index = 0; index < testingProfile.getNumberOfRequests(); index ++) {
            //set up a new row
            final TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            //add row to the table
            tableLayout.addView(row);

            //get the next request
            final Profile nextRequest = testingProfile.getNextRequest(index);

            //add profile pic as first element
            ImageView profilePic = new ImageView(this);
            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            profilePic.setLayoutParams(params);
            //TODO this will need to change to allow custom profile pictures
            profilePic.setImageResource(R.drawable.ic_default_profile_picture);
            profilePic.getLayoutParams().width = 100;
            profilePic.getLayoutParams().height = 100;
            row.addView(profilePic);

            //display the users name
            TextView profileName = new TextView(this);
            profileName.setTextSize(18);
            profileName.setText(nextRequest.getName());
            row.addView(profileName);

            //accept button
            Button acceptButton = new Button(this);
            acceptButton.setText("Accept");
            acceptButton.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            testingProfile.acceptRequest(nextRequest);
                            tableLayout.removeView(row);
                        }
                    });
            row.addView(acceptButton);

            Button declineButton = new Button(this);
            declineButton.setText("Decline");
            declineButton.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            testingProfile.declineRequest(nextRequest);
                            tableLayout.removeView(row);
                        }
                    });
            row.addView(declineButton);


        }
    }
}