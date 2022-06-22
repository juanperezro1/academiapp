package com.academiapp.content.create_pqrs.anonymous

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.academiapp.base.ActivityViewBinding
import com.academiapp.content.login.LoginActivity
import com.academiapp.databinding.ActivityCreatePqranonymusBinding
import com.academiapp.models.DocumentTypes
import com.academiapp.models.InstitutionAreas
import com.academiapp.models.PqrsTypes
import com.academiapp.utils.openActivity
import com.academiapp.utils.showMessage

class CreatePQRSAnonymousActivity :
    ActivityViewBinding<ActivityCreatePqranonymusBinding, CreatePQRSAnonymousVM>(),
    AdapterView.OnItemSelectedListener {

    private var documentTypeList = ArrayList<DocumentTypes>()
    private var pqrsTypeList = ArrayList<PqrsTypes>()
    private var institutionAreaList = ArrayList<InstitutionAreas>()

    private var idDocumentType: Int = -1
    private var idPqrsType: Int = -1
    private var idInstitutionArea: Int = -1

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityCreatePqranonymusBinding.inflate(layoutInflater)

    override fun viewListeners() {
        progressDialog.show(true)
        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
            btnLogin.setOnClickListener {
                finishAffinity()
                openActivity(LoginActivity::class.java)
            }

            btnSend.setOnClickListener {
                viewModel.sendPqrsAnonymous(idDocumentType,idPqrsType,idInstitutionArea,etMessage.text.toString())
            }


        }
    }

    override fun onItemSelected(p0: AdapterView<*>, p1: View, p2: Int, p3: Long) {
        when (p0) {
            binding.spDocumentType -> {
                idDocumentType = documentTypeList[p2].idDocumentType ?: -1
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

    override fun viewModelListeners() {
        viewModel.apply {
            documentTypeSuccess.observe(this@CreatePQRSAnonymousActivity) { documentTypeList ->
                this@CreatePQRSAnonymousActivity.documentTypeList = documentTypeList
                val spinnerList = ArrayList<String>()
                documentTypeList.forEach { spinnerList.add(it.description!!) }
                with(binding.spDocumentType) {
                    adapter = ArrayAdapter(
                        this@CreatePQRSAnonymousActivity,
                        android.R.layout.simple_spinner_item,
                        spinnerList
                    )
                    onItemSelectedListener = this@CreatePQRSAnonymousActivity
                }
            }

            pqrsTypeSuccess.observe(this@CreatePQRSAnonymousActivity) { pqrsTypeList ->
                this@CreatePQRSAnonymousActivity.pqrsTypeList = pqrsTypeList
                val spinnerList = ArrayList<String>()
                pqrsTypeList.forEach { spinnerList.add(it.description!!) }
                with(binding.spPQRSType) {
                    adapter = ArrayAdapter(
                        this@CreatePQRSAnonymousActivity,
                        android.R.layout.simple_spinner_item,
                        spinnerList
                    )
                    onItemSelectedListener = this@CreatePQRSAnonymousActivity
                }
            }

            institutionAreaSuccess.observe(this@CreatePQRSAnonymousActivity) { institutionAreaList ->
                this@CreatePQRSAnonymousActivity.institutionAreaList = institutionAreaList
                val spinnerList = ArrayList<String>()
                institutionAreaList.forEach { spinnerList.add(it.description!!) }
                with(binding.spArea) {
                    adapter = ArrayAdapter(
                        this@CreatePQRSAnonymousActivity,
                        android.R.layout.simple_spinner_item,
                        spinnerList
                    )
                    onItemSelectedListener = this@CreatePQRSAnonymousActivity
                }

            }

            error.observe(this@CreatePQRSAnonymousActivity) {
                showMessage(it)
            }

            progressStatus.observe(this@CreatePQRSAnonymousActivity) {
                progressDialog.show(it)
            }

            sendPqrsAnonymousSuccess.observe(this@CreatePQRSAnonymousActivity){
                showMessage(it)
                finish()
            }

            getDocumentType()
            getInstitutionAreas()
            getPqrsType()
        }
    }

}