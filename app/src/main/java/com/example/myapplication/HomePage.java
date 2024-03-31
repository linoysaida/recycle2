package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

}
