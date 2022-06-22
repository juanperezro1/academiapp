package com.academiapp.activities

import com.academiapp.activities.PersonDModel.Persons
import com.academiapp.activities.PersonDModel.Persons.Gender
import com.academiapp.activities.PersonDModel.Persons.Information_student
import com.academiapp.activities.PersonDModel.Persons.Place_of_issue
import com.academiapp.activities.PersonDModel.Persons.Rh_factor
import com.academiapp.activities.PersonDModel.Persons.Type_blood
import com.academiapp.activities.PersonDModel.Persons.Document_type
import com.academiapp.activities.PersonDModel.Persons.Information_student.Eps
import com.academiapp.activities.PersonDModel.Persons.Information_student.Disabilities
import com.academiapp.activities.PersonDModel.Persons.Information_student.Ethnic_group
import com.academiapp.activities.PersonDModel.Persons.Information_student.Neighborhood
import com.academiapp.activities.PersonDModel.Persons.Information_student.Disabilities.Disability
import java.io.Serializable

class PersonDModel : Serializable {
    var persons: List<Persons?>? = null
    var count: Int? = null

    class Persons : Serializable {
        var is_attendant: Boolean? = null
        var gender: Gender? = null
        var birth_date: String? = null
        var created_at: String? = null
        var information_student: Information_student? = null
        var document_id = 0f
            private set
        var is_student: Boolean? = null
        var is_other: Boolean? = null
        var information_attendant: Any? = null
        var place_of_issue: Place_of_issue? = null
        var rh_factor: Rh_factor? = null
        var is_teacher: Boolean? = null
        var updated_at: String? = null
        var id_gender: Int? = null
        var type_blood: Type_blood? = null
        var information_candidate: Any? = null
        var information_teacher: Any? = null
        var first_name: String? = null
        var email: String? = null
        var second_last_name: String? = null
        var document_type: Document_type? = null
        var id_person: Int? = null
        var address: String? = null
        var id_document_type: Int? = null
        var town: Place_of_issue? = null
        var last_name: String? = null
        var id_user: Int? = null
        var middle_name: Any? = null
        var id_town: Int? = null
        var id_qr: String? = null
        var birthplace: Place_of_issue? = null
        var id_type_blood: Int? = null
        var phone: String? = null
        var is_candidated: Boolean? = null
        var status: Boolean? = null
        var id_rh_factor: Int? = null
        fun setDocument_id(document_id: Int) {
            this.document_id = document_id.toFloat()
        }

        class Gender : Serializable {
            var id_gender: Int? = null
            var description: String? = null
        }

        class Information_student : Serializable {
            var id_person: Int? = null
            var id_ethnic_group: Int? = null
            var id_neighborhood: Int? = null
            var id_stratum: Int? = null
            var eps: Eps? = null
            var stratum: Stratum? = null
            var disabilities: List<Disabilities?>? = null
            var ethnic_group: Ethnic_group? = null
            var neighborhood: Neighborhood? = null
            var id_eps: Int? = null
            var id_student: Int? = null
            var status: Boolean? = null

            class Eps : Serializable {
                var description: String? = null
                var id_eps: Int? = null
            }

            class Stratum : Serializable {
                var id_stratum: Int? = null
                var description: String? = null
            }

            class Disabilities : Serializable {
                var disability: Disability? = null
                var id_disability: Int? = null
                var id_disability_student: Int? = null
                var id_student: Int? = null

                class Disability : Serializable {
                    var description: String? = null
                    var id_disability: Int? = null
                }
            }

            class Ethnic_group : Serializable {
                var id_ethnic_group: Int? = null
                var description: String? = null
            }

            class Neighborhood : Serializable {
                var id_neighborhood: Int? = null
                var description: String? = null
            }
        }

        class Place_of_issue : Serializable {
            var id_town: Int? = null
            var description: String? = null
        }

        class Rh_factor : Serializable {
            var description: String? = null
            var id_rh_factor: Int? = null
        }

        class Type_blood : Serializable {
            var id_type_blood: Int? = null
            var description: String? = null
        }

        class Document_type : Serializable {
            var id_document_type: Int? = null
            var description: String? = null
        }
    }
}