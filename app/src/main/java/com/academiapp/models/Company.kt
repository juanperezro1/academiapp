package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class Company(

    @SerializedName("name") var name: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("rector") var rector: String? = null,
    @SerializedName("sector") var sector: String? = null,
    @SerializedName("link_logo") var linkLogo: String? = null,
    @SerializedName("id_town") var idTown: Int? = null,
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("firm_rector_url") var firmRectorUrl: String? = null,
    @SerializedName("is_university") var isUniversity: Boolean? = null,
    @SerializedName("town") var town: Town? = Town()

)