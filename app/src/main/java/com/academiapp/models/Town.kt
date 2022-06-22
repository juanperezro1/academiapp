package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class Town(

    @SerializedName("id_town") var idTown: Int? = null,
    @SerializedName("description") var description: String? = null

)