package com.academiapp.activities

import com.academiapp.activities.AddWorkModel.PeoplePut
import java.io.Serializable

class PutDegreeModeld(
    var description: String, var id_project_degree: Int, var title: String,
    var people: List<PeoplePut>
) : Serializable