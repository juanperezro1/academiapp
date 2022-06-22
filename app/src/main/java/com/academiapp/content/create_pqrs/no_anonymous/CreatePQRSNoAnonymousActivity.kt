package com.academiapp.content.create_pqrs.no_anonymous

import android.R
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.academiapp.base.ActivityViewBinding
import com.academiapp.content.login.LoginActivity
import com.academiapp.databinding.ActivityCreatePqrsnoAnonymusBinding
import com.academiapp.models.DocumentTypes
import com.academiapp.models.InstitutionAreas
import com.academiapp.models.PqrsTypes
import com.academiapp.utils.openActivity
import com.academiapp.utils.showMessage

class CreatePQRSNoAnonymousActivity :
    ActivityViewBinding<ActivityCreatePqrsnoAnonymusBinding, CreatePQRSNoAnonymousVM>(),
    AdapterView.OnItemSelectedListener {
    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityCreatePqrsnoAnonymusBinding.inflate(layoutInflater)

    private var documentTypeList = ArrayList<DocumentTypes>()
    private var pqrsTypeList = ArrayList<PqrsTypes>()
    private var institutionAreaList = ArrayList<InstitutionAreas>()

    private var idDocumentType: Int = -1
    private var idPqrsType: Int = -1
    private var idInstitutionArea: Int = -1

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
                viewModel.sendPqrsNoAnonymous(
                    idDocumentType,
                    idPqrsType,
                    idInstitutionArea,
                    etMessage.text.toString(),
                    etNumDocument.text.toString(),
                    etFirstName.text.toString(),
                    etLastName.text.toString(),
                    etPhone.text.toString(),
                    etEmail.text.toString()
                )
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
            documentTypeSuccess.observe(this@CreatePQRSNoAnonymousActivity) { documentTypeList ->
                this@CreatePQRSNoAnonymousActivity.documentTypeList = documentTypeList
                val spinnerList = ArrayList<String>()
                documentTypeList.forEach { spinnerList.add(it.description!!) }
                with(binding.spDocumentType) {
                    adapter = ArrayAdapter(
                        this@CreatePQRSNoAnonymousActivity,
                        R.layout.simple_spinner_item,
                        spinnerList
                    )
                    onItemSelectedListener = this@CreatePQRSNoAnonymousActivity
                }
            }

            pqrsTypeSuccess.observe(this@CreatePQRSNoAnonymousActivity) { pqrsTypeList ->
                this@CreatePQRSNoAnonymousActivity.pqrsTypeList = pqrsTypeList
                val spinnerList = ArrayList<String>()
                pqrsTypeList.forEach { spinnerList.add(it.description!!) }
                with(binding.spPQRSType) {
                    adapter = ArrayAdapter(
                        this@CreatePQRSNoAnonymousActivity,
                        R.layout.simple_spinner_item,
                        spinnerList
                    )
                    onItemSelectedListener = this@CreatePQRSNoAnonymousActivity
                }
            }

            institutionAreaSuccess.observe(this@CreatePQRSNoAnonymousActivity) { institutionAreaList ->
                this@CreatePQRSNoAnonymousActivity.institutionAreaList = institutionAreaList
                val spinnerList = ArrayList<String>()
                institutionAreaList.forEach { spinnerList.add(it.description!!) }
                with(binding.spArea) {
                    adapter = ArrayAdapter(
                        this@CreatePQRSNoAnonymousActivity,
                        R.layout.simple_spinner_item,
                        spinnerList
                    )
                    onItemSelectedListener = this@CreatePQRSNoAnonymousActivity
                }

            }

            error.observe(this@CreatePQRSNoAnonymousActivity) {
                showMessage(it)
            }

            progressStatus.observe(this@CreatePQRSNoAnonymousActivity) {
                progressDialog.show(it)
            }

            sendPqrsNoAnonymousSuccess.observe(this@CreatePQRSNoAnonymousActivity){
                showMessage(it)
                finish()
            }

            getDocumentType()
            getInstitutionAreas()
            getPqrsType()
        }

    }

}