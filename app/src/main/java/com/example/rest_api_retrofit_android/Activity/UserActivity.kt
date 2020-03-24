package com.example.rest_api_retrofit_android.Activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

        val byteArray = intent.getByteArrayExtra("image")
        val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

        response?.let { render(it, bmp) }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun render(
        user: GitHubUser,
        image: Bitmap?
    ) {

        avatar.setImageBitmap(image)
        avatar.layoutParams.height = 220
        avatar.layoutParams.width = 220

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
