package com.academiapp.models

import com.google.gson.annotations.SerializedName

data class FavoriteFrequent (
    @SerializedName("id_favorite_frequent") var idFavoriteFrequent: Int?,
    @SerializedName("type") var type: String?,
    @SerializedName("status") var status : Boolean?,
    @SerializedName("id_user") var idUser : Int?,
    @SerializedName("id_option") var idOption: Int?
)