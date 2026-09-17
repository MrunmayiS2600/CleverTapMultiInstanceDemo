package com.example.clevertapmultiinstance

import android.app.Application
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.CleverTapInstanceConfig

class CleverTapMultiInstanceApp : Application() {

    companion object {
        // Project 1 = default instance, credentials come from AndroidManifest.xml meta-data.
        lateinit var project1Instance: CleverTapAPI

        // Project 2 = additional instance, credentials configured below.
        lateinit var project2Instance: CleverTapAPI
    }

    override fun onCreate() {
        super.onCreate()

        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG)

        project1Instance = CleverTapAPI.getDefaultInstance(applicationContext)!!

        val project2Config = CleverTapInstanceConfig.createInstance(
            applicationContext,
            BuildConfig.CLEVERTAP_PROJECT2_ACCOUNT_ID,
            BuildConfig.CLEVERTAP_PROJECT2_TOKEN
        )
        project2Config.setDebugLevel(CleverTapAPI.LogLevel.DEBUG)
        project2Instance = CleverTapAPI.instanceWithConfig(applicationContext, project2Config)!!

        // "App Launched" is a reserved/internal CleverTap event name and gets
        // silently rejected (wzrk_error 513) if pushed as a custom event.
        project1Instance.pushEvent("Demo App Opened")
        project2Instance.pushEvent("Demo App Opened")
    }
}
