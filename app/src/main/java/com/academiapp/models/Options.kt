package com.academiapp.models

import com.google.gson.annotations.SerializedName


data class Options(

    @SerializedName("id_option") var idOption: Int? = null,
    @SerializedName("option_name") var optionName: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("type_option") var typeOption: String? = null,
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("id_submodule") var idSubmodule: Int? = null,
    @SerializedName("id_user") var idUser: Int? = null,
    @SerializedName("shortcut") var shortcut: String? = null,
    @SerializedName("sw_university") var swUniversity: String? = null,
    @SerializedName("sw_app_movil") var swAppMovil: Boolean? = false,
    //@SerializedName("favorite_frequent") var favoriteFrequent: FavoriteFrequent? = null

)