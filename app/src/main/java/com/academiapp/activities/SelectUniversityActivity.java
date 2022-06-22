package com.academiapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.academiapp.R;
import com.academiapp.content.degree_work.DWorkActivity;
import com.academiapp.content.login.LoginActivity;
import com.academiapp.models.UniModel;
import com.academiapp.services.SharedPreferencesManager;
import com.academiapp.widgets.CustomProgress;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectUniversityActivity extends AppCompatActivity {

    List<String> listcount=new ArrayList<>();
    CustomProgress customProgress;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    UniModel modelll;
    SharedPreferencesManager sharedPreferencesManager;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_university);
        sharedPreferencesManager = new SharedPreferencesManager(this);
        if(!sharedPreferencesManager.getUserName().equals("")){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        customProgress=new CustomProgress(SelectUniversityActivity.this);
        customProgress.show(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apitest.academiapp.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        jsonPlaceHolderApi.GetUniversity().enqueue(new Callback<UniModel>() {
            @Override
            public void onResponse(Call<UniModel> call, Response<UniModel> response) {
                customProgress.show(false);


                modelll=response.body();

                for (UniModel.Tenants llttt:
                modelll.getTenants()) {
                    listcount.add(llttt.getName());
                }

                if(listcount.size()>0){
                    //Getting the instance of Spinner and applying OnItemSelectedListener on it
                    spin = findViewById(R.id.spinneruni);
                    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    //Creating the ArrayAdapter instance having the country list
                    ArrayAdapter aa = new ArrayAdapter(SelectUniversityActivity.this,android.R.layout.simple_spinner_item,listcount.toArray());
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spin.setAdapter(aa);



                }


            }

            @Override
            public void onFailure(Call<UniModel> call, Throwable t) {
                Toast.makeText(SelectUniversityActivity.this,"Someting Wrong",Toast.LENGTH_SHORT).show();
                customProgress.show(false);
            }
        });

    }

    public void BTNCLIckk(View view) {
        view.setEnabled(false);
        sharedPreferencesManager.savemainurl(modelll.getTenants().get(spin.getSelectedItemPosition()).getUrl_back());
        sharedPreferencesManager.savemainurlimg(modelll.getTenants().get(spin.getSelectedItemPosition()).getIcon());
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        view.setEnabled(true);
    }
}