package com.academiapp.services

import android.content.Context
import android.content.SharedPreferences
import com.academiapp.BuildConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesManager(var context: Context) {

    companion object {
        private var KEY = BuildConfig.APPLICATION_ID
        private var newInstance: SharedPreferences? = null
        private const val USERKEY = "userData"
        private const val CRITICALPOINTLISTKEY = "criticalPointList"

        private fun getInstance(context: Context): SharedPreferences {
            if (newInstance == null) {
                newInstance = context.getSharedPreferences(KEY, Context.MODE_PRIVATE)
            }
            return newInstance!!
        }
    }

    fun saveUserToken(token:String){
        getInstance(context).edit().apply{
            putString("token",token)
        }.apply()
    }

    fun getUserToken() : String {
        return getInstance(context).getString("token", "") ?: ""
    }

    fun saveUserName(token:String){
        getInstance(context).edit().apply{
            putString("username",token)
        }.apply()
    }

    fun getUserName() : String {
        return getInstance(context).getString("username", "") ?: ""
    }

    fun savePassword(token:String){
        getInstance(context).edit().apply{
            putString("password",token)
        }.apply()
    }

    fun getPassword() : String {
        return getInstance(context).getString("password", "") ?: ""
    }



    fun saveisteacher(token:Boolean){
        getInstance(context).edit().apply{
            putBoolean("state",token)
        }.apply()
    }

    fun getisteacher() : Boolean {
        return getInstance(context).getBoolean("state", true) ?: true
    }

    fun savepersonid(token:Int){
        getInstance(context).edit().apply{
            putInt("personid",token)
        }.apply()
    }

    fun saveuserheadquater(token:Int){
        getInstance(context).edit().apply{
            putInt("headquater",token)
        }.apply()
    }
    fun savepermisos(token:String){
        getInstance(context).edit().apply{
            putString("savepermisos",token)
        }.apply()
    }
    fun getpermisos() : String {
        return getInstance(context).getString("savepermisos", "") ?: ""
    }

    fun savepermisosmovil(token:String){
        getInstance(context).edit().apply{
            putString("movil",token)
        }.apply()
    }
    fun getpermisosmovil() : String {
        return getInstance(context).getString("movil", "") ?: ""
    }




    fun savemainurl(token:String){
        getInstance(context).edit().apply{
            putString("murl",token)
        }.apply()
    }
    fun getmainurl() : String {
        return getInstance(context).getString("murl", "") ?: ""
    }

    fun savemainurlimg(token:String){
        getInstance(context).edit().apply{
            putString("murlimg",token)
        }.apply()
    }
    fun getmainurlimg() : String {
        return getInstance(context).getString("murlimg", "") ?: ""
    }




    fun savepermisosp(token:String){
        getInstance(context).edit().apply{
            putString("savepermisosp",token)
        }.apply()
    }
    fun getpermisosp() : String {
        return getInstance(context).getString("savepermisosp", "") ?: ""
    }

    fun savepermisosmovilp(token:String){
        getInstance(context).edit().apply{
            putString("movilp",token)
        }.apply()
    }
    fun getpermisosmovilp() : String {
        return getInstance(context).getString("movilp", "") ?: ""
    }



    fun getuserheadquater() : Int {
        return getInstance(context).getInt("headquater", 0) ?: 0
    }

    fun getpersonid() : Int {
        return getInstance(context).getInt("personid", 0) ?: 0
    }

   /* fun saveUserData(loginData: LoginData) {
        val jsonData = Gson().toJson(loginData)
        getInstance(context).edit().apply {
            putString(USERKEY, jsonData)
        }.apply()
    }

    fun saveCriticalPointList(criticalPointList : ArrayList<CriticalPoint>){
        val jsonData = Gson().toJson(criticalPointList)
        getInstance(context).edit().apply {
            putString(CRITICALPOINTLISTKEY, jsonData)
        }.apply()
    }

    fun getCriticalPointList() : ArrayList<CriticalPoint>?{
        val jsonString = getInstance(context).getString(CRITICALPOINTLISTKEY,"")
        val type = object : TypeToken<ArrayList<CriticalPoint>>() {}.type
        return Gson().fromJson(jsonString, type)
    }

    fun getUserData(): LoginData? {
        val jsonString = getInstance(context).getString(USERKEY, "")
        return Gson().fromJson(jsonString, LoginData::class.java) ?: null
    }*/
}