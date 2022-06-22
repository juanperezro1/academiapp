package com.academiapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PqrsTypes(

    @SerializedName("id_pqrs_type") var idPqrsType: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("days_maximum_response") var daysMaximumResponse: String? = null,
    @SerializedName("previous_days_alert") var previousDaysAlert: String? = null

) : Serializable