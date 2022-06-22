package com.academiapp.content.degree_work.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.academiapp.base.BaseAdapter
import com.academiapp.content.degree_work.DegreeWorkActivity
import com.academiapp.databinding.DegreeWorkItemBinding
import com.academiapp.models.Pqrs
import com.academiapp.utils.openActivity

class DegreeWorkAdapter : BaseAdapter<String, DegreeWorkItemBinding>() {

    var onRemitClickListener : ((Int) -> Unit) ?= null
    var onEditClickList : ((String) -> Unit) ?= null


    override fun bindingInflater(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        attachParent: Boolean
    ) = DegreeWorkItemBinding.inflate(layoutInflater,parent,attachParent)

    override fun onItemViewReady(binding: DegreeWorkItemBinding, item: String, position: Int) {

        binding.apply {

            btnMore.setOnClickListener {
                onEditClickList?.invoke(item)
            }
        }

    }
}