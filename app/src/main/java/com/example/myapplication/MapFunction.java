package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MapFunction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_fanction);
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