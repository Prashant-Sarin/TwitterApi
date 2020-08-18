package com.sample.twitterapiapp.model

import com.google.gson.annotations.SerializedName

data class FeedEntities (
    @SerializedName("hashtags") var hashtags: List<Hashtag>? = null,
    @SerializedName("symbols") var symbols: List<Any?>? = null,
    @SerializedName("user_mentions") var userMentions: List<Any?>? = null,
    @SerializedName("urls") var urls: List<URL>? = null
)