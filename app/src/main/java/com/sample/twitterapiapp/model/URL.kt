package com.sample.twitterapiapp.model

import com.google.gson.annotations.SerializedName

data class URL(
    @SerializedName("url") var url: String? = null,
    @SerializedName("expanded_url") var expandedURL: String? = null,
    @SerializedName("display_url") var displayURL: String? = null,
    @SerializedName("indices") var indices: List<Long>? = null
)