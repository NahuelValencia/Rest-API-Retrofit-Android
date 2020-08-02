package com.example.rest_api_retrofit_android.Model

import com.google.gson.annotations.SerializedName

class StackoverflowUser(
    @SerializedName("user_id")
    private val id: Int,

    @SerializedName("display_name")
    private val userName: String,

    @SerializedName("profile_image")
    private val profileImage: String,

    @SerializedName("reputation")
    private val reputation: Int,

    @SerializedName("badge_counts")
    private val badge: StackoverflowBadges
) {
    val name: String
        get() = userName

    val image: String
        get() = profileImage

    val userReputation: Int
        get() = reputation

    val badges: StackoverflowBadges
        get() = badge
}