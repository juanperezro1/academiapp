package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class DocumentsTypeResponse(

    @SerializedName("document_types") var documentTypes: ArrayList<DocumentTypes> = arrayListOf(),
    @SerializedName("count") var count: Int? = null

)