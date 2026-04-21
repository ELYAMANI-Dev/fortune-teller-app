package com.moroccan.fortuneteller

import android.app.Application
import com.moroccan.fortuneteller.analytics.MixpanelAnalytics
import com.google.android.gms.ads.MobileAds

class FortuneTellerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        MixpanelAnalytics.init(this)
        MixpanelAnalytics.trackAppOpen()
    }
}
