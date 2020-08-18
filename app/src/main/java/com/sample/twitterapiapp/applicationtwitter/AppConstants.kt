package com.sample.twitterapiapp.applicationtwitter

object AppConstants {
    const val TWITTER_CONSUMER_KEY = "O2bq5zXR4AsB1FuMOxCab0ERw"
    const val TWITTER_CONSUMER_SECRET = "Keda1bVHLFrmCKRCHndsCPhBCBLA1MuIaBgfaU8vAVmZuIdIjG"
    const val TWITTER_BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAE1IGwEAAAAA%2BBeNLlgMPte9OmI40prLh8e7v%2Fk%3DYxRTnmaV344MMuguyGHXU2GneU8kerpYtMDJ75MG18KKUxwzGt"

    var OAUTH_TOKEN = ""
    var OAUTH_TOKEN_SECRET = ""

    // Preference Constants
    const val PREFERENCE_NAME = "twitter_oauth"

    const val TWITTER_CALLBACK_URL = "twittersdk://"

    // Twitter oauth urls
    const val URL_TWITTER_AUTH = "auth_url"
    const val URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier"
    const val URL_TWITTER_OAUTH_TOKEN = "oauth_token"
}