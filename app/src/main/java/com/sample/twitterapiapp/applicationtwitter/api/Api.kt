package com.sample.twitterapiapp.applicationtwitter.api

import com.google.gson.GsonBuilder
import com.sample.twitterapiapp.applicationtwitter.AppConstants
import com.sample.twitterapiapp.model.Feed
import com.sample.twitterapiapp.model.Statuses
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor
import java.util.concurrent.TimeUnit

interface Api {

    companion object {
        fun create(): Api {

            val gson = GsonBuilder()
                .create()

            val consumer = OkHttpOAuthConsumer(
                AppConstants.TWITTER_CONSUMER_KEY,
                AppConstants.TWITTER_CONSUMER_SECRET
            )
            consumer.setTokenWithSecret(AppConstants.OAUTH_TOKEN, AppConstants.OAUTH_TOKEN_SECRET)

            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(60000, TimeUnit.SECONDS)
            httpClient.writeTimeout(120000, TimeUnit.SECONDS)
            httpClient.readTimeout(120000, TimeUnit.SECONDS)
            httpClient.retryOnConnectionFailure(true)
            httpClient.addInterceptor(SigningInterceptor(consumer))

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.twitter.com/1.1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

            return retrofit.create(Api::class.java)
        }

    }


    @GET("statuses/home_timeline.json")
    fun getHomeTimeline(@Query("count") count: Int, @Query("include_entities") include_entities: Boolean): Call<ArrayList<Feed>>

    @GET("search/tweets.json")
    fun getTweets(@Query("q") q: String, @Query("count") count: Int, @Query("include_entities") include_entities: Boolean): Call<Statuses>

}
