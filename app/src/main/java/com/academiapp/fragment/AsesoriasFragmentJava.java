package com.academiapp.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.academiapp.R;
import com.academiapp.activities.AddNewActivity;
import com.academiapp.activities.ConsultBody;
import com.academiapp.activities.ConsultancyDModel;
import com.academiapp.activities.JsonPlaceHolderApi;
import com.academiapp.activities.MainActivity;
import com.academiapp.activities.POstModelCOns;
import com.academiapp.activities.WorkDModel;
import com.academiapp.app.MyApplication;
import com.academiapp.content.degree_work.DWorkActivity;
import com.academiapp.content.degree_work.adapter.WorkDegreeAdapter;
import com.academiapp.functions.ApiBaseURl;
import com.academiapp.services.SharedPreferencesManager;
import com.academiapp.widgets.CustomProgress;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AsesoriasFragmentJava#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AsesoriasFragmentJava extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View v;
    RecyclerView rcv;
    AsessoriasAdapter asessoriasAdapter;
    POstModelCOns pOstModelCOns;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Dialog addDialog;
    private TextView sp_persona_edit_dialog;
    private Spinner sp_rol_edit_dialog;
    private EditText sp_rol_edit_dialog3;
    private TextView txt_Guardar_dialog;

    public AsesoriasFragmentJava() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AsesoriasFragmentJava.
     */
    // TODO: Rename and change types and number of parameters
    public static AsesoriasFragmentJava newInstance(String param1, String param2) {
        AsesoriasFragmentJava fragment = new AsesoriasFragmentJava();
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
    int idperson;
    CustomProgress customProgress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_asesorias, container, false);
        pOstModelCOns=new POstModelCOns();
        rcv = v.findViewById(R.id.rcv_asesorias);

        SharedPreferencesManager sharedPreferencesManager=new SharedPreferencesManager(getContext());
        idperson=sharedPreferencesManager.getpersonid();
        ImageView imgghh=v.findViewById(R.id.img_add_asesorias);
        if(sharedPreferencesManager.getisteacher()){
            imgghh.setVisibility(View.VISIBLE);
            imgghh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setEnabled(false);
                    initAddDialog();
                    v.setEnabled(true);
                }
            });
        }



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

        customProgress=new CustomProgress(getContext());
        customProgress.show(true);

        WorkDModel.ProjectsDegree pro=((MainActivity)getContext()).getWorkdetail();

        //ConsultBody ccop=new ConsultBody(pro.getId_project_degree());
        jsonPlaceHolderApi.GetConsultanc(pro.getId_project_degree()).enqueue(new Callback<ConsultancyDModel>() {
            @Override
            public void onResponse(Call<ConsultancyDModel> call, Response<ConsultancyDModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    customProgress.show(false);
                    return;
                }
                customProgress.show(false);

                if(response.body()!=null&&response.body().getProjectsConsultancies().size()>0){
                    rcv.setLayoutManager(new LinearLayoutManager(getContext()));
                    asessoriasAdapter = new AsessoriasAdapter( getContext(), response.body().getProjectsConsultancies());
                    rcv.setAdapter(asessoriasAdapter);
                }
                else{
                    Toast.makeText(getContext(), "Body returns null", Toast.LENGTH_SHORT).show();
                    customProgress.show(false);
                }

            }

            @Override
            public void onFailure(Call<ConsultancyDModel> call, Throwable t) {
                Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                customProgress.show(false);
            }
        });

        //initRcv(list);
        return v;
    }

int mYear,mMonth,mDay;
    private void initAddDialog() {
        addDialog = new Dialog(getContext());
        addDialog.setContentView(R.layout.consukltanc_edit_dialog);
        addDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        sp_persona_edit_dialog = addDialog.findViewById(R.id.sp_persona_edit_dialog);
        sp_rol_edit_dialog = addDialog.findViewById(R.id.sp_rol_edit_dialog);

        List<String> liststr=new ArrayList<>();
        liststr.add("presencial");
        liststr.add("virtual");
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, liststr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_rol_edit_dialog.setAdapter(adapter);





        sp_persona_edit_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "yyyy-MM-dd"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                        sp_persona_edit_dialog.setText(sdf.format(myCalendar.getTime()));

                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                //mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        sp_rol_edit_dialog3 = addDialog.findViewById(R.id.sp_rol_edit_dialog3);
        txt_Guardar_dialog = addDialog.findViewById(R.id.txt_Guardar_dialog);
        
        txt_Guardar_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss1=sp_persona_edit_dialog.getText().toString();
                String ss2=sp_rol_edit_dialog3.getText().toString();
                if(!ss1.equals("")
                        &&!ss2.equals("")){
                    pOstModelCOns.setConsultancy(sp_rol_edit_dialog3.getText().toString());
                    if(sp_rol_edit_dialog.getSelectedItemPosition()==0){
                        pOstModelCOns.setConsultancy_type(false);
                    }
                    else{
                        pOstModelCOns.setConsultancy_type(true);
                    }

                    pOstModelCOns.setId_person(idperson);
                    pOstModelCOns.setConsultancy_date(sp_persona_edit_dialog.getText().toString());
                    pOstModelCOns.setId_project_degree(((MainActivity)getContext()).getWorkdetail().getId_project_degree());

                    customProgress.show(true);
                    addDialog.dismiss();
                    jsonPlaceHolderApi.AddConsultamncy(pOstModelCOns).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
                                customProgress.show(false);
                                addDialog.dismiss();
                                ((MainActivity)getContext()).finish();
                            }
                            else{
                                Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                                customProgress.show(false);
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                            customProgress.show(false);
                        }
                    });

                }

                else{
                    Toast.makeText(getContext(), "Please fill form!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        addDialog.show();
    }

}