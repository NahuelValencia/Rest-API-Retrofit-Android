package com.example.rest_api_retrofit_android.Model

import com.google.gson.annotations.SerializedName

class StackoverflowBadges(
    @SerializedName("bronze")
    private val bronze_badge: Int,

    @SerializedName("silver")
    private val silver_badge: Int,

    @SerializedName("gold")
    private val gold_badge: Int
) {
    val bronze: Int
        get() = bronze_badge

    val silver: Int
        get() = silver_badge

    val gold: Int
        get() = gold_badge
}