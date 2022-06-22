package com.academiapp.content.audits

import android.view.LayoutInflater
import android.view.View
import com.academiapp.base.ActivityViewBinding
import com.academiapp.content.audits.adapter.AuditsAdapter
import com.academiapp.databinding.ActivityAuditsBinding
import com.academiapp.models.Audits
import org.koin.android.ext.android.inject

class AuditsActivity : ActivityViewBinding<ActivityAuditsBinding,AuditsVM>() {

    val adapter : AuditsAdapter by inject()
    private var auditList : ArrayList<Audits> = arrayListOf()
    override fun inflateLayout(layoutInflater: LayoutInflater) = ActivityAuditsBinding.inflate(layoutInflater)

    override fun viewListeners() {
        auditList = intent.extras?.getSerializable("auditList") as ArrayList<Audits> ?: arrayListOf()


        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
            rvAudits.adapter = adapter
            adapter.list = auditList

            txtInfo.visibility = if(auditList.isEmpty()) View.VISIBLE else View.GONE
        }

    }

    override fun viewModelListeners() {
    }

}