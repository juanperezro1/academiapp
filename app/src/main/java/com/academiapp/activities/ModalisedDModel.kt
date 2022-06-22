package com.academiapp.activities

import com.academiapp.activities.ModalisedDModel.ProjectModalities
import com.academiapp.activities.ModalisedDModel.ProjectModalities.Levels.Activities
import java.io.Serializable

class ModalisedDModel : Serializable {
    var count: Int? = null
    var projectModalities: List<ProjectModalities?>? = null

    class ProjectModalities : Serializable {
        var id_program_headquarter: Int? = null
        var id_project_modality: Int? = null
        var updated_at: String? = null
        var name: String? = null
        var created_at: String? = null
        var id_user: Int? = null
        var resolution: String? = null
        var user: User? = null
        var levels: List<Levels?>? = null
        var status: Boolean? = null

        class User : Serializable {
            var id_user: Int? = null
            var username: String? = null
        }

        class Levels : Serializable {
            var id_project_modality: Int? = null
            var activities: List<Activities?>? = null
            var name: String? = null
            var position: Int? = null
            var sw_starter: Any? = null
            var mandatory: Boolean? = null
            var id_project_modality_level: Int? = null
            var is_requested: Boolean? = null

            class Activities : Serializable {
                var id_project_activity: Int? = null
                var name: String? = null
                var mandatory: Boolean? = null
                var upload_file: Boolean? = null
                var id_project_modality_level: Int? = null
            }
        }
    }
}