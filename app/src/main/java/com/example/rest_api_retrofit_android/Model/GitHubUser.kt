package com.example.rest_api_retrofit_android.Model

import com.google.gson.annotations.SerializedName

class GitHubUser(
    @SerializedName("id") //indica que el elemento id tiene que ser serializado a json. Tine que tener el mismo nombre que la respuesta de la API
    private val id: Long,

    @SerializedName("avatar_url")
    private val avatar: String,

    @SerializedName("name")
    private val userName: String,

    @SerializedName("email")
    private val userEmail: String?,

    @SerializedName("followers")
    private val followers: Int,

    @SerializedName("following")
    private val following: Int,

    @SerializedName("login")
    private val login: String
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

    val loginName: String
        get() = login

}