package com.academiapp.activities

import com.academiapp.activities.ConsultancyDModel.ProjectsConsultancies
import com.academiapp.activities.ConsultancyDModel.ProjectsConsultancies.Project
import java.io.Serializable

class ConsultancyDModel : Serializable {
    var count: Int? = null
    var projectsConsultancies: List<ProjectsConsultancies?>? = null

    class ProjectsConsultancies : Serializable {
        var consultancy_type: Boolean? = null
        var consultancy: String? = null
        var id_person: Int? = null
        var updated_at: String? = null
        var person: Person? = null
        var created_at: String? = null
        var project: Project? = null
        var id_user: Int? = null
        var id_project_degree: Int? = null
        var consultancy_date: String? = null
        var user: User? = null
        var id_project_degree_consultancy: Int? = null

        class Person : Serializable {
            var id_person: Int? = null
        }

        class Project : Serializable {
            var id_project_degree: Int? = null
        }

        class User : Serializable {
            var id_user: Int? = null
            var username: String? = null
        }
    }
}