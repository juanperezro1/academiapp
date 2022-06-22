package com.academiapp.content.pqrs.edit

import android.R
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.academiapp.base.ActivityViewBinding
import com.academiapp.content.audits.AuditsActivity
import com.academiapp.content.pqrs.PQRSActivity
import com.academiapp.databinding.ActivityEditPqrsactivityBinding
import com.academiapp.models.*
import com.academiapp.utils.openActivity
import com.academiapp.utils.showMessage

class EditPQRSActivity : ActivityViewBinding<ActivityEditPqrsactivityBinding, EditPqrsVM>(),
    AdapterView.OnItemSelectedListener {

    private var pqrsStatusList = ArrayList<PqrsStatus>()
    private var pqrsTypeList = ArrayList<PqrsTypes>()
    private var institutionAreaList = ArrayList<InstitutionAreas>()

    private var idPqrsStatus: Int = -1
    private var idPqrsType: Int = -1
    private var idInstitutionArea: Int = -1
    lateinit var pqrs : Pqrs

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityEditPqrsactivityBinding.inflate(layoutInflater)


    override fun viewListeners() {
        progressDialog.show(true)
        pqrs = intent.extras?.getSerializable("pqrs") as Pqrs
        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }

            btnMyAudits.setOnClickListener {
                openActivity(AuditsActivity::class.java, extras = {
                    putSerializable("auditList",pqrs.audits)
                })
            }

            btnSave.setOnClickListener {
                viewModel.editPqrs(pqrs.idPqrs ?: -1,idPqrsType,idInstitutionArea,idPqrsStatus)
            }

            txtDescription.text = pqrs.description
            txtDate.text = pqrs.createdAt?.split(" ")?.get(0) ?: ""
        }
    }

    override fun viewModelListeners() {
        viewModel.apply {

            pqrsStatusSuccess.observe(this@EditPQRSActivity){pqrsStatusList ->
                this@EditPQRSActivity.pqrsStatusList = pqrsStatusList
                val spinnerList = ArrayList<String>()
                pqrsStatusList.forEach { spinnerList.add(it.description!!) }
                with(binding.spPqrsStatus){
                    adapter = ArrayAdapter(
                        this@EditPQRSActivity,
                        R.layout.simple_spinner_item,
                        spinnerList
                    )
                    onItemSelectedListener = this@EditPQRSActivity
                }
                pqrsStatusList.forEachIndexed { index, pqrsStatus ->
                    if (pqrsStatus.idPqrsStatus == pqrs.pqrsStatus?.idPqrsStatus){
                        binding.spPqrsStatus.setSelection(index)
                    }
                }
            }

            pqrsTypeSuccess.observe(this@EditPQRSActivity) { pqrsTypeList ->
                this@EditPQRSActivity.pqrsTypeList = pqrsTypeList
                val spinnerList = ArrayList<String>()
                pqrsTypeList.forEach { spinnerList.add(it.description!!) }
                with(binding.spPQRSType) {
                    adapter = ArrayAdapter(
                        this@EditPQRSActivity,
                        R.layout.simple_spinner_item,
                        spinnerList
                    )
                    onItemSelectedListener = this@EditPQRSActivity
                }

                pqrsTypeList.forEachIndexed { index, pqrsTypes ->
                    if (pqrsTypes.idPqrsType == pqrs.pqrsType?.idPqrsType){
                        binding.spPQRSType.setSelection(index)
                    }
                }
            }

            institutionAreaSuccess.observe(this@EditPQRSActivity) { institutionAreaList ->
                this@EditPQRSActivity.institutionAreaList = institutionAreaList
                val spinnerList = ArrayList<String>()
                institutionAreaList.forEach { spinnerList.add(it.description!!) }
                with(binding.spArea) {
                    adapter = ArrayAdapter(
                        this@EditPQRSActivity,
                        R.layout.simple_spinner_item,
                        spinnerList
                    )
                    onItemSelectedListener = this@EditPQRSActivity
                }

                institutionAreaList.forEachIndexed { index, institutionAreas ->
                    if(institutionAreas.idInstitutionArea == pqrs.idInstitutionArea){
                        binding.spArea.setSelection(index)
                    }
                }
            }

            editPqrsSuccess.observe(this@EditPQRSActivity){
                showMessage("Pqrs Guardado con exito")
                finish()
            }

            error.observe(this@EditPQRSActivity) {
                showMessage(it)
            }

            progressStatus.observe(this@EditPQRSActivity) {
                progressDialog.show(it)
            }

            getPqrsType()
            getPqrsStatus()
            getInstitutionAreas()
        }
    }

    override fun onItemSelected(p0: AdapterView<*>, p1: View, p2: Int, p3: Long) {
        when (p0) {
            binding.spPqrsStatus -> {
                idPqrsStatus = pqrsStatusList[p2].idPqrsStatus ?: -1
            }
            binding.spPQRSType -> {
                idPqrsType = pqrsTypeList[p2].idPqrsType ?: -1
            }
            binding.spArea -> {
                idInstitutionArea = institutionAreaList[p2].idInstitutionArea ?: -1
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}