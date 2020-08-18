package com.sample.twitterapiapp.ui.feed

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.twitterapiapp.applicationtwitter.prefs.oauth_secret
import com.sample.twitterapiapp.applicationtwitter.prefs.oauth_token
import com.sample.twitterapiapp.applicationtwitter.prefs.preferences
import com.sample.twitterapiapp.listeners.RestCallback
import com.sample.twitterapiapp.model.Feed
import com.sample.twitterapiapp.repository.TwitterRepository

class FeedViewModel : ViewModel() {

    private val TAG = FeedViewModel::class.java.simpleName

    var feedList = ArrayList<Feed>()
    var feedApiSuccess = MutableLiveData<Boolean>()

    /**
     * This function fetched feeds from twitter and updates feedApiSuccess(MutableLiveData) based on success/failure
     */
    fun getFeeds(context: Context) {
        Log.d(TAG, "oauth-token = ${context.preferences.oauth_token}")
        Log.d(TAG, "oauth-secret = ${context.preferences.oauth_secret}")

        val callback = object : RestCallback<ArrayList<Feed>> {
            override fun onSuccess(t: ArrayList<Feed>?) {
                Log.d(TAG, "getFeeds success value = $t")
                Log.d(TAG, "getFeeds success feeds size = ${t?.size}")
                t?.let {
                    feedList = it
                }
                feedApiSuccess.postValue(true)
            }

            override fun onFailure(error: String) {
                Log.e(TAG, "getFeeds failed")
                feedApiSuccess.postValue(false)
            }
        }
        TwitterRepository().getFeedsFromApi(callback)

    }
}