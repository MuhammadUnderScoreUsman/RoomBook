package com.mohammadosman.roomsocialrdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.mohammadosman.roomsocialrdb.R
import com.mohammadosman.roomsocialrdb.ui.auth.SignInFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<SignInFragment>(R.id.fragment_container_view)
            }
        }
    }
}