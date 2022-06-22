package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class User(

    @SerializedName("id_user") var idUser: Int? = null,
    @SerializedName("id_person") var idPerson: Int? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("last_login_at") var lastLoginAt: String? = null,
    @SerializedName("is_admin") var isAdmin: Boolean? = null,
    @SerializedName("person") var person: Person? = Person()

)