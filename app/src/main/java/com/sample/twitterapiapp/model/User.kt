package com.sample.twitterapiapp.model

import android.net.Uri
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (
    @SerializedName("id") var id: Long? = null,
    @SerializedName("id_str") var idStr: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("screen_name") var screenName: String? = null,
    @SerializedName("location") var location: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("entities") var entities: UserEntities? = null,
    @SerializedName("protected") var protected: Boolean? = null,
    @SerializedName("followers_count") var followersCount: Long? = null,
    @SerializedName("friends_count") var friendsCount: Long? = null,
    @SerializedName("listed_count") var listedCount: Long? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("favourites_count") var favouritesCount: Long? = null,
    @SerializedName("utc_offset") var utcOffset: Any? = null,
    @SerializedName("time_zone") var timeZone: Any? = null,
    @SerializedName("geo_enabled") var geoEnabled: Boolean? = null,
    @SerializedName("verified") var verified: Boolean? = null,
    @SerializedName("statuses_count") var statusesCount: Long? = null,
    @SerializedName("lang") var lang: Any? = null,
    @SerializedName("contributors_enabled") var contributorsEnabled: Boolean? = null,
    @SerializedName("is_translator") var isTranslator: Boolean? = null,
    @SerializedName("is_translation_enabled") var isTranslationEnabled: Boolean? = null,
    @SerializedName("profile_background_color") var profileBackgroundColor: String? = null,
    @SerializedName("profile_background_image_url") var profileBackgroundImageURL: String? = null,
    @SerializedName("profile_background_image_url_https") var profileBackgroundImageURLHTTPS: String? = null,
    @SerializedName("profile_background_tile") var profileBackgroundTile: Boolean? = null,
    @SerializedName("profile_image_url") var profileImageURL: String? = null,
    @SerializedName("profile_image_url_https") var profileImageURLHTTPS: String? = null,
    @SerializedName("profile_banner_url") var profileBannerURL: String? = null,
    @SerializedName("profile_link_color") var profileLinkColor: String? = null,
    @SerializedName("profile_sidebar_border_color") var profileSidebarBorderColor: String? = null,
    @SerializedName("profile_sidebar_fill_color") var profileSidebarFillColor: String? = null,
    @SerializedName("profile_text_color") var profileTextColor: String? = null,
    @SerializedName("profile_use_background_image") var profileUseBackgroundImage: Boolean? = null,
    @SerializedName("has_extended_profile") var hasExtendedProfile: Boolean? = null,
    @SerializedName("default_profile") var defaultProfile: Boolean? = null,
    @SerializedName("default_profile_image") var defaultProfileImage: Boolean? = null,
    @SerializedName("following") var following: Boolean? = null,
    @SerializedName("follow_request_sent") var followRequestSent: Boolean? = null,
    @SerializedName("notifications") var notifications: Boolean? = null,
    @SerializedName("translator_type") var translatorType: String? = null
): Serializable {

    fun getImageURI(): Uri {
        return Uri.parse(this.profileImageURL)
    }
}