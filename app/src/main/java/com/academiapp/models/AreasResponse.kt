package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class AreasResponse(

    @SerializedName("institution_areas") var institutionAreas: ArrayList<InstitutionAreas> = arrayListOf(),
    @SerializedName("count") var count: Int? = null

)