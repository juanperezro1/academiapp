package com.academiapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.academiapp.R;
import com.academiapp.activities.AreasDModel;
import com.academiapp.activities.JsonPlaceHolderApi;
import com.academiapp.activities.LoginDModel;
import com.academiapp.activities.LoginSubmit;
import com.academiapp.activities.ModalisedDModel;
import com.academiapp.activities.ODSDModel;
import com.academiapp.app.MyApplication;
import com.academiapp.content.degree_work.DWorkActivity;
import com.academiapp.functions.ApiBaseURl;
import com.academiapp.services.SharedPreferencesManager;
import com.academiapp.widgets.CustomProgress;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformacionGeneralEditFragmentJava#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformacionGeneralEditFragmentJava extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    View v;
    EditText edt_Titulo, edt_Resumen_edit, edt_Palabras_clave;
    Spinner sp_modalidad, sp_Areas, sp_Ods;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public InformacionGeneralEditFragmentJava() {
        // Required empty public constructor
    }
    CustomProgress customProgress;
    ArrayAdapter<String> modalisedadapter;
    ArrayAdapter<String> areaadapter;
    ArrayAdapter<String> odsadapter;
    boolean ismodalised=false;
    boolean isods=false;
    boolean isareas=false;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customProgress=new CustomProgress(getContext());
        //customProgress.show(true);

        SharedPreferencesManager sharedPreferencesManager=new SharedPreferencesManager(getContext());
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " +sharedPreferencesManager.getUserToken() )
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        SharedPreferencesManager sp11 = new SharedPreferencesManager(MyApplication.Companion.applicationContext());
        String mainurl11=sp11.getmainurl();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(mainurl11)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        modalisedadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
        areaadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
        odsadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);

        //get the spinner from the xml.


        jsonPlaceHolderApi.GetModalised().enqueue(new Callback<ModalisedDModel>() {
            @Override
            public void onResponse(Call<ModalisedDModel> call, Response<ModalisedDModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    customProgress.show(false);
                }
                ismodalised=true;
                customProgress.show(false);
                for (ModalisedDModel.ProjectModalities projectModalities:
                response.body().getProjectModalities()) {
                    modalisedadapter.add(projectModalities.getName());
                }

                sp_modalidad.setAdapter(modalisedadapter);
            }

            @Override
            public void onFailure(Call<ModalisedDModel> call, Throwable t) {
                Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                customProgress.show(false);
            }
        });


        jsonPlaceHolderApi.GetAreas().enqueue(new Callback<AreasDModel>() {
            @Override
            public void onResponse(Call<AreasDModel> call, Response<AreasDModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    customProgress.show(false);
                }
                ismodalised=true;
                customProgress.show(false);
                for (AreasDModel.Areas_projects projectModalities:
                        response.body().getAreas_projects()) {
                    areaadapter.add(projectModalities.getArea());
                }

                sp_Areas.setAdapter(areaadapter);
            }

            @Override
            public void onFailure(Call<AreasDModel> call, Throwable t) {
                Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                customProgress.show(false);
            }
        });


        jsonPlaceHolderApi.GetODS().enqueue(new Callback<ODSDModel>() {
            @Override
            public void onResponse(Call<ODSDModel> call, Response<ODSDModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    customProgress.show(false);
                }
                ismodalised=true;
                customProgress.show(false);
                for (ODSDModel.Ods projectModalities:
                        response.body().getOds()) {
                    odsadapter.add(projectModalities.getDescription());
                }

                sp_Ods.setAdapter(odsadapter);
            }

            @Override
            public void onFailure(Call<ODSDModel> call, Throwable t) {
                Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                customProgress.show(false);
            }
        });









        //customProgress.show(false);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformacionGeneralEditFragmentJava.
     */
    // TODO: Rename and change types and number of parameters
    public static InformacionGeneralEditFragmentJava newInstance(String param1, String param2) {
        InformacionGeneralEditFragmentJava fragment = new InformacionGeneralEditFragmentJava();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_informacion_general_edit, container, false);
        edt_Titulo = v.findViewById(R.id.edt_Titulo);
        edt_Resumen_edit = v.findViewById(R.id.edt_Resumen_edit);
        edt_Palabras_clave = v.findViewById(R.id.edt_Palabras_clave);
        sp_modalidad = v.findViewById(R.id.sp_modalidad);
        sp_Areas = v.findViewById(R.id.sp_Areas);
        sp_Ods = v.findViewById(R.id.sp_Ods);

        return v;
    }



}