package com.academiapp.activities

import java.io.Serializable

class AddWorkModel : Serializable {
    var id_project_modality: Int? = null
    var description: String? = null
    var key_words: List<Key_words?>? = null
    var areas: List<Areas?>? = null
    var title: String? = null
    var people: List<People?>? = null
    var ods: List<Ods?>? = null

    class Key_words : Serializable {
        var key: String? = null
    }

    class Areas : Serializable {
        var id_area_project: Int? = null
    }

    class People : Serializable {
        var id_person: Int? = null
        var id_project_rol: Int? = null
    }

    class PeoplePut(var id_person: Int, var id_project_rol: Int, var id_project_degree: Int) :
        Serializable

    class Ods : Serializable {
        var id_ods: Int? = null
    }
}