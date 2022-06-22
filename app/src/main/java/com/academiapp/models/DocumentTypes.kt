package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class DocumentTypes(

    @SerializedName("id_document_type") var idDocumentType: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("status") var status: Boolean? = null

)