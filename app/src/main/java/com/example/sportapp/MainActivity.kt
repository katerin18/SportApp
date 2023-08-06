package com.example.sportapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workoutDetailsFragment = WorkoutDetails()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_workout_container, workoutDetailsFragment)
            .commit()
    }
}