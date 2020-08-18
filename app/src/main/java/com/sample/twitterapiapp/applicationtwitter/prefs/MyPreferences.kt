package com.sample.twitterapiapp.applicationtwitter.prefs

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.twitterapiapp.model.Feed


// Preference keys
const val PREF_KEY_OAUTH_TOKEN = "oauth_token"
const val PREF_KEY_OAUTH_SECRET = "oauth_token_secret"
const val PREF_KEY_IS_LOGGED_IN = "isLoggedIn"
const val PREF_KEY_USERNAME = "username"
const val PREF_KEY_FEEDS = "feeds"

var SharedPreferences.oauth_token: String?
    get() {
        return this.getString(PREF_KEY_OAUTH_TOKEN, "")
    }
    set(newValue) {
        this.edit().putString(PREF_KEY_OAUTH_TOKEN, newValue).apply()
    }

var SharedPreferences.oauth_secret: String?
    get() {
        return this.getString(PREF_KEY_OAUTH_SECRET, "")
    }
    set(newValue) {
        this.edit().putString(PREF_KEY_OAUTH_SECRET, newValue).apply()
    }

var SharedPreferences.isLoggedIn: Boolean
    get() {
        return this.getBoolean(PREF_KEY_IS_LOGGED_IN, false)
    }
    set(newValue) {
        this.edit().putBoolean(PREF_KEY_IS_LOGGED_IN, newValue).apply()
    }

var SharedPreferences.username: String?
    get() {
        return this.getString(PREF_KEY_USERNAME, "")
    }
    set(newValue) {
        this.edit().putString(PREF_KEY_USERNAME, newValue).apply()
    }

var SharedPreferences.feeds: ArrayList<Feed>
    get() {
        val feedString = this.getString(PREF_KEY_FEEDS, null)
        val type = object : TypeToken<java.util.ArrayList<Feed>>() {}.type
        return if (feedString != null) {
            Gson().fromJson<java.util.ArrayList<Feed>>(feedString, type)
        } else {
            java.util.ArrayList<Feed>()
        }
    }
    set(value) {
        this.edit().putString(PREF_KEY_FEEDS, Gson().toJson(value)).apply()
    }