package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class LoginResponse(

    @SerializedName("user") var user: User? = User(),
    @SerializedName("token") var token: String? = null,
    @SerializedName("company") var company: Company? = Company(),
    @SerializedName("definitions") var definitions: ArrayList<Definitions> = arrayListOf(),
    @SerializedName("permisos") var permisos: ArrayList<Permission> = arrayListOf(),
    @SerializedName("profiles") var profiles: ArrayList<Profiles> = arrayListOf(),
    @SerializedName("user_program_headquarters") var userProgramHeadquarters: ArrayList<Any> = arrayListOf()

)