package com.academiapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PqrsStatus(

    @SerializedName("id_pqrs_status") var idPqrsStatus: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("status") var status : Boolean? = null
) : Serializable