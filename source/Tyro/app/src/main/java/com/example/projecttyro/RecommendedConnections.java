package com.example.projecttyro;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecommendedConnections extends AppCompatActivity {

    List<Profile> users = new TestUsers().getUsers();
    protected Profile testingProfile = new Profile("Sam", "Tester", "London", "Birmingham");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_connections);

        final ScrollView scrollView = (ScrollView) findViewById(R.id.recommendedConnectionsScrollView);
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.recommendedConnectionsTable);

        //give psuedoprofile a dummy interest
        Interest interest = new Interest("Football");
        testingProfile.addInterest(interest);

        //overwrite testing profile with real profile
        Intent intent = getIntent();
        /* If first time loading, no extras so will be working with test data */
        if (intent.hasExtra("profile")) {
            testingProfile = intent.getParcelableExtra("profile");
        }

        //set up navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        List<Profile> listOfRecommended = new ArrayList<Profile>();

        for (final Profile otherUser : users){
            for (Interest commonInterest : testingProfile.getInterests()){
                if(!otherUser.equals(testingProfile) && otherUser.getInterests().contains(commonInterest)
                        && !(listOfRecommended.contains(otherUser))){
                    //add user to the list
                    listOfRecommended.add(otherUser);
                    //set up a new row
                    final TableRow row = new TableRow(this);
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(lp);

                    //add row to the table
                    tableLayout.addView(row);

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
                    profileName.setText(otherUser.getName());
                    row.addView(profileName);

                    //add button -- allows user to request to add and undo that request also
                    final Button addButton = new Button(this);
                    addButton.setText("Connect");
                    addButton.setOnClickListener(
                            new View.OnClickListener() {
                                public void onClick(View v) {
                                    if(addButton.getText() == "Connect") {
                                        otherUser.requestToConnect(testingProfile);
                                        addButton.setText("Requested");
                                    }
                                    else{
                                        addButton.setText("Connect");
                                        otherUser.removeConnectionRequest(testingProfile);
                                    }
                                }
                            });
                    row.addView(addButton);
                }// if
            }//for
        }//for

        Button backButton = findViewById(R.id.recommendedConnectionsBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendedConnections.this, SearchUsersActivity.class);
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
                        case R.id.navigation_home:
                            intent = new Intent(RecommendedConnections.this, SearchUsersActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_profile_page:
                            intent = new Intent(RecommendedConnections.this, ProfileActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_connection_requests: //allows user to go back to home page instead of pressing back
                            intent = new Intent(RecommendedConnections.this, ConnectionRequests.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_car_sharing: //TODO when car sharing implimented
                            break;
                    }
                    return true;
                }
            };
}