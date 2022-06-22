package com.academiapp.activities

import java.io.Serializable

class ODSDModel : Serializable {
    var count: Int? = null
    var ods: List<Ods?>? = null

    class Ods : Serializable {
        var id_ods: Int? = null
        var description: String? = null
        var status: Boolean? = null
    }
}