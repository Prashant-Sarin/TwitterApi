package com.sample.twitterapiapp.model

import com.google.gson.annotations.SerializedName

data class Statuses(
    @SerializedName("statuses") var statuses: List<Feed>,
    @SerializedName("search_metadata") var searchMetadata: SearchMetadata
)
