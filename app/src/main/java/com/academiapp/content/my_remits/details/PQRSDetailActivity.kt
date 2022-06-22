package com.academiapp.content.my_remits.details

import android.view.LayoutInflater
import com.academiapp.base.ActivityViewBinding
import com.academiapp.content.my_remits.MyRemitsActivity
import com.academiapp.content.pqrs.edit.EditPQRSActivity
import com.academiapp.databinding.ActivityPqrsdetailBinding
import com.academiapp.models.Pqrs
import com.academiapp.utils.openActivity
import com.academiapp.utils.showMessage
import com.academiapp.widgets.RemitDialog

class PQRSDetailActivity : ActivityViewBinding<ActivityPqrsdetailBinding, PQRSDetailVM>() {
    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityPqrsdetailBinding.inflate(layoutInflater)

    lateinit var pqrs : Pqrs

    override fun viewListeners() {

        pqrs = intent.extras?.getSerializable("pqrs") as Pqrs

        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }

            btnEdit.setOnClickListener {
                openActivity(EditPQRSActivity::class.java,extras = {
                    putSerializable("pqrs",pqrs)
                })
            }

            btnRemit.setOnClickListener {
                RemitDialog(this@PQRSDetailActivity,pqrs.idPqrs ?: -1).show()
            }

            binding.btnMyRemits.setOnClickListener {
                openActivity(MyRemitsActivity::class.java, extras = {
                    putSerializable("remissionList",pqrs.remisions)
                })
            }

        }
    }
    private  fun setData(){
        binding.apply {
            txtArea.text = pqrs.pqrsArea?.description
            txtPqrsType.text = pqrs.pqrsType?.description
            txtObservations.text = pqrs.description
            txtPqrsStatus.text = pqrs.pqrsStatus?.description
            txtDate.text = pqrs.createdAt?.split(" ")?.get(0) ?: ""
        }
    }

    override fun onResume() {
        super.onResume()
        pqrs = intent.extras?.getSerializable("pqrs") as Pqrs
        viewModel.getPqrs(pqrs.idPqrs ?: -1)
    }


    override fun viewModelListeners() {
        viewModel.apply {
            getPqrsSuccess.observe(this@PQRSDetailActivity){
                pqrs = it
                setData()
            }

            progressStatus.observe(this@PQRSDetailActivity){
                progressDialog.show(it)
            }

            error.observe(this@PQRSDetailActivity){
                showMessage(it)
            }
            getPqrs(pqrs.idPqrs ?: -1)
        }
    }

}