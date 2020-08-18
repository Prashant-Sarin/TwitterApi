package com.sample.twitterapiapp.ui.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.twitterapiapp.applicationtwitter.AppConstants
import com.sample.twitterapiapp.applicationtwitter.prefs.*
import com.sample.twitterapiapp.ui.login.LoginActivity.Companion.requestToken
import com.sample.twitterapiapp.ui.login.LoginActivity.Companion.twitter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import twitter4j.TwitterException
import twitter4j.TwitterFactory
import twitter4j.User
import twitter4j.conf.Configuration
import twitter4j.conf.ConfigurationBuilder

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.java.simpleName


    var user: User? = null
    var isLoggedIn = MutableLiveData<Boolean>()

    /**
     * Function to login twitter
     */
    fun loginToTwitter(context: Context) {
        // Check if already logged in
        if (!isTwitterLoggedInAlready(context)) {
            CoroutineScope(Dispatchers.IO).launch {
                val builder = ConfigurationBuilder()
                builder.setOAuthConsumerKey(AppConstants.TWITTER_CONSUMER_KEY)
                builder.setOAuthConsumerSecret(AppConstants.TWITTER_CONSUMER_SECRET)
                val configuration: Configuration = builder.build()
                val factory = TwitterFactory(configuration)
                twitter = factory.instance
                try {
                    requestToken = twitter?.getOAuthRequestToken(AppConstants.TWITTER_CALLBACK_URL)
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(requestToken?.authenticationURL)
                        )
                    )
                } catch (e: TwitterException) {
                    e.printStackTrace()
                }
            }
        } else {
            isLoggedIn.postValue(true)
        }
    }


    /** This if conditions is tested once is
     * redirected from twitter page. Parse the uri to get oAuth
     * Verifier
     */
    fun checkForAlreadyLoggedIn(context: Context, intent: Intent) {
        if (!isTwitterLoggedInAlready(context)) {
            val uri = intent.data
            if (uri != null && uri.toString().startsWith(AppConstants.TWITTER_CALLBACK_URL)) {
                CoroutineScope(Dispatchers.IO).launch {
                    // oAuth verifier
                    val verifier = uri
                        .getQueryParameter(AppConstants.URL_TWITTER_OAUTH_VERIFIER)
                    try {
                        // Get the access token
                        val accessToken = twitter?.getOAuthAccessToken(
                            requestToken, verifier
                        )
                        // saving details in preferences
                        context.preferences.oauth_token = accessToken?.token
                        context.preferences.oauth_secret = accessToken?.tokenSecret
                        context.preferences.isLoggedIn = true
                        AppConstants.OAUTH_TOKEN = accessToken?.token ?: ""
                        AppConstants.OAUTH_TOKEN_SECRET = accessToken?.tokenSecret ?: ""
                        Log.e(TAG, "Twitter OAuth Token = ${accessToken?.token}")

                        // Getting user details from twitter
                        val userID = accessToken?.userId ?: 0L
                        val userObj = twitter?.showUser(userID)
                        context.preferences.username = userObj?.name ?: ""
                        if (userObj != null) {
                            user = userObj
                            isLoggedIn.postValue(true)
                        } else {
                            isLoggedIn.postValue(false)
                        }

                    } catch (e: Exception) {
                        // Check log for login errors
                        Log.e(TAG, "Twitter Login Error ${e.message}")
                        isLoggedIn.postValue(false)
                    }
                }
            }
        }
    }

    /**
     * Check if user already logged in your application using twitter Login flag that is stored in
     * Shared Preferences
     */
    fun isTwitterLoggedInAlready(context: Context): Boolean {
        // return twitter login status from Shared Preferences
        return context.preferences.getBoolean(PREF_KEY_IS_LOGGED_IN, false)
    }
}