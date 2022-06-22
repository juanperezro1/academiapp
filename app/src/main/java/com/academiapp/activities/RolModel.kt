package com.academiapp.activities

import com.academiapp.activities.RolModel.Projects_roles
import java.io.Serializable

class RolModel : Serializable {
    var count: Int? = null
    var projects_roles: List<Projects_roles?>? = null

    class Projects_roles : Serializable {
        var id_project_rol: Int? = null
        var description: String? = null
        var status: Boolean? = null
    }
}