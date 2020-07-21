package com.example.projecttyro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class EditProfileActivity extends AppCompatActivity {

    private Profile profile = new Profile("Indip", "test@barclays.com",
            "1234", "tester", "knutsford",
            "radbroke", new Date(1999, 9, 18));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_editprofilepage);

        Button button =  findViewById(R.id.completeProfile);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                intent.putExtra("name", ((EditText) findViewById(R.id.editName)).getText().toString());
                intent.putExtra("nameVisible", ((Switch)findViewById(R.id.switch1)).isChecked());
                intent.putExtra("jobTitle", ((EditText) findViewById(R.id.editJobTitle)).getText().toString());
                intent.putExtra("jobTitleVisible", ((Switch)findViewById(R.id.switch2)).isChecked());
                intent.putExtra("location", ((EditText) findViewById(R.id.editLocation)).getText().toString());
                intent.putExtra("locationVisible", ((Switch)findViewById(R.id.switch3)).isChecked());
                intent.putExtra("carSharing", ((EditText) findViewById(R.id.editCarSharing)).getText().toString());
                intent.putExtra("carSharingVisible", ((Switch)findViewById(R.id.switch5)).isChecked());
                startActivity(intent);
            }
        });



    }
}
