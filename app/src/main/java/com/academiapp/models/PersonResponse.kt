package com.academiapp.models

import com.google.gson.annotations.SerializedName

data class PersonResponse (
    @SerializedName("persons") var persons : ArrayList<Person> ?= null
)