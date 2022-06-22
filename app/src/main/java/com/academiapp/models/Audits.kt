package com.academiapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Audits(

    @SerializedName("id_audit_pqrs") var idAuditPqrs: Int? = null,
    @SerializedName("id_pqrs") var idPqrs: Int? = null,
    @SerializedName("observation") var observation: String? = null,
    @SerializedName("id_pqrs_status") var idPqrsStatus: Int? = null,
    @SerializedName("id_institution_area") var idInstitutionArea: Int? = null,
    @SerializedName("id_pqrs_type") var idPqrsType: Int? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("id_user") var idUser: Int? = null,
    @SerializedName("pqrs_status") var pqrsStatus: PqrsStatus? = PqrsStatus(),
    @SerializedName("user_register") var userRegister: UserRegister? = UserRegister(),
    @SerializedName("pqrs_types") var pqrsTypes: PqrsTypes? = PqrsTypes(),
    @SerializedName("pqrs_areas") var pqrsAreas: PqrsAreas? = PqrsAreas()

) : Serializable