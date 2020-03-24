package com.example.rest_api_retrofit_android.Model

import com.google.gson.annotations.SerializedName

class GitHubUser(
    @SerializedName("id") //indica que el elemento id tiene que ser serializado a json. Tine que tener el mismo nombre que la respuesta de la API
    val id: Long,

    @SerializedName("avatar_url")
    val avatar: String,

    @SerializedName("name")
    val userName: String,

    @SerializedName("email")
    val userEmail: String?,

    @SerializedName("followers")
    val followers: Int,

    @SerializedName("following")
    val following: Int,

    @SerializedName("login")
    val login: String
) {

    val getId: Long
        get() = id

    val displayAvatar: String
        get() = avatar

    val displayUserName: String
        get() = userName

    val displayUserEmail: String?
        get() = userEmail

    val displayFollowers: Int
        get() = followers

    val displayFollowing: Int
        get() = following

    val displayLogin: String
        get() = login

}