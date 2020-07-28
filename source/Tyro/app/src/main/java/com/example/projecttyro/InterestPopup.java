package com.example.projecttyro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InterestPopup extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interest_popup);

        Button addInterest = findViewById(R.id.addInterestButton);

        addInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String interestTag = ((EditText) findViewById(R.id.addInterestField)).getText().toString();
                Interest interest = new Interest(interestTag);
                Intent intent = new Intent();
                intent.putExtra("interest", interest);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
