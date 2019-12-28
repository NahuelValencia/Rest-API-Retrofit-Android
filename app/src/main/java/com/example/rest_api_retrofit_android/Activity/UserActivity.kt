package com.example.rest_api_retrofit_android.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.rest_api_retrofit_android.R

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

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
