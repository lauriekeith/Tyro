package com.example.projecttyro;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchUsersActivity extends AppCompatActivity {

    List<Profile> users = new TestUsers().getUsers();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchable_activity);

        SearchView searchView =  findViewById(R.id.searchView);
        final ListView listView = (ListView) findViewById(R.id.lv1);

        Interest interest = new Interest("Football");
        Interest interest2 = new Interest("Foosall");
        Interest interest3 = new Interest("Cricket");
        Set<Interest> allInterests = InterestStore.getInstance().getInterestList();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.navigation_home);

        Button recommendedConnectionButton = findViewById(R.id.recommendedConnectionsButton);
        recommendedConnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchUsersActivity.this, RecommendedConnections.class);
                startActivity(intent);
            }
        });

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
                Interest clickedInterest = new Interest(item);
                Intent intent = new Intent(SearchUsersActivity.this, InterestTagUsers.class);
                intent.putExtra("interest", clickedInterest);
                startActivity(intent);

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    (adapter).getFilter().filter(query);
                }else{
                    Toast.makeText(SearchUsersActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    listView.setVisibility(View.INVISIBLE);
                } else {
                    listView.setVisibility(View.VISIBLE);
                }

                adapter.getFilter().filter(newText);
                return false;
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
                            //do noting, already there
                            break;

                        case R.id.navigation_connection_requests:
                            intent = new Intent(SearchUsersActivity.this, ConnectionRequests.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_profile_page: //do nothing, already there
                            intent = new Intent(SearchUsersActivity.this, ProfileActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_car_sharing: //TODO when car sharing implimented
                            Intent intent2 = new Intent(SearchUsersActivity.this, CarSharing.class);
                            startActivity(intent2);
                            break;
                    }
                    return true;
                }
            };

}

