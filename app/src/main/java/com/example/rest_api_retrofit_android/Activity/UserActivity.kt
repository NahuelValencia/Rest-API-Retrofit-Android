package com.example.rest_api_retrofit_android.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.rest_api_retrofit_android.Model.GitHubUser
import com.example.rest_api_retrofit_android.R
import com.example.rest_api_retrofit_android.Rest.APIClient
import com.example.rest_api_retrofit_android.Rest.GitHubUserEndPoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity() {

    lateinit var avatar: ImageView
    lateinit var userName: TextView
    lateinit var followers: TextView
    lateinit var following: TextView
    lateinit var logIn: TextView
    lateinit var email: TextView
    lateinit var ownedRepos: Button

    lateinit var extras: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val actionBar = supportActionBar
        actionBar!!.title = "User Activity"
        actionBar.setDisplayHomeAsUpEnabled(true)

        extras = intent.extras!!

        avatar = findViewById(R.id.user_avatar)
        userName = findViewById(R.id.username)
        followers = findViewById(R.id.followers)
        following = findViewById(R.id.following)
        email = findViewById(R.id.email)
        ownedRepos = findViewById(R.id.btn_owned_repos)

        userName.text = extras.getString("user_name")

        loadData()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadData() {
        val apiService: GitHubUserEndPoints =
            APIClient.getClient().create(GitHubUserEndPoints::class.java)

        val call: Call<GitHubUser> = apiService.getUser(userName.toString()).also {
            it.enqueue(object : Callback<GitHubUser> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {

                    if (response.body()?.displayUserName == null) {
                        userName.text = "No name provided"
                    } else {
                        userName.text = "User name: ${response.body()?.displayUserName}"
                    }
                    followers.text = "Followers: ${response.body()?.displayFollowers}"
                    following.text = "Following: ${response.body()?.displayFollowing}"
                    logIn.text = "LogIn: ${response.body()?.displayLogin}"

                    if (response.body()?.displayUserName == null) {
                        email.text = "No E-mal provided"
                    } else {
                        email.text = "E-mail: ${response.body()?.displayUserEmail}"
                    }
                }

                override fun onFailure(call: Call<GitHubUser>, t: Throwable) {

                }

            })
        }

    }
}
