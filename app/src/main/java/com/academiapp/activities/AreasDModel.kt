package com.academiapp.activities

import com.academiapp.activities.AreasDModel.Areas_projects
import java.io.Serializable

class AreasDModel : Serializable {
    var count: Int? = null
    var areas_projects: List<Areas_projects?>? = null

    class Areas_projects : Serializable {
        var area: String? = null
        var id_area_project: Int? = null
        var id_user: Int? = null
        var status: Boolean? = null
    }
}