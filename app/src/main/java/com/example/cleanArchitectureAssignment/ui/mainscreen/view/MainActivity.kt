package com.example.cleanArchitectureAssignment.ui.mainscreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cleanArchitectureAssignment.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}