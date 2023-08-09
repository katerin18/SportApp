package com.example.sportapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // here is RemoteConfigManager logic
        val mFirebaseRemoteConfig = Firebase.remoteConfig
        var linkFirebaseRemoteConfig: String
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
            build()
        }

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mFirebaseRemoteConfig.activate()
                    linkFirebaseRemoteConfig = mFirebaseRemoteConfig.getString("url")
                    onUrlFetched(linkFirebaseRemoteConfig)

                } else {
                    Log.e(TAG, task.exception.toString())
                }

            }
    }

    private fun onUrlFetched(url: String) {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
            finish()
        }, 1000)
    }
}