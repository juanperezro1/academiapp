package com.academiapp.content.my_remits

import android.view.LayoutInflater
import com.academiapp.base.ActivityViewBinding
import com.academiapp.content.my_remits.details.PQRSDetailActivity
import com.academiapp.content.pqrs.adapter.PQRSAdapter
import com.academiapp.content.pqrs.edit.EditPQRSActivity
import com.academiapp.databinding.ActivityMyRemitsBinding
import com.academiapp.models.Remisions
import com.academiapp.utils.openActivity
import com.academiapp.widgets.RemitDialog
import org.koin.android.ext.android.inject

class MyRemitsActivity : ActivityViewBinding<ActivityMyRemitsBinding,MyRemitsVM>() {

    val adapter : PQRSAdapter by inject()
    private var remissionList : ArrayList<Remisions> = arrayListOf()

    override fun inflateLayout(layoutInflater: LayoutInflater) = ActivityMyRemitsBinding.inflate(layoutInflater)

    override fun viewListeners() {
        remissionList = intent.extras?.getSerializable("remissionList") as ArrayList<Remisions>

        binding.rvPqrs.adapter = adapter
       // adapter.list = arrayListOf("","","")

        adapter.onItemClickListener = {
            openActivity(PQRSDetailActivity::class.java)
        }

        adapter.onEditClickList = {
            openActivity(EditPQRSActivity::class.java)
        }

        adapter.onRemitClickListener = {
            RemitDialog(this,it).show()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun viewModelListeners() {
    }

}