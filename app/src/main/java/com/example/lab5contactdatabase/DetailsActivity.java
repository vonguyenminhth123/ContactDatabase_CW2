package com.example.lab5contactdatabase;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Person;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
public class DetailsActivity extends AppCompatActivity {
    private ArrayList<PersonModal> personModals;
    private DatabaseHelper databaseHelper;
    private PersonAdapter personAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        personModals = new ArrayList<>();
        databaseHelper = new DatabaseHelper(DetailsActivity.this);
        // getting person array list from databasehelper class.
        personModals = databaseHelper.getDetails();
        // on below line passing array list to adapter class.
        personAdapter = new PersonAdapter(personModals, this);
        recyclerView = findViewById(R.id.recyclerview);
        for (PersonModal person : personModals) {
            Log.d("Person Details", person.getName()); // Log person details to verify if data is retrieved
        }
        // setting layout manager for recycler view.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // setting our adapter to recycler view.
        recyclerView.setAdapter(personAdapter);
    }
}