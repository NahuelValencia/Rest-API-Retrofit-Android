package com.example.rest_api_retrofit_android.Activity

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.rest_api_retrofit_android.Model.GitHubUser
import com.example.rest_api_retrofit_android.R
import com.example.rest_api_retrofit_android.Rest.APIClient
import com.example.rest_api_retrofit_android.Rest.GitHubUserEndPoints
import com.example.rest_api_retrofit_android.Utilitis.ImageDownloader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btnLogin: Button
    lateinit var etUserName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btn_login)
        etUserName = findViewById(R.id.input_usernmae)

        btnLogin.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_login -> loadData(this)
        }
    }

    private fun loadData(loginActivity: LoginActivity) {
        val apiService: GitHubUserEndPoints =
            APIClient.getClient().create(GitHubUserEndPoints::class.java)

        val user = etUserName.text.toString()

        apiService.getUser(user).also {
            Log.i("apiService call", "ApiService: $apiService / Call: $it")
            it.enqueue(object : Callback<GitHubUser> {

                override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {
                    val resp = response.body() ?: return

                    var image: Bitmap? = null
                    val task = ImageDownloader()
                    try {
                        image = task.execute(resp.displayAvatar).get()
                    } catch (e: Exception) {
                        e.stackTrace
                    }

                    UserActivity.screenBuilder(loginActivity, resp, image)
                }

                override fun onFailure(call: Call<GitHubUser>, t: Throwable) {
                    Log.i("GithubCall", "Fail call to github api")
                    Log.i("GithubCall Error", t.message)
                }

            })
        }
    }
}