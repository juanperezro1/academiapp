package com.academiapp.activities

import com.academiapp.activities.LoginDModel.Permisos
import java.io.Serializable

class LoginDModel : Serializable {
    var profiles: List<Profiles?>? = null
    var company: Company? = null
    var permisos: List<Permisos?>? = null
    var user: User? = null
    var definitions: List<Definitions?>? = null
    var token: String? = null

    class Profiles : Serializable {
        var profile_name: String? = null
        var description: Any? = null
        var id_profile: Int? = null
        var status: Boolean? = null
    }

    class Company : Serializable {
        var rector: String? = null
        var is_university: Boolean? = null
        var id_town: Int? = null
        var address: String? = null
        var town: Town? = null
        var phone: String? = null
        var name: String? = null
        var link_logo: String? = null
        var sector: String? = null
        var status: Boolean? = null
        var firm_rector_url: String? = null

        class Town : Serializable {
            var id_town: Int? = null
            var description: String? = null
        }
    }

    class Permisos : Serializable {
        var id_module: Int? = null
        var updated_at: String? = null
        var submodules: List<Submodules?>? = null
        var icon: String? = null
        var created_at: String? = null
        var module_name: String? = null
        var id_user: Int? = null
        var status: Boolean? = null

        class Submodules : Serializable {
            var id_submodule: Int? = null
            var id_module: Int? = null
            var submodule_name: String? = null
            var updated_at: String? = null
            var icon: String? = null
            var options: List<Options?>? = null
            var created_at: String? = null
            var id_user: Int? = null
            var status: Boolean? = null

            class Options : Serializable {
                var id_submodule: Int? = null
                var type_option: String? = null
                var sw_app_movil: Boolean? = null
                var icon: String? = null
                var created_at: String? = null
                var option_name: String? = null
                var id_user: Int? = null
                var url: String? = null
                var shortcut: String? = null
                var updated_at: String? = null
                var id_option: Int? = null
                var sw_university: Any? = null
                var status: Boolean? = null
            }
        }
    }

    class User : Serializable {
        var is_admin: Boolean? = null
        var last_login_at: String? = null
        var id_person: Int? = null
        var person: Person? = null
        var id_user: Int? = null
        var username: String? = null

        class Person : Serializable {
            var id_person: Int? = null
            var is_teacher: Boolean? = null
            var last_name: String? = null
            var middle_name: String? = null
            var first_name: String? = null
            var is_student: Boolean? = null
            var email: String? = null
            var second_last_name: String? = null
        }
    }

    class Definitions : Serializable {
        var value: String? = null
        var id_definition: Int? = null
        var key: String? = null
        var status: Boolean? = null
    }
}