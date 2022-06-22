package com.academiapp.models

import com.academiapp.models.Options
import com.google.gson.annotations.SerializedName


data class Submodules(

    @SerializedName("id_submodule") var idSubmodule: Int? = null,
    @SerializedName("submodule_name") var submoduleName: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("id_module") var idModule: Int? = null,
    @SerializedName("id_user") var idUser: Int? = null,
    @SerializedName("options") var options: ArrayList<Options> = arrayListOf()

)