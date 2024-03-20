package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
public class ScanFunction extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // קבע את תוכן התצוגה לlayout של העמוד הנוכחי, לדוגמה activity_other
        setContentView(R.layout.activity_scan_function);

        // מצא את הכפתור שמפעיל את הסריקה, והקצה לו מאזין ללחיצה
        findViewById(R.id.scan).setOnClickListener(view -> openScanActivity());
    }

    private void openScanActivity() {
        Intent intent = new Intent(this, ScanFunction.class);
        startActivity(intent);
    }
}