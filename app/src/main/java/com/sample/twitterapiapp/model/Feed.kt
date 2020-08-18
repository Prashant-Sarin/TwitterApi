package com.sample.twitterapiapp.model

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("id") var id: Double? = null,
    @SerializedName("id_str") var idStr: String? = null,
    @SerializedName("text") var text: String? = null,
    @SerializedName("truncated") var truncated: Boolean? = null,
    @SerializedName("entities") var entities: FeedEntities? = null,
    @SerializedName("source") var source: String? = null,
    @SerializedName("in_reply_to_status_id") var inReplyToStatusID: Any? = null,
    @SerializedName("in_reply_to_status_id_str") var inReplyToStatusIDStr: Any? = null,
    @SerializedName("in_reply_to_user_id") var inReplyToUserID: Any? = null,
    @SerializedName("in_reply_to_user_id_str") var inReplyToUserIDStr: Any? = null,
    @SerializedName("in_reply_to_screen_name") var inReplyToScreenName: Any? = null,
    @SerializedName("user") var user: User? = null,
    @SerializedName("geo") var geo: Any? = null,
    @SerializedName("coordinates") var coordinates: Any? = null,
    @SerializedName("place") var place: Any? = null,
    @SerializedName("contributors") var contributors: Any? = null,
    @SerializedName("is_quote_status") var isQuoteStatus: Boolean? = null,
    @SerializedName("retweet_count") var retweetCount: Long? = null,
    @SerializedName("favorite_count") var favoriteCount: Long? = null,
    @SerializedName("favorited") var favorited: Boolean? = null,
    @SerializedName("retweeted") var retweeted: Boolean? = null,
    @SerializedName("possibly_sensitive") var possiblySensitive: Boolean? = null,
    @SerializedName("possibly_sensitive_appealable") var possiblySensitiveAppealable: Boolean? = null,
    @SerializedName("lang") var lang: String? = null
)