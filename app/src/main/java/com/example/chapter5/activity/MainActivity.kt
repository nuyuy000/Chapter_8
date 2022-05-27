package com.example.chapter5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter5.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object{
        const val SHARED_FILE = "sharedfile"
    }
}