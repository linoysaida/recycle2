package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage1); // Make sure R.layout.homepage matches your XML file name
        // Find CardViews by their ID
        CardView addCard = findViewById(R.id.add);
        CardView searchCard = findViewById(R.id.search);
        CardView mapCard = findViewById(R.id.map);
        CardView scanCard = findViewById(R.id.scan);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

// מאזין ללחיצות על פריטים בתפריט התחתון
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);



        // Set click listeners for each CardView to start the respective activities
        addCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, AddFunction.class);
            startActivity(intent);
        });

        searchCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, SearchFunction.class);
            startActivity(intent);
        });

        mapCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, MapFunction.class);
            startActivity(intent);
        });

        scanCard.setOnClickListener(v -> {

            Intent intent = new Intent(HomePage.this, ScanFunction.class);
            startActivity(intent);
        });



    }

    private boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home) { // החלף ב-ID המדויק של פריט "ראשי" שלך
            // טען מחדש את HomePage או נווט אליו אם אתה נמצא בפעילות אחרת
            Intent intent = new Intent(HomePage.this, HomePage.class);
            startActivity(intent);
            return true;
            // אפשר להוסיף כאן מקרים נוספים לפריטים אחרים בתפריט
        }

        if (item.getItemId() == R.id.action_logout) { // החלף ב-ID המדויק של פריט "ראשי" שלך
            // טען מחדש את HomePage או נווט אליו אם אתה נמצא בפעילות אחרת
            Intent intent = new Intent(HomePage.this, LogIn.class);
            startActivity(intent);
            return true;
            // אפשר להוסיף כאן מקרים נוספים לפריטים אחרים בתפריט
        }
        return false;
    }
}
