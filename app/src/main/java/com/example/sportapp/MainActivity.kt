package com.example.sportapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.google.firebase.remoteconfig.BuildConfig
import java.util.Locale

const val TAG = "my_tag"

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val urlFromSharedPref = sharedPref.getString("url", "") // getting a link locally
        var linkFirebaseRemoteConfig: String

        if (urlFromSharedPref.isNullOrBlank()) { // checking link from SharedPreferences
            linkFirebaseRemoteConfig = intent.getStringExtra("url").toString()

            if (linkFirebaseRemoteConfig.isEmpty() || checkIsEmulatorOrGoogle()) { // checking link from Firebase Remote Config and device
                supportFragmentManager.commit { // opening the stub
                    setReorderingAllowed(true)
                    add<WorkoutDetailsFragment>(R.id.fragment_workout_container)
                }
            } else {
                sharedPref.edit().putString("url", linkFirebaseRemoteConfig).apply() // saving link from Firebase Remote Config locally
                Toast.makeText(this, "opening WebView", Toast.LENGTH_SHORT).show()
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<WebViewFragment>(R.id.fragment_web_view_container)
                }
            }
        } else {
            if (hasInternet()) { // just works for minSdk >= 23
                Toast.makeText(this, "opening WebView", Toast.LENGTH_SHORT).show()
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<WebViewFragment>(R.id.fragment_web_view_container)
                }
                // opening WebView
            } else {
                supportFragmentManager.commit { // opening NoInternetFragment
                    setReorderingAllowed(true)
                    add<NoInternetFragment>(R.id.fragment_no_internet_container)
                }
            }
        }

    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun hasInternet(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        return networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET) ?: false
    }

    private fun checkIsEmulatorOrGoogle(): Boolean {
        if (BuildConfig.DEBUG) return false

        fun checkBasicEmulatorSigns(): Boolean =
            Build.FINGERPRINT.startsWith("generic") ||
                    Build.MODEL.contains("google_sdk") ||
                    Build.MODEL.contains("Emulator") ||
                    Build.MODEL.contains("Android SDK built for x86") ||
                    Build.MODEL.lowercase(Locale.getDefault()).contains("droid4x")

        fun checkGoogleBrand(): Boolean =
            Build.BRAND.contains("google")

        fun checkHardwareEquals(): Boolean =
            Build.HARDWARE == "goldfish" ||
                    Build.HARDWARE == "vbox86" ||
                    Build.HARDWARE.lowercase(Locale.getDefault()).contains("nox")

        fun checkBuildProduct(): Boolean =
            Build.PRODUCT == "sdk" ||
                    Build.PRODUCT == "google_sdk" ||
                    Build.PRODUCT == "sdk_x86" ||
                    Build.PRODUCT == "vbox86p" ||
                    Build.PRODUCT.lowercase(Locale.getDefault()).contains("nox")

        fun checkOthersParameters(): Boolean =
            Build.MANUFACTURER.contains("Genymotion") ||
                    Build.BOARD.lowercase(Locale.getDefault()).contains("nox") ||
                    Build.BOOTLOADER.lowercase(Locale.getDefault()).contains("nox")

        var result = checkBasicEmulatorSigns() ||
                checkGoogleBrand() ||
                checkHardwareEquals() ||
                checkBuildProduct() ||
                checkOthersParameters()

        if (result) return true

        result = result or (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))

        if (result) return true

        result = result or ("google_sdk".equals(Build.PRODUCT, ignoreCase = true))
        return result
    }

}