package com.academiapp.services

import com.academiapp.models.*
import com.academiapp.utils.Constants
import io.reactivex.Observable
import retrofit2.http.*

interface RetrofitApi {
    @POST(Constants.LOGINURL)
    fun login(@Body login: Login) : Observable<LoginResponse>

    @GET(Constants.DOCUMENTTYPEURL)
    fun getDocumentTypes() : Observable<DocumentsTypeResponse>

    @GET(Constants.PRQSTYPEURL)
    fun getPqrsTypes() : Observable<PqrsTypeResponse>

    @GET(Constants.AREAURL)
    fun getInstituteAreas() : Observable<AreasResponse>

    @POST(Constants.SAVEPQRSANONYMOUSURL)
    fun savePqrsAnonymous(@Body data : HashMap<String,Any>) : Observable<Any>

    @GET(Constants.GETPQRSURL)
    fun getPqrs(@Header("Authorization") authHeader : String) : Observable<PqrsResponse>

    @GET(Constants.SEARCHPQRSPERTYPE+"{type}")
    fun searchPqrsPerType(@Header("Authorization") authHeader : String,@Path("type") type : Int) : Observable<PqrsResponse>

    @GET(Constants.GETPQRSSTATUS)
    fun getPqrsStatus(@Header("Authorization") authHeader : String) : Observable<PqrsStatusResponse>

    @POST(Constants.POSTEDITPQRS)
    fun postEditPqrs(@Header("Authorization") authHeader : String, @Body data : HashMap<String,Any>) : Observable<Pqrs>

    @GET(Constants.SHOWPQRS+"{idPqrs}")
    fun showPqrs(@Header("Authorization") authHeader : String,@Path("idPqrs") idPqrs : Int) : Observable<Pqrs>

    @GET(Constants.GETPERSONS)
    fun getPersons(@Header("Authorization") authHeader : String) : Observable<PersonResponse>

    @POST(Constants.POSTREMISSIONPQRS)
    fun remissionPqrs(@Header("Authorization") authHeader : String,@Body data : HashMap<String, Any>) : Observable<Pqrs>

}