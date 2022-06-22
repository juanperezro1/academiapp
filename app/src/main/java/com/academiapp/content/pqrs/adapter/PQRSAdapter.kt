package com.academiapp.content.pqrs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.academiapp.base.BaseAdapter
import com.academiapp.databinding.PqrsTypeItemBinding
import com.academiapp.models.Pqrs

class PQRSAdapter :  BaseAdapter<Pqrs,PqrsTypeItemBinding>(){

    var onRemitClickListener : ((Int) -> Unit) ?= null
    var onEditClickList : ((Pqrs) -> Unit) ?= null

    override fun bindingInflater(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        attachParent: Boolean
    ) =  PqrsTypeItemBinding.inflate(layoutInflater,parent,attachParent)


    override fun onItemViewReady(binding: PqrsTypeItemBinding, item: Pqrs, position: Int) {
        binding.apply {
            btnRefer.setOnClickListener {
                onRemitClickListener?.invoke(item.idPqrs ?: -1)
            }

            btnEdit.setOnClickListener {
                onEditClickList?.invoke(item)
            }

            txtDescription.text = item.description
            txtTitle.text = item.pqrsType?.description

        }
    }
}