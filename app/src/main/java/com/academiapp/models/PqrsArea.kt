package com.academiapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PqrsArea(

    @SerializedName("id_institution_area") var idInstitutionArea: Int? = null,
    @SerializedName("description") var description: String? = null

) : Serializable