package com.academiapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class UserRegister(

    @SerializedName("id_user") var idUser: Int? = null,
    @SerializedName("id_person") var idPerson: Int? = null,
    @SerializedName("person") var person: Person? = Person()

) : Serializable