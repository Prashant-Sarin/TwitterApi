package com.sample.twitterapiapp.model

import com.google.gson.annotations.SerializedName

data class UserEntities (
    @SerializedName("url") var url: Description? = null,
    @SerializedName("description") var description: Description? = null
)