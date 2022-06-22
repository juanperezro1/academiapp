package com.academiapp.content.degree_work;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.academiapp.R;
import com.academiapp.activities.AddNewActivity;
import com.academiapp.activities.JsonPlaceHolderApi;
import com.academiapp.activities.LoginDModel;
import com.academiapp.activities.LoginSubmit;
import com.academiapp.activities.ModalisedDModel;
import com.academiapp.activities.WorkDModel;
import com.academiapp.app.MyApplication;
import com.academiapp.content.degree_work.adapter.WorkDegreeAdapter;
import com.academiapp.functions.ApiBaseURl;
import com.academiapp.models.LoginResponse;
import com.academiapp.models.Options;
import com.academiapp.models.Permission;
import com.academiapp.models.Submodules;
import com.academiapp.services.SharedPreferencesManager;
import com.academiapp.widgets.CustomProgress;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DWorkActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    WorkDegreeAdapter adapter;

    public boolean addpermission=false;
    public boolean viewpermission=false;
    public boolean editpermission=false;
    SharedPreferencesManager sharedPreferencesManager;
    public List<WorkDModel.ProjectsDegree> mainlist=new ArrayList<>();
    CustomProgress customProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree_work);
        sharedPreferencesManager=new SharedPreferencesManager(DWorkActivity.this);

        PermissionBooleanGet();



        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        findViewById(R.id.linearLayout3).setVisibility(View.GONE);

        customProgress=new CustomProgress(DWorkActivity.this);
        //customProgress.show(true);



        if(sharedPreferencesManager.getisteacher() || !addpermission){
            findViewById(R.id.img_add).setVisibility(View.GONE);
        }
        else{
            findViewById(R.id.img_add).setVisibility(View.VISIBLE);
        }

        findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isadd=false;
                if(!isadd){
                    AlertDialog.Builder builder = new AlertDialog.Builder(DWorkActivity.this);
                    builder.setMessage("User does not have permission!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });


        SharedPreferencesManager sp11 = new SharedPreferencesManager(MyApplication.Companion.applicationContext());
        String mainurl11=sp11.getmainurl();


        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " +sharedPreferencesManager.getUserToken() )
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(mainurl11)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        recyclerView=findViewById(R.id.rvDegreeWork);


//        Call<LoginDModel> call= jsonPlaceHolderApi.GetLogin(new LoginSubmit("an_jemu88","123456"));
//        call.enqueue(new Callback<LoginDModel>() {
//            @Override
//            public void onResponse(Call<LoginDModel> call, Response<LoginDModel> response) {
//                customProgress.show(false);
//            }
//
//            @Override
//            public void onFailure(Call<LoginDModel> call, Throwable t) {
//                Toast.makeText(DWorkActivity.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
//                customProgress.show(false);
//            }
//        });



        findViewById(R.id.img_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DWorkActivity.this, AddNewActivity.class));
            }
        });



