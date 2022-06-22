package com.academiapp.content.pqrs.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academiapp.models.*
import com.academiapp.services.RetrofitApi
import com.academiapp.services.RetrofitConnection
import com.academiapp.services.SharedPreferencesManager

class EditPqrsVM(private val rc: RetrofitConnection, private val api: RetrofitApi,private val sp : SharedPreferencesManager): ViewModel() {

    private val _pqrsStatusSuccess = MutableLiveData<ArrayList<PqrsStatus>>()
    val pqrsStatusSuccess: LiveData<ArrayList<PqrsStatus>> get() = _pqrsStatusSuccess

    private val _pqrsTypeSuccess = MutableLiveData<ArrayList<PqrsTypes>>()
    val pqrsTypeSuccess: LiveData<ArrayList<PqrsTypes>> get() = _pqrsTypeSuccess

    private val _institutionAreaSuccess = MutableLiveData<ArrayList<InstitutionAreas>>()
    val institutionAreaSuccess: LiveData<ArrayList<InstitutionAreas>> get() = _institutionAreaSuccess

    private val _progressStatus = MutableLiveData<Boolean>()
    val progressStatus: LiveData<Boolean> get() = _progressStatus

    private val _editPqrsSuccess = MutableLiveData<Pqrs>()
    val editPqrsSuccess : LiveData<Pqrs> get() = _editPqrsSuccess

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    private var count = 0

    private fun checkProgressStatus() {
        count++
        if (count >= 3) {
            _progressStatus.postValue(false)
        }
    }

    fun getPqrsStatus() {
        rc.connection(api.getPqrsStatus("Bearer ${sp.getUserToken()}"), success = {
            _pqrsStatusSuccess.postValue(it?.pqrsStatus)
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

    fun editPqrs(idPqrs: Int, idPqrsType:Int, idInstitutionArea : Int, idPqrsStatus : Int){
        val  data = HashMap<String,Any>()
        data["id_pqrs"] = idPqrs
        data["id_pqrs_type"] = idPqrsType
        data["id_institution_area"] = idInstitutionArea
        data["id_pqrs_status"] = idPqrsStatus
        _progressStatus.postValue(true)
        rc.connection(api.postEditPqrs("Bearer ${sp.getUserToken()}",data), success = {
            _progressStatus.postValue(false)
            _editPqrsSuccess.postValue(it)
        }, error = {
            _error.postValue(it)
            _progressStatus.postValue(false)
        })
    }
}