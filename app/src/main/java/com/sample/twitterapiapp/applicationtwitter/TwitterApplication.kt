package com.sample.twitterapiapp.applicationtwitter

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.sample.twitterapiapp.applicationtwitter.prefs.oauth_secret
import com.sample.twitterapiapp.applicationtwitter.prefs.oauth_token
import com.sample.twitterapiapp.applicationtwitter.prefs.preferences

class TwitterApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (!preferences.oauth_token.isNullOrEmpty() && !preferences.oauth_secret.isNullOrEmpty()){
            AppConstants.OAUTH_TOKEN = preferences.oauth_token ?: ""
            AppConstants.OAUTH_TOKEN_SECRET = preferences.oauth_secret ?: ""
        }

        Fresco.initialize(this)
    }
}