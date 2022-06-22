package com.academiapp.content.login

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import com.academiapp.R
import com.academiapp.base.ActivityViewBinding
import com.academiapp.content.create_pqrs.CreatePQRSActivity
import com.academiapp.content.home.HomeActivity
import com.academiapp.databinding.ActivityLoginBinding
import com.academiapp.services.SharedPreferencesManager
import com.academiapp.utils.openActivity
import com.academiapp.utils.showMessage
import com.bumptech.glide.Glide
import java.io.InputStream
import java.net.URL


class LoginActivity : ActivityViewBinding<ActivityLoginBinding, LoginVM>() {
    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun viewListeners() {
        binding.apply {
            btnPQRS.setOnClickListener {
                openActivity(CreatePQRSActivity::class.java)
            }

            btnLogin.setOnClickListener {
                viewModel.login(etUsername.text.toString(),etPassword.text.toString())
            }
        }

    }

    override fun viewModelListeners() {
        viewModel.apply {
            loginSuccess.observe(this@LoginActivity){
                openActivity(HomeActivity::class.java)
                finish()
            }

            loginError.observe(this@LoginActivity){
                if(it.equals("Credenciales invalidas o usuario inactivo"))
                    showMessage(it)
                else
                    showMessage("No Internet Connection")
            }

            progressStatus.observe(this@LoginActivity){
                progressDialog.show(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferencesManager = SharedPreferencesManager(this@LoginActivity)

        if(sharedPreferencesManager.getmainurlimg()!=""){

            val imageview :ImageView=findViewById(R.id.imageView1)


            val media = sharedPreferencesManager.getmainurlimg()
            if (media !== null) {
                Glide.with(this)
                    .load(media)
                    .into(imageview)
            } else {
                imageview.setImageResource(R.drawable.logo)
            }

        }
            if (sharedPreferencesManager.getUserName()!="") {
                viewModel.login(sharedPreferencesManager.getUserName(),sharedPreferencesManager.getPassword())
            }

    }



}