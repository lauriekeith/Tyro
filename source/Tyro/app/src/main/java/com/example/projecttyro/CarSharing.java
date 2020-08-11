package com.example.projecttyro;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class CarSharing extends AppCompatActivity {

    TestUsers testUsers = new TestUsers();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_sharing);
        findViewById(R.id.carSharingPageStatus).setVisibility(View.INVISIBLE);
        Button findColleagues = findViewById(R.id.findNearbyColleagues);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.navigation_car_sharing);

        findColleagues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getColleagues();
            }
        });





    }

    public String getDistance(String homeAddress, String address) {
        try {
           return String.valueOf(convertMetersToMiles((double) new NetworkInfo().execute(homeAddress, address).get()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    private double convertMetersToMiles(Double meters) {
        return Math.round(meters / 1600 * 100.0) / 100.0 ;
    }

    public void getColleagues() {
        findViewById(R.id.carSharingPageStatus).setVisibility(View.VISIBLE);
        final EditText editText = findViewById(R.id.enterPostCode);
        String homeAddress = editText.getText().toString();
        List<Profile> users = testUsers.getUsers();
        LinearLayout linearLayout = findViewById(R.id.nearestPeople);
        for (Profile user: users) {
            String distance  = getDistance(homeAddress, user.getPostCode()) + " miles";
            Button button1 = new Button(this);
            button1.setText(user.getName());

            Button button2 = new Button(this);
            button2.setText(distance);

            Button button3 = new Button(this);
            button3.setText(user.isCarSharing() ? "YES" : "NO");

            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.addView(button1);
            layout.addView(button2);
            layout.addView(button3);
            linearLayout.addView(layout);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            intent = new Intent(CarSharing.this, SearchUsersActivity.class);
                            startActivity(intent);

                        case R.id.navigation_connection_requests:
                            intent = new Intent(CarSharing.this, ConnectionRequests.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_profile_page: //do nothing, already there
                            intent = new Intent(CarSharing.this, ProfileActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_car_sharing: //TODO when car sharing implimented
                            break;
                    }
                    return true;
                }
            };


}
