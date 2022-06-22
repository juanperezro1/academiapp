package com.academiapp.models

import com.google.gson.annotations.SerializedName

data class PqrsResponse(
    @SerializedName("pqrs") var pqrs: ArrayList<Pqrs> = arrayListOf(),
    @SerializedName("count") var count: Int? = null
)