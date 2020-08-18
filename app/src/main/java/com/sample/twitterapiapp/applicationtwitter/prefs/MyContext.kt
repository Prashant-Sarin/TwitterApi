package com.sample.twitterapiapp.applicationtwitter.prefs

import android.content.Context
import android.content.SharedPreferences
import com.sample.twitterapiapp.applicationtwitter.AppConstants

/**
 * Returns Shared prefernce instance
 * */
val Context.preferences: SharedPreferences
    get() {
        return getSharedPreferences(AppConstants.PREFERENCE_NAME, Context.MODE_PRIVATE)
    }