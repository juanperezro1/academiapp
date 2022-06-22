package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class Definitions(

    @SerializedName("id_definition") var idDefinition: Int? = null,
    @SerializedName("key") var key: String? = null,
    @SerializedName("value") var value: String? = null,
    @SerializedName("status") var status: Boolean? = null

)