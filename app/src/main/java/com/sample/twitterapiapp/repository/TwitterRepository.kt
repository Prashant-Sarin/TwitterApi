package com.sample.twitterapiapp.repository

import com.sample.twitterapiapp.applicationtwitter.api.Api
import com.sample.twitterapiapp.applicationtwitter.api.ApiHandler
import com.sample.twitterapiapp.listeners.RestCallback
import com.sample.twitterapiapp.model.Feed

class TwitterRepository : ApiHandler() {

    private val TAG = TwitterRepository::class.java.simpleName

    private val api = Api.create()

    fun getFeedsFromApi(callback: RestCallback<ArrayList<Feed>>) {
        val call = api.getHomeTimeline(200, true)
        makeApiCall(call, callback)
    }

}