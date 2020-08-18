package com.sample.twitterapiapp.model

import com.google.gson.annotations.SerializedName

data class Hashtag (
    @SerializedName("text") var text: String? = null,
    @SerializedName("indices") var indices: List<Long>? = null
)