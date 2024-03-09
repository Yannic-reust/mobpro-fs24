package com.example.sw03_ui_state

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.mainAcivityView, MainFragment())
            .commit()

    }
}


