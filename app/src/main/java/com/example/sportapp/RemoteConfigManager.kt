package com.example.sportapp

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class RemoteConfigManager {
    private val mFirebaseRemoteConfig = Firebase.remoteConfig
    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 3600
    }

    fun initFRC() {
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mFirebaseRemoteConfig.activate()
                } else {
                    Log.e(TAG, task.exception.toString())
                }
            }
    }

    fun getValueFromRFC(key: String): String {
        return mFirebaseRemoteConfig.getString(key)
    }
}

