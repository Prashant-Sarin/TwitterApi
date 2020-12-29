package com.sample.twitterapiapp.model

import com.google.gson.annotations.SerializedName

data class SearchMetadata(
    @SerializedName("completed_in") var completedIn: Double,
    @SerializedName("max_id") var maxID: Long,
    @SerializedName("max_id_str") var maxIDStr: String,
    @SerializedName("next_results") var nextResults: String,
    @SerializedName("query") var query: String,
    @SerializedName("count") var count: Long,
    @SerializedName("since_id") var sinceID: Long,
    @SerializedName("since_id_str") var sinceIDStr: String
)