//        boolean isadd=false;
//
//        List<String> lli=new ArrayList<>(Arrays.asList(sharedPreferencesManager.getpermisos().split(",")));
//        List<String> llo=new ArrayList<>(Arrays.asList(sharedPreferencesManager.getpermisosmovil().split(",")));
//
//        int iiopooo=0;
//        for (String dfg:
//                lli) {
//            if(dfg.toLowerCase().contains("search")||dfg.toLowerCase().contains("edit")){
//                if(llo.get(iiopooo).toLowerCase().contains("true")){
//                    isadd=true;
//                }
//            }
//            iiopooo++;
//        }

        if(!viewpermission){
            customProgress.show(false);
            Toast.makeText(DWorkActivity.this, "User does not have view permission!", Toast.LENGTH_SHORT).show();
        }
        else{
            LoadData();
        }
    }


    void LoadData(){
        customProgress.show(true);
        if(isNetworkConnected()){
            Call<WorkDModel> call;
            if(sharedPreferencesManager.getisteacher()){
                call=jsonPlaceHolderApi.GetWorkDegreeT();
            }
            else{
                call=jsonPlaceHolderApi.GetWorkDegree();
            }

            call.enqueue(new Callback<WorkDModel>() {
                @Override
                public void onResponse(Call<WorkDModel> call, Response<WorkDModel> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(DWorkActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        customProgress.show(false);
                    }
                    customProgress.show(false);

                    WorkDModel workDModel=response.body();



                    if(workDModel!=null){

                        Collections.sort(workDModel.getProjectsDegree(), new Comparator<WorkDModel.ProjectsDegree>(){
                            public int compare(WorkDModel.ProjectsDegree obj1, WorkDModel.ProjectsDegree obj2) {
                                // ## Ascending order
                                //return obj1.firstName.compareToIgnoreCase(obj2.firstName); // To compare string values
                                // return Integer.valueOf(obj1.empId).compareTo(Integer.valueOf(obj2.empId)); // To compare integer values

                                // ## Descending order
                                return obj2.getUpdated_at().compareToIgnoreCase(obj1.getUpdated_at()); // To compare string values
                                // return Integer.valueOf(obj2.empId).compareTo(Integer.valueOf(obj1.empId)); // To compare integer values
                            }
                        });

                        mainlist=workDModel.getProjectsDegree();



                        recyclerView.setHasFixedSize(true);
                        adapter=new WorkDegreeAdapter(DWorkActivity.this,mainlist);
                        recyclerView.setLayoutManager(new LinearLayoutManager(DWorkActivity.this));
                        recyclerView.setAdapter(adapter);
                    }

                }

                @Override
                public void onFailure(Call<WorkDModel> call, Throwable t) {
                    Toast.makeText(DWorkActivity.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                    customProgress.show(false);
                }
            });
        }
        else{
            customProgress.show(false);
            Toast.makeText(DWorkActivity.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    void PermissionBooleanGet(){

        List<String> lli=new ArrayList<>(Arrays.asList(sharedPreferencesManager.getpermisos().split(",")));
        List<String> llo=new ArrayList<>(Arrays.asList(sharedPreferencesManager.getpermisosmovil().split(",")));


        int iiopooo=0;
        for (String dfg:
                lli) {
            if(dfg.toLowerCase().contains("add")){
                if(llo.get(iiopooo).toLowerCase().contains("true")){
                    addpermission=true;

                }
            }
            iiopooo++;
        }


        iiopooo=0;
        for (String dfg:
                lli) {
            if(dfg.toLowerCase().contains("search")){
                if(llo.get(iiopooo).toLowerCase().contains("true")){
                    viewpermission=true;

                }
            }
            iiopooo++;
        }

        iiopooo=0;
        for (String dfg:
                lli) {
            if(dfg.toLowerCase().contains("edit")){
                if(llo.get(iiopooo).toLowerCase().contains("true")){
                    editpermission=true;
                }
            }
            iiopooo++;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AddItem();
    }

    public void AddItem(){
        if(!viewpermission){
            if(adapter==null && MyApplication.Companion.getTempdata()!=null){
                mainlist.add(0, MyApplication.Companion.getTempdata());
                recyclerView.setHasFixedSize(true);
                adapter=new WorkDegreeAdapter(DWorkActivity.this,mainlist);
                recyclerView.setLayoutManager(new LinearLayoutManager(DWorkActivity.this));
                recyclerView.setAdapter(adapter);
                MyApplication.Companion.setTempdata(null);
            }
            else{
                if(MyApplication.Companion.getTempdata()!=null){
                    mainlist.add(0, MyApplication.Companion.getTempdata());
                    adapter.notifyItemChanged(0,MyApplication.Companion.getTempdata());

                    MyApplication.Companion.setTempdata(null);
                }
            }
        }
        else{
            if(MyApplication.Companion.getTempdata()!=null){
                LoadData();
                MyApplication.Companion.setTempdata(null);
            }

        }



    }
}
