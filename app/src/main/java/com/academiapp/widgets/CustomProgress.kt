package com.academiapp.widgets

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.academiapp.databinding.CustomProgressBinding
import com.academiapp.utils.getActivity

class CustomProgress(private val context: Context) {
    private var viewGroup : ViewGroup? = null
    private lateinit var binding : CustomProgressBinding

    fun show(isShow:Boolean){
        if(isShow) show() else hide()
    }

    private fun show(){
        viewGroup = getActivity(context)?.window?.decorView?.rootView as ViewGroup?
        binding = CustomProgressBinding.inflate(LayoutInflater.from(context))
        viewGroup?.addView(binding.root)
    }

    private fun hide(){
        if(viewGroup != null){
            viewGroup?.removeView(binding.root)
        }
    }
}