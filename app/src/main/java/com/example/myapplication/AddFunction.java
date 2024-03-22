package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class AddFunction extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_function);

        EditText productName = findViewById(R.id.productName);
        MaterialButton scanButton = findViewById(R.id.scanButton);
        MaterialButton addButton = findViewById(R.id.addButton);
        Spinner materialSpinner = findViewById(R.id.materialSpinner);

        // Populate the spinner with material options
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.material_options));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materialSpinner.setAdapter(adapter);

        scanButton.setOnClickListener(v -> {
            Toast.makeText(AddFunction.this,"product scaned", Toast.LENGTH_SHORT).show();
        });

        addButton.setOnClickListener(v -> {
            String nameInput = productName.getText().toString();
            String materialSelected = materialSpinner.getSelectedItem().toString();

            if (nameInput.isEmpty() || materialSelected.equals("Select Material")) {
                Toast.makeText(AddFunction.this, "all fields above must be field", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(AddFunction.this, "product added", Toast.LENGTH_SHORT).show();
            }
        });


    }
}