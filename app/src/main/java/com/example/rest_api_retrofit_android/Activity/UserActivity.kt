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

        val actionBar = supportActionBar ?: return
        actionBar.title = "User Activity"
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

        apiService.getUser(userName.text.toString()).also {
            it.enqueue(object : Callback<GitHubUser> {

                override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {

                    if (response.body()?.displayUserName == null) {
                        userName.text = getString(R.string.no_name_provided)
                    } else {
                        userName.text = "${getString(R.string.user_name)}: ${response.body()?.displayUserName}"
                    }

                    followers.text = "${getString(R.string.followers)}: ${response.body()?.displayFollowers}"
                    following.text = "${getString(R.string.following)}: ${response.body()?.displayFollowing}"
                    logIn.text = "${getString(R.string.log_in)}: ${response.body()?.displayLogin}"

                    if (response.body()?.displayUserName == null) {
                        email.text = getString(R.string.no_email_provided)
                    } else {
                        email.text = "${getString(R.string.email)}: ${response.body()?.displayUserEmail}"
                    }
                }

                override fun onFailure(call: Call<GitHubUser>, t: Throwable) {

                }

            })
        }

    }
}
