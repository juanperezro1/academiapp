package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class InstitutionAreas(

    @SerializedName("id_institution_area") var idInstitutionArea: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("status") var status: Boolean? = null

)