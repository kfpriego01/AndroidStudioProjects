package com.example.idle_parilla_pepito_poliquit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DeskTrackerApplication : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set your layout (use the proper activity layout, not item layout)
        setContentView(R.layout.item_desk)
    }
}