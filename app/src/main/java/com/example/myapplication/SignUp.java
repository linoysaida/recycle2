package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        EditText password2 = findViewById(R.id.password2);
        CheckBox showPasswordCheckbox = findViewById(R.id.showPasswordCheckbox);
        Button signUpButton = findViewById(R.id.loginbtn);

        showPasswordCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                password.setInputType(InputType.TYPE_CLASS_TEXT);
                password2.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                password2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            password.setSelection(password.getText().length());
            password2.setSelection(password2.getText().length());
        });

        signUpButton.setOnClickListener(v -> {
            // אימות הקלט
            if (validateInput(username, password, password2)) {
                // הודעת אישור
                Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                // מעבר לעמוד ההתחברות
                Intent intent = new Intent(SignUp.this, LogIn.class);
                startActivity(intent);
                finish(); // סגירת פעילות ההרשמה
            }
        });
    }

    private boolean validateInput(EditText username, EditText password, EditText password2) {
        // בדיקה אם כל השדות מלאים והסיסמאות תואמות
        if (username.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty() ||
                password2.getText().toString().isEmpty()) {
            Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.getText().toString().equals(password2.getText().toString())) {
            Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
