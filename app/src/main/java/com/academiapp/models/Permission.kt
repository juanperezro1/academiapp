package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class Permission(

    @SerializedName("id_module") var idModule: Int? = null,
    @SerializedName("module_name") var moduleName: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("id_user") var idUser: Int? = null,
    @SerializedName("submodules") var submodules: ArrayList<Submodules> = arrayListOf()

)