package com.academiapp.content.create_pqrs

import android.view.LayoutInflater
import com.academiapp.base.ActivityViewBinding
import com.academiapp.content.create_pqrs.anonymous.CreatePQRSAnonymousActivity
import com.academiapp.content.create_pqrs.no_anonymous.CreatePQRSNoAnonymousActivity
import com.academiapp.databinding.ActivityCreatePqrsactivityBinding
import com.academiapp.utils.openActivity

class CreatePQRSActivity : ActivityViewBinding<ActivityCreatePqrsactivityBinding, CreatePQRSVM>() {

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityCreatePqrsactivityBinding.inflate(layoutInflater)

    override fun viewListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }

            cvPQRSAnonym.setOnClickListener {
                openActivity(CreatePQRSAnonymousActivity::class.java)
            }

            cvPQRSnoAnonym.setOnClickListener {
                openActivity(CreatePQRSNoAnonymousActivity::class.java)
            }
        }
    }

    override fun viewModelListeners() {

    }

}