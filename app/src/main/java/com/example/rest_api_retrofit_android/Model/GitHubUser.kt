package com.example.rest_api_retrofit_android.Model

import android.util.Log
import com.google.gson.annotations.SerializedName

class GitHubUser(
    @SerializedName("id") //indica que el elemento id tiene que ser serializado a json. Tine que tener el mismo nombre que la respuesta de la API
    val id: Long,

    @SerializedName("avatar_url")
    val avatar: String,

    @SerializedName("name")
    val userName: String,

    @SerializedName("email")
    val userEmail: String,

    @SerializedName("followers")
    val followers: String,

    @SerializedName("following")
    val following: String,

    @SerializedName("login")
    val login: String
) {

    val getId: Long
        get() = id

    val displayAvatar: String
        get() = avatar

    val displayUserName: String
        get() = userName

    val displayUserEmail: String
        get() = userEmail

    val displayFollowers: String
        get() = followers

    val displayFollowing: String
        get() = following

    val displayLogin: String
        get() = login

}