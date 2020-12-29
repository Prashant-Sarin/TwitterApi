package com.sample.twitterapiapp.ui.search

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.twitterapiapp.applicationtwitter.prefs.oauth_secret
import com.sample.twitterapiapp.applicationtwitter.prefs.oauth_token
import com.sample.twitterapiapp.applicationtwitter.prefs.preferences
import com.sample.twitterapiapp.listeners.RestCallback
import com.sample.twitterapiapp.model.Feed
import com.sample.twitterapiapp.model.Statuses
import com.sample.twitterapiapp.repository.TwitterRepository

class SearchViewModel : ViewModel() {

    private val TAG = SearchViewModel::class.java.simpleName

    var queryText = MutableLiveData<String>()
    var feedList = ArrayList<Feed>()
    var feedApiSuccess = MutableLiveData<Boolean>()
    var checked = MutableLiveData<Boolean>()


    /**
     * This function searches tweets based on query
     */
    fun getTweets(query: String, context: Context) {
        Log.d(TAG, "oauth-token = ${context.preferences.oauth_token}")
        Log.d(TAG, "oauth-secret = ${context.preferences.oauth_secret}")

        val callback = object : RestCallback<Statuses> {
            override fun onSuccess(t: Statuses?) {
                Log.d(TAG, "getTweets success value = ${t?.statuses?.size}")
                t?.statuses?.let {
                    feedList = it as ArrayList<Feed>
                }
                feedApiSuccess.postValue(true)
            }

            override fun onFailure(error: String) {
                Log.e(TAG, "getTweets failed")
                feedApiSuccess.postValue(false)
            }
        }
        TwitterRepository().getTweetsFromApi(query, callback)

    }


    /**
     * This method updates query text based on input field changes.
     */
    fun onUpdateText(queryStr: CharSequence) {
        Log.d(TAG, "query text changed to = $queryStr")
        queryText.postValue(queryStr.toString())
    }


    /**
     * This method gets the switch state change.
     */
    fun onChecked(checkedState: Boolean) {
        checked.postValue(checkedState)
    }


    /**
     * This function returns the sorted list based on popularity by considering followersCount and then taking retweetCount into account.
     */
    fun getSortedList(): ArrayList<Feed> {
        val list =
            feedList.sortedWith(compareByDescending<Feed> { it.user?.followersCount }.thenByDescending { it.retweetCount })
        val sortedList = ArrayList<Feed>()
        sortedList.addAll(list)
        return sortedList
    }

}