package com.example.rest_api_retrofit_android.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.rest_api_retrofit_android.Model.GitHubUser
import com.example.rest_api_retrofit_android.R
import com.example.rest_api_retrofit_android.Rest.APIClient
import com.example.rest_api_retrofit_android.Rest.GitHubUserEndPoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var resp: GitHubUser? = null

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

        apiService.getUser(etUserName.text.toString()).also {
            it.enqueue(object : Callback<GitHubUser> {

                override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {
                    resp = response.body()
                    getUser(loginActivity)
                }

                override fun onFailure(call: Call<GitHubUser>, t: Throwable) {

                }

            })
        }
    }

    private fun getUser(loginActivity: LoginActivity) {
        val intent = Intent(loginActivity, UserActivity::class.java)
        startActivity(loginActivity, intent, null)
    }
}