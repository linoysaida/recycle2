package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class AddFunction extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 101;

    private MaterialButton scanButton;
    private Spinner materialSpinner;
    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_function);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

// מאזין ללחיצות על פריטים בתפריט התחתון
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);


        scanButton = findViewById(R.id.scanButton);
        MaterialButton addButton = findViewById(R.id.addButton);
        materialSpinner = findViewById(R.id.materialSpinner);
        categorySpinner = findViewById(R.id.productCategory);

        // Populate the spinner with material options
        ArrayAdapter<String> materialAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.material_options));
        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materialSpinner.setAdapter(materialAdapter);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.category_options));
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        scanButton.setOnClickListener(v -> {
            if (checkCameraPermission()) {
                openCamera();
            } else {
                requestCameraPermission();
            }
        });

        addButton.setOnClickListener(v -> {
            String categorySelected = categorySpinner.getSelectedItem().toString();
            String materialSelected = materialSpinner.getSelectedItem().toString();

            if (categorySelected.equals("בחר קטגוריה") || materialSelected.equals("בחר סוג אריזה")) {
                Toast.makeText(AddFunction.this, "all fields above must be filled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddFunction.this, "product added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }





    private boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home) { // החלף ב-ID המדויק של פריט "ראשי" שלך
            // טען מחדש את HomePage או נווט אליו אם אתה נמצא בפעילות אחרת
            Intent intent = new Intent(AddFunction.this, HomePage.class);
            startActivity(intent);
            return true;
            // אפשר להוסיף כאן מקרים נוספים לפריטים אחרים בתפריט
        }

        if (item.getItemId() == R.id.action_logout) { // החלף ב-ID המדויק של פריט "ראשי" שלך
            // טען מחדש את HomePage או נווט אליו אם אתה נמצא בפעילות אחרת
            Intent intent = new Intent(AddFunction.this, LogIn.class);
            startActivity(intent);
            return true;
            // אפשר להוסיף כאן מקרים נוספים לפריטים אחרים בתפריט
        }
        return false;
    }
}