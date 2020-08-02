package com.example.rest_api_retrofit_android.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rest_api_retrofit_android.Adapters.GitHubRepositoryAdapter
import com.example.rest_api_retrofit_android.Model.GitHubRepository
import com.example.rest_api_retrofit_android.Model.GitHubUser
import com.example.rest_api_retrofit_android.R
import com.example.rest_api_retrofit_android.Rest.APIClient
import com.example.rest_api_retrofit_android.Rest.GitHubUserEndPoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream


class UserActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var avatar: ImageView
    lateinit var userName: TextView
    lateinit var followers: TextView
    lateinit var following: TextView
    lateinit var email: TextView
    lateinit var ownedRepos: Button

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

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
        recyclerView = findViewById(R.id.recyclerview)
        progressBar = findViewById(R.id.progressBar)

        ownedRepos.setOnClickListener(this)

        user?.let { render(it) }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_owned_repos -> user?.let {
                progressBar.visibility = View.VISIBLE
                loadRepos(this, it)
            }
        }
    }

    private fun loadRepos(context: Context, user: GitHubUser) {
        val apiService: GitHubUserEndPoints =
            APIClient.getClient().create(GitHubUserEndPoints::class.java)

        val userName = user.loginName

        apiService.getRepositories(userName).also {
            Log.i("apiService call", "ApiService: $apiService / Call: $it")
            it.enqueue(object : Callback<List<GitHubRepository>> {

                override fun onResponse(
                    call: Call<List<GitHubRepository>>,
                    response: Response<List<GitHubRepository>>
                ) {
                    val repositories = response.body() ?: return
                    renderRepos(repositories, context)
                }

                override fun onFailure(call: Call<List<GitHubRepository>>, t: Throwable) {
                    Log.e("GithubCall", "Fail call to github api")
                    Log.e("GithubCall Error", t.message.toString())
                }
            })
        }
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

    private fun renderRepos(response: List<GitHubRepository>, context: Context) {
        val adapter = GitHubRepositoryAdapter(response)

        ownedRepos.visibility = View.GONE
        progressBar.visibility = View.GONE

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
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
