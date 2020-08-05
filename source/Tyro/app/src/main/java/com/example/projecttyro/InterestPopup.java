package com.example.projecttyro;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InterestPopup extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interest_popup);

        Button addInterest = findViewById(R.id.addInterestButton);
        final EditText text =  findViewById(R.id.addInterestField);
        final ListView listView = (ListView) findViewById(R.id.interestsView);
        Interest interest = new Interest("hello");
        Interest interest2 = new Interest("Football");
        Interest interest3 = new Interest("Cricket");
        Set<Interest> allInterests = InterestStore.getInstance().getInterestList();


        final List<String> list = new ArrayList<>();
        for (Interest interest1: allInterests) {
            list.add(interest1.toString());
        }


        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setVisibility(View.INVISIBLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapter.getItem(i);
                text.setText(item);
                listView.setVisibility(View.INVISIBLE);
            }
        });

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);

                if (charSequence.length() == 0) {
                    listView.setVisibility(View.INVISIBLE);
                } else {
                    listView.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
