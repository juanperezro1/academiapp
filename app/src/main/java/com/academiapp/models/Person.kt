package com.academiapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Person(

    @SerializedName("id_person") var idPerson: Int? = null,
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("middle_name") var middleName: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("second_last_name") var secondLastName: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("is_teacher") var is_teacher: Boolean? = false,
    @SerializedName("is_student") var is_student: Boolean? = false

) : Serializable