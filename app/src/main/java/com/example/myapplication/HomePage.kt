package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.myapplication.R.layout.homepage


class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(homepage)
        val scanCard = findViewById<CardView>(R.id.scan)
        val searchCard = findViewById<CardView>(R.id.search)
        val addCard = findViewById<CardView>(R.id.add)
        val MapCard = findViewById<CardView>(R.id.map)
        scanCard.setOnClickListener {
            val intent = Intent(this@HomePage, ScanActivity::class.java)
            startActivity(intent)
        }
        searchCard.setOnClickListener {
            val intent = Intent(this@HomePage, SearchActivity::class.java)
            startActivity(intent)
        }

        scanCard.setOnClickListener {
            val intent = Intent(this@HomePage, AddActivity::class.java)
            startActivity(intent)
        }

        scanCard.setOnClickListener {
            val intent = Intent(this@HomePage, MapActivity::class.java)
            startActivity(intent)
        }
    }
}