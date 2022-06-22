package com.academiapp.models

import com.google.gson.annotations.SerializedName

data class PqrsStatusResponse (
    @SerializedName("pqrs_status") var pqrsStatus : ArrayList<PqrsStatus> = arrayListOf(),
    @SerializedName("count") var count : Int? = null
)
