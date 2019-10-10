package com.monkeyapp.numbers

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.google.android.gms.ads.MobileAds

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        // enable night mode when battery save mode is on
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)

        // set ads id
        MobileAds.initialize(this, "ca-app-pub-6498719425690429~1480158317")
    }
}