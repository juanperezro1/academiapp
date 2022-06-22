package com.academiapp.content.my_remits.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academiapp.models.Pqrs
import com.academiapp.services.RetrofitApi
import com.academiapp.services.RetrofitConnection
import com.academiapp.services.SharedPreferencesManager

class PQRSDetailVM(
    private val rc: RetrofitConnection,
    private val api: RetrofitApi,
    private val sp: SharedPreferencesManager
) : ViewModel() {

    private val _getPqrsSuccess = MutableLiveData<Pqrs>()
    val getPqrsSuccess: LiveData<Pqrs> get() = _getPqrsSuccess

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _progressStatus = MutableLiveData<Boolean>()
    val progressStatus: LiveData<Boolean> get() = _progressStatus

    fun getPqrs(idPqrs: Int) {
        _progressStatus.postValue(true)
        rc.connection(api.showPqrs("Bearer ${sp.getUserToken()}",idPqrs), success = {
            _getPqrsSuccess.postValue(it)
            _progressStatus.postValue(false)
        }, error = {
            _error.postValue(it)
            _progressStatus.postValue(false)
        })
    }
}