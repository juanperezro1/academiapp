package com.academiapp.content.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academiapp.models.Login
import com.academiapp.models.LoginResponse
import com.academiapp.services.RetrofitApi
import com.academiapp.services.RetrofitConnection
import com.academiapp.services.SharedPreferencesManager

class LoginVM(private val rc: RetrofitConnection,private val api : RetrofitApi,private val sp : SharedPreferencesManager) : ViewModel() {

    private val _loginSuccess = MutableLiveData<LoginResponse>()
    val loginSuccess : LiveData<LoginResponse> get() = _loginSuccess

    private val _loginError = MutableLiveData<String>()
    val loginError : LiveData<String> get() = _loginError

    private val _progressStatus = MutableLiveData<Boolean>()
    val progressStatus: LiveData<Boolean> get() = _progressStatus

    fun  login(userName: String, password:String){

        if(userName.isEmpty() || password.isEmpty()){
            _loginError.postValue("Todos los campos deben ser rellenados")
        }else{
            _progressStatus.postValue(true)
            val login = Login(userName,password)
            rc.connection(api.login(login), success = {
                _progressStatus.postValue(false)
                sp.saveUserToken(it!!.token ?: "")

                sp.saveUserName(userName ?: "")
                sp.savePassword(password ?: "")

                sp.saveuserheadquater(it!!.userProgramHeadquarters.size)

                var sss = ""
                var sss1 = ""

                var psss = ""
                var psss1 = ""
                for ((_, _, _, _, _, _, _, submodules) in it.permisos) {
                    for ((_, submoduleName, _, _, _, _, _, _, options) in submodules) {
                        if (submoduleName == "Degree Works") {
                            for ((_, ssd, _, _, _, _, _, _, _, _, _, _, ssd1) in options) {
                                if (ssd != null) {
                                    sss= "$sss,$ssd"

                                }
                                if (ssd1 != null) {

                                    if(ssd1){
                                        sss1= "$sss1,true"
                                    }
                                    else{
                                        sss1= "$sss1,false"
                                    }
                                }
                            }
                        }
                    }
                }


                for ((_, _, _, _, _, _, _, submodules) in it.permisos) {
                    for ((_, submoduleName, _, _, _, _, _, _, options) in submodules) {
                        if (submoduleName == "PQRS") {
                            for ((_, ssd, _, _, _, _, _, _, _, _, _, _, ssd1) in options) {
                                if (ssd != null) {
                                    psss= "$psss,$ssd"

                                }
                                if (ssd1 != null) {

                                    if(ssd1){
                                        psss1= "$psss1,true"
                                    }
                                    else{
                                        psss1= "$psss1,false"
                                    }
                                }
                            }
                        }
                    }
                }

                sp.savepermisos(sss)
                sp.savepermisosmovil(sss1)

                sp.savepermisosp(psss)
                sp.savepermisosmovilp(psss1)

                sp.savepersonid(it!!.user!!.idPerson ?: 0)
                sp.saveisteacher(it!!.user!!.person!!.is_teacher ?: false)
                //sp.saveisteacher(false)

                _loginSuccess.postValue(it)
            }, error = {
                _progressStatus.postValue(false)
                _loginError.postValue(if(it!!.contains("HTTP 422")) "Credenciales invalidas o usuario inactivo" else it)
            })
        }

    }

}