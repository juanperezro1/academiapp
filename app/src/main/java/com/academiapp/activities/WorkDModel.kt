package com.academiapp.activities

import com.academiapp.activities.WorkDModel.ProjectsDegree
import com.academiapp.activities.WorkDModel.ProjectsDegree.Project_modality
import com.academiapp.activities.WorkDModel.ProjectsDegree.Project_status
import com.academiapp.activities.WorkDModel.ProjectsDegree.People.Rol
import com.academiapp.activities.WorkDModel.ProjectsDegree.Ods.Odsdes
import com.academiapp.activities.WorkDModel.ProjectsDegree.Audits.PStatus
import java.io.Serializable

class WorkDModel : Serializable {
    var count: Int? = null
    var projectsDegree: List<ProjectsDegree>? = null

    class ProjectsDegree : Serializable {
        var project_modality: Project_modality? = null
        var id_project_modality: Int? = null
        var laureate: Boolean? = null
        var project_status: Project_status? = null
        var description: String? = null
        var created_at: String? = null
        var key_words: List<Key_words>? = null
        var areas: List<Areas>? = null
        var id_user: Int? = null
        var id_project_degree: Int? = null
        var title: String? = null
        var people: List<People>? = null
        var meritorious: Boolean? = null
        var date_exposure_project: Any? = null
        var updated_at: String? = null
        var finish_date: Any? = null
        var user: User? = null
        var audits: List<Audits>? = null
        var levels: List<Levels>? = null
        var start_date: Any? = null
        var id_project_status: Int? = null
        var ods: List<Ods>? = null

        class Project_modality : Serializable {
            var id_project_modality: Int? = null
            var name: String? = null
        }

        class Project_status : Serializable {
            var description: String? = null
            var id_project_status: Int? = null
        }

        class Key_words : Serializable {
            var id_key: Int? = null
            var id_project_degree: Int? = null
            var key: String? = null
        }

        class Areas : Serializable {
            var area: Area? = null
            var id_area_project: Int? = null
            var id_project_degree: Int? = null

            class Area : Serializable {
                var area: String? = null
                var id_area_project: Int? = null
            }
        }

        class People : Serializable {
            var id_person: Int? = null
            var person: Person? = null
            var id_project_rol: Int? = null
            var id_project_degree: Int? = null
            var rol: Rol? = null

            class Person : Serializable {
                var id_person: Int? = null
                var last_name: String? = null
                var first_name: String? = null
            }

            class Rol : Serializable {
                var id_project_rol: Int? = null
                var description: String? = null
            }
        }

        class User : Serializable {
            var id_user: Int? = null
            var username: String? = null
        }

        class Levels : Serializable {
            var level: Level? = null
            var id_project_level: Int? = null
            var id_project_degree: Int? = null
            var id_project_modality_level: Int? = null

            class Level : Serializable {
                var name: String? = null
                var mandatory: Boolean? = null
                var id_project_modality_level: Int? = null
                var is_requested: Boolean? = null
            }
        }

        class Ods : Serializable {
            var id_ods: Int? = null
            var id_project_degree: Int? = null
            var ods: Odsdes? = null

            class Odsdes : Serializable {
                var id_ods: Int? = null
                var description: String? = null
            }
        }

        class Audits : Serializable {
            var title: Any? = null
            var description: Any? = null
            var date_exposure_project: Any? = null
            var finish_date: Any? = null
            var start_date: Any? = null
            var project_status: PStatus? = null

            class PStatus : Serializable {
                var description: Any? = null
            }
        }
    }
}