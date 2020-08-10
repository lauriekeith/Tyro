package com.example.projecttyro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class CarSharing extends AppCompatActivity {

    TestUsers testUsers = new TestUsers();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_sharing);
        final EditText editText = findViewById(R.id.enterPostCode);


       List<Profile> users = testUsers.getUsers();
       LinearLayout linearLayout = findViewById(R.id.nearestPeople);
       for (Profile user: users) {
           String distance  = getDistance(editText, user.getPostCode()) + " miles";
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

    public String getDistance(EditText editText, String address) {
        try {
           return String.valueOf(convertMetersToMiles((double) new NetworkInfo().execute(address).get()));
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


}
