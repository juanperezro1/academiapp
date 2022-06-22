package com.academiapp.activities

import com.academiapp.activities.LoginSubmit
import com.academiapp.activities.LoginDModel
import com.academiapp.activities.ModalisedDModel
import com.academiapp.activities.AreasDModel
import com.academiapp.activities.ODSDModel
import com.academiapp.activities.PersonDModel
import com.academiapp.activities.RolModel
import com.academiapp.activities.WorkDModel
import com.academiapp.activities.ConsultancyDModel
import com.academiapp.activities.AddWorkModel
import com.academiapp.activities.WorkDModel.ProjectsDegree
import com.academiapp.activities.POstModelCOns
import okhttp3.ResponseBody
import com.academiapp.activities.PutDegreeModeld
import com.academiapp.models.UniModel
import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {

    @GET("api/tenants")
    fun GetUniversity(): Call<UniModel?>?


    @POST("login")
    fun GetLogin(@Body loginSubmit: LoginSubmit?): Call<LoginDModel?>?

    @GET("setup_project")
    fun GetModalised(): Call<ModalisedDModel?>?

    @GET("areas_project")
    fun GetAreas(): Call<AreasDModel?>?

    @GET("ods")
    fun GetODS(): Call<ODSDModel?>?

    @GET("persons")
    fun GetPerson(): Call<PersonDModel?>?

    @GET("projects_roles")
    fun GetRol(): Call<RolModel?>?

    @GET("personAsAStudent")
    fun GetWorkDegree(): Call<WorkDModel?>?

    @GET("personAsADirector")
    fun GetWorkDegreeT(): Call<WorkDModel?>?

    @GET("project_consultancies?")
    fun GetConsultanc(@Query("id_project_degree") id_project_degree: Int?): Call<ConsultancyDModel?>?

    @POST("project_degree")
    fun AddWorkModel(@Body loginSubmit: AddWorkModel?): Call<ProjectsDegree?>?

    @POST("project_consultancies")
    fun AddConsultamncy(@Body loginSubmit: POstModelCOns?): Call<ResponseBody?>?

    @PUT("project_degree/{id_project}")
    fun PutWorkdessgree(
        @Path("id_project") id_project: Int?,
        @Body loginSubmit: PutDegreeModeld?
    ): Call<ProjectsDegree?>?
}