package com.academiapp.functions;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("api/personAsAStudent/")
    Call<WorkDegree> GetWorkDegree();

    @GET("api/setup_project/")
    Call<Modality> GetModality();

    @GET("api/persons/")
    Call<People> GetPersons();

    @GET("api/areas_project/")
    Call<Area> GetAreas();

    @GET("api/ods/")
    Call<ODS> GetODS();

    @GET("api/projects_roles/")
    Call<ROL> GetROL();

}
