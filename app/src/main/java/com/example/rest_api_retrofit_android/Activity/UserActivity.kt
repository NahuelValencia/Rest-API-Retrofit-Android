package com.example.rest_api_retrofit_android.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.rest_api_retrofit_android.Model.GitHubUser
import com.example.rest_api_retrofit_android.R
import java.io.ByteArrayOutputStream


class UserActivity : AppCompatActivity() {

    lateinit var avatar: ImageView
    lateinit var userName: TextView
    lateinit var followers: TextView
    lateinit var following: TextView
    lateinit var email: TextView
    lateinit var ownedRepos: Button

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

        user?.let { render(it) }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun render(user: GitHubUser) {

        val userImage = decodeImage(byteArray)

        avatar.setImageBitmap(userImage)
        avatar.layoutParams.height = 220
        avatar.layoutParams.width = 220

        userName.text = "${getString(R.string.user_name)}: ${user.displayUserName}"

        followers.text = "${getString(R.string.followers)}: ${user.displayFollowers}"
        following.text = "${getString(R.string.following)}: ${user.displayFollowing}"

        val userEmail = user.displayUserEmail?.let {
            "${getString(R.string.email)}: $it"
        } ?: "${getString(R.string.email)}: ${getString(R.string.no_email_provided)}"

        email.text = userEmail
    }

    private fun decodeImage(image: ByteArray?): Bitmap? {
        val byteArray = image ?: return null
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    companion object {
        private var user: GitHubUser? = null
        private var userImage: Bitmap? = null
        private var byteArray: ByteArray? = null

        fun screenBuilder(context: Context, user: GitHubUser, image: Bitmap?) {
            this.user = user
            this.userImage = image ?: return

            val intent = Intent(context, UserActivity::class.java)

            val stream = ByteArrayOutputStream()
            userImage!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
            byteArray = stream.toByteArray()

            startActivity(context, intent, null)
        }
    }
}
