package com.academiapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pqrs(
    @SerializedName("id_pqrs") var idPqrs: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("id_pqrs_type") var idPqrsType: Int? = null,
    @SerializedName("id_pqrs_status") var idPqrsStatus: Int? = null,
    @SerializedName("id_institution_area") var idInstitutionArea: Int? = null,
    @SerializedName("pqrs_type") var pqrsType: PqrsTypes? = PqrsTypes(),
    @SerializedName("pqrs_status") var pqrsStatus: PqrsStatus? = PqrsStatus(),
    @SerializedName("pqrs_area") var pqrsArea: PqrsArea? = PqrsArea(),
    @SerializedName("audits") var audits: ArrayList<Audits> = arrayListOf(),
    @SerializedName("remisions") var remisions: ArrayList<Remisions> = arrayListOf()
) : Serializable