package com.example.myapplication;

import android.annotation.SuppressLint;
        import android.app.Dialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.InputType;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

        import com.google.android.material.button.MaterialButton;
        import com.google.firebase.FirebaseApp;
        import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        MaterialButton loginbtn = findViewById(R.id.loginbtn);
        CheckBox showPasswordCheckbox = findViewById(R.id.showPasswordCheckbox);


        // Initialize Firebase
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this);
        }

        // Now you can use FirebaseAuth
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        TextView signupTextView = findViewById(R.id.signup);
        signupTextView.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, SignUp.class);
            startActivity(intent);
        });






        showPasswordCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                password.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            password.setSelection(password.getText().length());
        });

        TextView forgetPasswordTextView = findViewById(R.id.forgetpass);
        forgetPasswordTextView.setOnClickListener(v -> {
            // Show the popup window
            showPopupWindow();
        });
    }

    private void showPopupWindow() {
        // Inflate the layout for the popup window
        @SuppressLint("InflateParams") View popupView = getLayoutInflater().inflate(R.layout.activity_main2, null);

        // Create a new dialog
        Dialog popupDialog = new Dialog(LogIn.this);
        popupDialog.setContentView(popupView);
        popupDialog.setCancelable(true);

        // Find views in the popup layout
        EditText phoneNumberEditText = popupView.findViewById(R.id.phoneNumberEditText);
        Button submitButton = popupView.findViewById(R.id.submitButton);

        // Set onClickListener for the submit button
        submitButton.setOnClickListener(v -> {
            // Handle the submission of the phone number here
            String phoneNumber = phoneNumberEditText.getText().toString();
            // Perform desired action with the phone number (e.g., send reset link)
            // Dismiss the dialog
            popupDialog.dismiss();
        });

        // Show the popup dialog
        popupDialog.show();
    }


}

