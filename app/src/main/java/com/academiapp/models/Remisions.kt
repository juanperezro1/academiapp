package com.academiapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Remisions(

    @SerializedName("id_remision_pqrs") var idRemisionPqrs: Int? = null,
    @SerializedName("observation") var observation: String? = null,
    @SerializedName("remision_date") var remisionDate: String? = null,
    @SerializedName("id_pqrs") var idPqrs: Int? = null,
    @SerializedName("id_person") var idPerson: Int? = null,
    @SerializedName("person") var person: Person? = Person()

) : Serializable