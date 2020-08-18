package com.sample.twitterapiapp.model

import com.google.gson.annotations.SerializedName


data class Description(
    @SerializedName("urls") var urls: List<URL>? = null
)