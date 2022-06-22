package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class PqrsTypeResponse(

    @SerializedName("pqrs_types") var pqrsTypes: ArrayList<PqrsTypes> = arrayListOf(),
    @SerializedName("count") var count: Int? = null

)