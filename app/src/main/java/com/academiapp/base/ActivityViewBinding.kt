package com.academiapp.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.academiapp.widgets.CustomProgress
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class ActivityViewBinding<VB: ViewBinding, VM: ViewModel> : AppCompatActivity()  {

    protected lateinit var binding : VB
    val viewModel: VM by lazy {getViewModel(clazz = viewModelClass())}

    lateinit var progressDialog : CustomProgress


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
        progressDialog = CustomProgress(this)
        viewListeners()
        viewModelListeners()
    }

    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): KClass<VM> {
        return ((javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VM>).kotlin
    }

    abstract fun inflateLayout(layoutInflater: LayoutInflater) : VB

    abstract fun viewListeners()

    abstract fun viewModelListeners()
}