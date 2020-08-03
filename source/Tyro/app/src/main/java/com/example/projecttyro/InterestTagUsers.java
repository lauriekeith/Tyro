package com.example.projecttyro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Set;

public class InterestTagUsers extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_tag);

        Intent intent = getIntent();
        Interest interest = intent.getParcelableExtra("interest");
        TextView view = findViewById(R.id.interestTagTitle);
        assert interest != null;
        view.setText(interest.toString());
        List<Profile> users = new TestUsers().getUsers();
        LinearLayout linearLayout = findViewById(R.id.interestTagLayout);
        // TODO: Connect tag with all profiles - allows constant time access.
        for (Profile user : users) {
            if (user.getInterests().contains(interest)) {
                Button button = new Button(this);
                button.setText(user.getName());
                linearLayout.addView(button);
            }
        }


    }
}

