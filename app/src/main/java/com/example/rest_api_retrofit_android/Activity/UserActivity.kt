package com.example.rest_api_retrofit_android.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.rest_api_retrofit_android.Model.GitHubUser
import com.example.rest_api_retrofit_android.R

class UserActivity : AppCompatActivity() {

    lateinit var avatar: ImageView
    lateinit var userName: TextView
    lateinit var followers: TextView
    lateinit var following: TextView
    lateinit var email: TextView
    lateinit var ownedRepos: Button

    private val response = resp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val actionBar = supportActionBar ?: return
        actionBar.title = "User Activity"
        actionBar.setDisplayHomeAsUpEnabled(true)

        avatar = findViewById(R.id.user_avatar)
        userName = findViewById(R.id.username)
        followers = findViewById(R.id.followers)
        following = findViewById(R.id.following)
        email = findViewById(R.id.email)
        ownedRepos = findViewById(R.id.btn_owned_repos)

        response?.let { render(it) }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun render(user: GitHubUser) {

        userName.text = "${getString(R.string.user_name)}: ${user.displayUserName}"

        followers.text = "${getString(R.string.followers)}: ${user.displayFollowers}"
        following.text = "${getString(R.string.following)}: ${user.displayFollowing}"

        if (user.displayUserEmail == null) {
            email.text = "${getString(R.string.email)}: ${getString(R.string.no_email_provided)}"
        } else {
            email.text = "${getString(R.string.email)}: ${user.displayUserEmail}"
        }
    }
}
