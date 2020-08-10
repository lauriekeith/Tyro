package com.example.projecttyro;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;


public class CarSharing extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_sharing);
        EditText editText = findViewById(R.id.carSharingDistance);
        getDistance(editText, "SM13JT");
        editText = findViewById(R.id.carSharingDistance2);
        getDistance(editText, "SM13DY");

    }

    public void getDistance(EditText editText, String address) {
        try {
            editText.setText(String.valueOf(new NetworkInfo().execute(address).get()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
