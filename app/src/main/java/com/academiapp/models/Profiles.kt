package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class Profiles(

    @SerializedName("id_profile") var idProfile: Int? = null,
    @SerializedName("profile_name") var profileName: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("status") var status: Boolean? = null

)