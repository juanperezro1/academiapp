package com.academiapp.content.create_pqrs.anonymous

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academiapp.models.DocumentTypes
import com.academiapp.models.InstitutionAreas
import com.academiapp.models.PqrsTypes
import com.academiapp.services.RetrofitApi
import com.academiapp.services.RetrofitConnection

class CreatePQRSAnonymousVM(private val rc: RetrofitConnection, private val api: RetrofitApi) :
    ViewModel() {

    private val _documentTypeSuccess = MutableLiveData<ArrayList<DocumentTypes>>()
    val documentTypeSuccess: LiveData<ArrayList<DocumentTypes>> get() = _documentTypeSuccess

    private val _pqrsTypeSuccess = MutableLiveData<ArrayList<PqrsTypes>>()
    val pqrsTypeSuccess: LiveData<ArrayList<PqrsTypes>> get() = _pqrsTypeSuccess

    private val _institutionAreaSuccess = MutableLiveData<ArrayList<InstitutionAreas>>()
    val institutionAreaSuccess: LiveData<ArrayList<InstitutionAreas>> get() = _institutionAreaSuccess

    private val _progressStatus = MutableLiveData<Boolean>()
    val progressStatus: LiveData<Boolean> get() = _progressStatus


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _sendPqrsAnonymousSuccess = MutableLiveData<String>()
    val sendPqrsAnonymousSuccess: LiveData<String> get() = _sendPqrsAnonymousSuccess

    private var count = 0

    private fun checkProgressStatus() {
        count++
        if (count >= 3) {
            _progressStatus.postValue(false)
        }
    }

    fun sendPqrsAnonymous(
        idDocumentType: Int,
        idPqrsType: Int,
        idInstitutionArea: Int,
        message: String
    ) {
        if (idDocumentType == -1 || idPqrsType == -1 || idDocumentType == -1 || message.isEmpty()) {
            _error.postValue("todos los campos son requeridos")
        } else {
            _progressStatus.postValue(true)
            val data = HashMap<String, Any>()
            data["id_pqrs_status"] = 1
            data["id_pqrs_type"] = idPqrsType
            data["id_institution_area"] = idInstitutionArea
            data["description"] = message
            rc.connection(api.savePqrsAnonymous(data), success = {
                _progressStatus.postValue(false)
                _sendPqrsAnonymousSuccess.postValue("PQRS Enviado con éxito")
            }, error = {
                _progressStatus.postValue(false)
                _error.postValue(it)
            })
        }

    }

    fun getDocumentType() {
        rc.connection(api.getDocumentTypes(), success = {
            _documentTypeSuccess.postValue(it?.documentTypes)
            checkProgressStatus()
        }, error = {
            _error.postValue(it)
            checkProgressStatus()
        })
    }

    fun getPqrsType() {
        rc.connection(api.getPqrsTypes(), success = {
            _pqrsTypeSuccess.postValue(it?.pqrsTypes)
            checkProgressStatus()
        }, error = {
            _error.postValue(it)
            checkProgressStatus()
        })
    }

    fun getInstitutionAreas() {
        rc.connection(api.getInstituteAreas(), success = {
            _institutionAreaSuccess.postValue(it?.institutionAreas)
            checkProgressStatus()
        }, error = {
            _error.postValue(it)
            checkProgressStatus()
        })
    }

}