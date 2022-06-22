package com.academiapp.content.pqrs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academiapp.models.Pqrs
import com.academiapp.models.PqrsTypes
import com.academiapp.services.RetrofitApi
import com.academiapp.services.RetrofitConnection
import com.academiapp.services.SharedPreferencesManager

class PqrsVM(
    private val rc: RetrofitConnection,
    private val api: RetrofitApi,
    private val sp: SharedPreferencesManager
) : ViewModel() {

    private val _getPqrsSuccess = MutableLiveData<ArrayList<Pqrs>>()
    val getPqrsSuccess: LiveData<ArrayList<Pqrs>> get() = _getPqrsSuccess

    private val _pqrsTypeSuccess = MutableLiveData<ArrayList<PqrsTypes>>()
    val pqrsTypeSuccess: LiveData<ArrayList<PqrsTypes>> get() = _pqrsTypeSuccess

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _progressStatus = MutableLiveData<Boolean>()
    val progressStatus: LiveData<Boolean> get() = _progressStatus

    private var count = 0
    private var isFirt = true
    private fun checkProgressStatus() {
        count++
        if (count >= 2) {
            _progressStatus.postValue(false)
            isFirt = false
            count = 0
        }
    }

    fun getPqrs() {
        _progressStatus.postValue(true)
        rc.connection(api.getPqrs(authHeader = "Bearer ${sp.getUserToken()}"), success = {
            checkProgressStatus()
            _getPqrsSuccess.postValue(it!!.pqrs)
        }, error = {
            checkProgressStatus()
            _error.postValue(it)
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


    fun searchPqrsType(type: Int) {
        count++
        if (type == -1) {
            if(!isFirt)
            getPqrs()
        } else {
            _progressStatus.postValue(true)
            rc.connection(
                api.searchPqrsPerType(authHeader = "Bearer ${sp.getUserToken()}", type),
                success = {
                    _getPqrsSuccess.postValue(it!!.pqrs)
                    checkProgressStatus()
                },
                error = {
                    _error.postValue(it)
                    checkProgressStatus()
                })
        }

    }

}