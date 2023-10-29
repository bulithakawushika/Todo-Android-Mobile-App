package com.example.todoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * This is the main activity of the ToDo application.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view to the activity_main.xml layout.
        setContentView(R.layout.activity_main)
    }
}