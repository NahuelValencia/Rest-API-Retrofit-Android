package com.example.rest_api_retrofit_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.databinding.BindingBuildInfo
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_login.*

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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_login -> getUser()
        }
    }

    private fun getUser() {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("user_name", input_usernmae.text.toString())
        startActivity(intent)
    }


}
