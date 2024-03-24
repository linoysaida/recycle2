package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SlashPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slpash_page);

        Handler handler=new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(SlashPage.this, LogIn.class));
            finish();
        }, 4000);
    }


}