package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MapFunction extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap; // Hold a reference to the GoogleMap

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_function);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

// מאזין ללחיצות על פריטים בתפריט התחתון
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);



        // Setup map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Setup search text field
        setupSearchField();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        // You might want to move the camera to a default location
    }

    private void setupSearchField() {
        EditText searchText = findViewById(R.id.search_text);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().toLowerCase().contains("bin") && googleMap != null) {
                    showAllBins(); // Show both bins
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void showAllBins() {
        // Assuming binLocation1 and binLocation2 are defined
        LatLng binLocation1 = new LatLng(32.071273, 34.786130); // Example location 1
        LatLng binLocation2 = new LatLng(32.081273, 34.776130); // Example location 2

        googleMap.clear(); // Clear existing markers if any
        googleMap.addMarker(new MarkerOptions().position(binLocation1).title("Bin 1"));
        googleMap.addMarker(new MarkerOptions().position(binLocation2).title("Bin 2"));

        // Adjust camera to show both bins
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(binLocation1);
        builder.include(binLocation2);
        LatLngBounds bounds = builder.build();
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
    }


    private boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home) { // החלף ב-ID המדויק של פריט "ראשי" שלך
            // טען מחדש את HomePage או נווט אליו אם אתה נמצא בפעילות אחרת
            Intent intent = new Intent(MapFunction.this, HomePage.class);
            startActivity(intent);
            return true;
            // אפשר להוסיף כאן מקרים נוספים לפריטים אחרים בתפריט
        }

        if (item.getItemId() == R.id.action_logout) { // החלף ב-ID המדויק של פריט "ראשי" שלך
            // טען מחדש את HomePage או נווט אליו אם אתה נמצא בפעילות אחרת
            Intent intent = new Intent(MapFunction.this, LogIn.class);
            startActivity(intent);
            return true;
            // אפשר להוסיף כאן מקרים נוספים לפריטים אחרים בתפריט
        }
        return false;
    }
}
