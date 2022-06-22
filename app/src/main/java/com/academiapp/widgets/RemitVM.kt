package com.academiapp.widgets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academiapp.models.Person
import com.academiapp.models.Pqrs
import com.academiapp.services.RetrofitApi
import com.academiapp.services.RetrofitConnection
import com.academiapp.services.SharedPreferencesManager

class RemitVM(
    private val rc: RetrofitConnection,
    private val api: RetrofitApi,
    private val sp: SharedPreferencesManager
) : ViewModel() {

    private val _getPersonSuccess = MutableLiveData<ArrayList<Person>>()
    val getPersonSuccess : LiveData<ArrayList<Person>> get() = _getPersonSuccess

    private val _remissionPqrsSuccess = MutableLiveData<Pqrs>()
    val remissionPqrsSuccess : LiveData<Pqrs> get() = _remissionPqrsSuccess

    private val _progressStatus = MutableLiveData<Boolean>()
    val progressStatus : LiveData<Boolean> get() = _progressStatus

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error


    fun getPersons(){
        _progressStatus.postValue(true)
        rc.connection(api.getPersons("Bearer ${sp.getUserToken()}"), success = {
            _getPersonSuccess.postValue(it?.persons)
            _progressStatus.postValue(false)
        }, error = {
            _error.postValue(it)
            _progressStatus.postValue(false)
        })
    }

    fun remitPqrs(date:String,idPerson : Int, idPqrs: Int){
        val data = HashMap<String,Any>()
        data["remision_date"] = date
        data["id_person"] = idPerson
        data["id_pqrs"] = idPqrs
        _progressStatus.postValue(true)
        rc.connection(api.remissionPqrs("Bearer ${sp.getUserToken()}",data), success = {
            _remissionPqrsSuccess.postValue(it)
            _progressStatus.postValue(false)
        }, error = {
            _error.postValue(it)
            _progressStatus.postValue(false)
        })
    }

}