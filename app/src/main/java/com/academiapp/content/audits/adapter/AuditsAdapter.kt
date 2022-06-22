package com.academiapp.content.audits.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.academiapp.base.BaseAdapter
import com.academiapp.databinding.AuditItemBinding
import com.academiapp.models.Audits

class AuditsAdapter : BaseAdapter<Audits, AuditItemBinding>() {

    override fun bindingInflater(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        attachParent: Boolean
    ) = AuditItemBinding.inflate(layoutInflater,parent,attachParent)

    @SuppressLint("SetTextI18n")
    override fun onItemViewReady(binding: AuditItemBinding, item: Audits, position: Int) {
            binding.apply {

                txtArea.text = item.pqrsAreas?.description
                txtDate.text = item.createdAt
                txtFrom.text = item.userRegister?.person?.firstName + " " + item.userRegister?.person?.lastName
                txtObservation.text = item.observation
                txtPqrsStatus.text = item.pqrsStatus?.description
                txtPqrsType.text = item.pqrsTypes?.description

                btnMore.setOnClickListener {
                    if(btnMore.text == "Ver mas"){
                        llMore.visibility = View.VISIBLE
                        btnMore.text = "Ver menos"
                    }else{
                        llMore.visibility = View.GONE
                        btnMore.text = "Ver mas"
                    }

                }
            }
    }

}