package com.academiapp.content.degree_work

//import com.academiapp.content.degree_work.adapter.DegreeWorkAdapter

import android.view.LayoutInflater
import com.academiapp.activities.AddNewActivity
import com.academiapp.activities.MainActivity
import com.academiapp.base.ActivityViewBinding
import com.academiapp.content.degree_work.adapter.DegreeWorkAdapter
import com.academiapp.databinding.ActivityDegreeWorkBinding
import com.academiapp.utils.openActivity
import org.koin.android.ext.android.inject


class DegreeWorkActivity : ActivityViewBinding<ActivityDegreeWorkBinding, DegreeWorkVM>() {


    val adapter : DegreeWorkAdapter by inject()
//lateinit var adapter : WorkDegreeAdapter

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityDegreeWorkBinding.inflate(layoutInflater)

    override fun viewListeners() {
//        initializeRcv()
        binding.apply {
            rvDegreeWork.adapter = adapter
            adapter.list = arrayListOf("","","","")
        }
    }


    override fun viewModelListeners() {
        adapter.onEditClickList = {
            openActivity(MainActivity::class.java, extras = {
                putSerializable("pqrs",it)
            })
        }
        binding.imgAdd.setOnClickListener {
            openActivity(AddNewActivity::class.java)
        }
    }

//    private fun initializeRcv() {
//        binding.apply {
////        rcvAllCcurrenciesList = findViewById<View>(R.id.rcv_currencies_list)
//            rvDegreeWork.setLayoutManager(LinearLayoutManager(this@DegreeWorkActivity))
//            val list: ArrayList<String> = ArrayList()
//            list.add("text")
//            list.add("text")
//            list.add("text")
//            list.add("text")
////            adapter.list = arrayListOf("","","","");
//            adapter = WorkDegreeAdapter(this@DegreeWorkActivity,list)
//            //        currenciesAdapter.setClickListener(this);
//            rvDegreeWork.setAdapter(adapter as RecyclerView.Adapter<*>?)
////            progressDialog.dismiss()
//        }
//    }
}