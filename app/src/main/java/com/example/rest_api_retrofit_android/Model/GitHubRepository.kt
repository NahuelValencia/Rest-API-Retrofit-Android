package com.example.rest_api_retrofit_android.Model

import com.google.gson.annotations.SerializedName

class GitHubRepository(
    @SerializedName("id") //indica que el elemento id tiene que ser serializado a json. Tine que tener el mismo nombre que la respuesta de la API
    private val id: Long,

    @SerializedName("name")
    private val repoName: String,

    @SerializedName("private")
    private val private: Boolean,

    @SerializedName("html_url")
    private val url: String,

    @SerializedName("description")
    private val description: String?,

    @SerializedName("language")
    private val language: String,

    @SerializedName("created_at")
    private val created_at: String,

    @SerializedName("updated_at")
    private val updated_at: String
) {

    val getId: Long
        get() = id

    val repositoryName: String
        get() = repoName

    val isPublic: Boolean
        get() = !private

    val repositoryUrl: String
        get() = url

    val repoDescription: String
        get() {
            description?.let {
                return it
            } ?: return "There is no description"
        }

    val languageProgramming: String
        get() = language

    val creation: String
        get() = created_at

    val modified: String
        get() = updated_at
}