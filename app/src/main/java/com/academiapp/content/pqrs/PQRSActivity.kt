package com.academiapp.content.pqrs

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.academiapp.base.ActivityViewBinding
import com.academiapp.content.my_remits.MyRemitsActivity
import com.academiapp.content.my_remits.details.PQRSDetailActivity
import com.academiapp.content.pqrs.adapter.PQRSAdapter
import com.academiapp.content.pqrs.edit.EditPQRSActivity
import com.academiapp.databinding.ActivityPqrsactivityBinding
import com.academiapp.models.PqrsTypes
import com.academiapp.utils.openActivity
import com.academiapp.widgets.RemitDialog
import org.koin.android.ext.android.inject

class PQRSActivity : ActivityViewBinding<ActivityPqrsactivityBinding, PqrsVM>(),
    AdapterView.OnItemSelectedListener {
    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityPqrsactivityBinding.inflate(layoutInflater)

    val adapter: PQRSAdapter by inject()
    private var pqrsTypeList = ArrayList<PqrsTypes>()
    private var idPqrsType: Int = -1

    override fun viewListeners() {
        binding.rvPqrs.adapter = adapter

        adapter.onItemClickListener = {
            openActivity(PQRSDetailActivity::class.java, extras = {
                putSerializable("pqrs",it)
            })
        }

        adapter.onEditClickList = {
            openActivity(EditPQRSActivity::class.java, extras = {
                putSerializable("pqrs",it)
            })
        }

        adapter.onRemitClickListener = {
            RemitDialog(this,it).show()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }


    }

    override fun viewModelListeners() {
        viewModel.apply {
            getPqrsSuccess.observe(this@PQRSActivity) {
                adapter.list = it
            }

            progressStatus.observe(this@PQRSActivity) {
                progressDialog.show(it)
            }

            pqrsTypeSuccess.observe(this@PQRSActivity) { pqrsTypeList ->
                this@PQRSActivity.pqrsTypeList = pqrsTypeList
                val spinnerList = ArrayList<String>()
                spinnerList.add("Tipo de PQRS")
                pqrsTypeList.forEach { spinnerList.add(it.description!!) }
                with(binding.spPqrsType) {
                    adapter = ArrayAdapter(
                        this@PQRSActivity,
                        android.R.layout.simple_spinner_item,
                        spinnerList
                    )
                    onItemSelectedListener = this@PQRSActivity
                }
            }
            getPqrsType()
            getPqrs()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.searchPqrsType(idPqrsType)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0) {
            binding.spPqrsType -> {
                idPqrsType = if (p2 != 0) {
                    pqrsTypeList[p2 - 1].idPqrsType ?: -1
                } else {
                    -1
                }
                viewModel.searchPqrsType(idPqrsType)
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}
