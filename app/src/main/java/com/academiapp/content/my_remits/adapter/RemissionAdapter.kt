package com.academiapp.content.my_remits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.academiapp.base.BaseAdapter
import com.academiapp.databinding.PqrsTypeItemBinding
import com.academiapp.models.Pqrs
import com.academiapp.models.Remisions

class RemissionAdapter : BaseAdapter<Remisions, PqrsTypeItemBinding>() {

    var onRemitClickListener : ((Int) -> Unit) ?= null
    var onEditClickList : ((Pqrs) -> Unit) ?= null

    override fun bindingInflater(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        attachParent: Boolean
    ) =  PqrsTypeItemBinding.inflate(layoutInflater,parent,attachParent)

    override fun onItemViewReady(binding: PqrsTypeItemBinding, item: Remisions, position: Int) {
        binding.apply {
            btnRefer.setOnClickListener {
                onRemitClickListener?.invoke(item.idPqrs ?: -1)
            }

            btnEdit.setOnClickListener {
               // onEditClickList?.invoke(item)
            }

           // txtDescription.text = item.
           // txtTitle.text = item.pqrsType?.description

        }
    }

}