package com.academiapp.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.academiapp.R;
import com.academiapp.activities.EditPersonAddAdapter;
import com.academiapp.activities.JsonPlaceHolderApi;
import com.academiapp.activities.ModalisedDModel;
import com.academiapp.activities.PersonDModel;
import com.academiapp.app.MyApplication;
import com.academiapp.functions.ApiBaseURl;
import com.academiapp.services.SharedPreferencesManager;
import com.academiapp.widgets.CustomProgress;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonasEditFragmentJava#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonasEditFragmentJava extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View v;
    TextView txt_Nombre_detail, txt_Rol_details;
    ImageView imgAdd;
    Dialog addDialog;

    TextView sp_persona_edit_dialog, sp_rol_edit_dialog;
    TextView txt_Guardar_dialog;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CustomProgress customProgress;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public PersonasEditFragmentJava() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customProgress = new CustomProgress(getContext());
        //customProgress.show(true);

        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getContext());
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + sharedPreferencesManager.getUserToken())
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


        //get the spinner from the xml.


        jsonPlaceHolderApi.GetPerson().enqueue(new Callback<PersonDModel>() {
            @Override
            public void onResponse(Call<PersonDModel> call, Response<PersonDModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    customProgress.show(false);
                }


                customProgress.show(false);

                personarecylerview.setHasFixedSize(true);
                personarecylerview.setLayoutManager(new LinearLayoutManager(getContext()));
                EditPersonAddAdapter mAdapter = new EditPersonAddAdapter(getContext(), (List<PersonDModel.Persons>) response.body().getPersons());
                personarecylerview.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<PersonDModel> call, Throwable t) {
                Toast.makeText(getContext(), "Failed!" + t.getMessage(), Toast.LENGTH_SHORT).show();
                customProgress.show(false);
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonasEditFragmentJava.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonasEditFragmentJava newInstance(String param1, String param2) {
        PersonasEditFragmentJava fragment = new PersonasEditFragmentJava();
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
        v = inflater.inflate(R.layout.fragment_personas_edit, container, false);
        txt_Rol_details = v.findViewById(R.id.txt_Rol_details);
        txt_Nombre_detail = v.findViewById(R.id.txt_Nombre_detail);
        imgAdd = v.findViewById(R.id.img_add_personas);
        personarecylerview=v.findViewById(R.id.personarecylerview);

        initAddDialog();
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog.show();
            }
        });

        return v;
    }

    RecyclerView personarecylerview;
    private void initAddDialog() {
        addDialog = new Dialog(getContext());
        addDialog.setContentView(R.layout.perosna_edit_dialog);
        sp_persona_edit_dialog = addDialog.findViewById(R.id.sp_persona_edit_dialog);
        sp_rol_edit_dialog = addDialog.findViewById(R.id.sp_rol_edit_dialog);
        txt_Guardar_dialog = addDialog.findViewById(R.id.txt_Guardar_dialog);
        txt_Guardar_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sp_persona_edit_dialog.getText().toString().equals("")
                &&sp_rol_edit_dialog.getText().toString().equals("")){

                    addDialog.dismiss();
                }

            }
        });

    }
}