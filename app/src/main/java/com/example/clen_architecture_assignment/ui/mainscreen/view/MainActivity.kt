package com.example.clen_architecture_assignment.ui.mainscreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clen_architecture_assignment.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}