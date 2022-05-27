package com.example.itmedbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface SaveInfoService{
    @FormUrlEncoded
    @POST("/user/save-infouser")
    Call <Boolean> saveInfoOfUser(@Field("data") List<String> data);
}

public class SignFormActivity extends AppCompatActivity implements TransferSignData{
    public EditText Name,DB,Region,Locality,Adress,SNILS,OMS;
    public Switch IsDoctor;
    public Boolean IsDoc = false;
    public List<String> data = new ArrayList<>();

    public void getSignData(String one,String two){
        if(IsDoctor.isChecked()) IsDoc = true;
        data.add(Name.getText().toString());
        data.add(DB.getText().toString());
        data.add(Region.getText().toString());
        data.add(Locality.getText().toString());
        data.add(Adress.getText().toString());
        data.add(SNILS.getText().toString());
        data.add(OMS.getText().toString());
        data.add(IsDoc.toString());
        data.add(one);
        data.add(two);
        data.add(getId());
        new RequestToServer().execute("");
    }

    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.210:8080")
            .addConverterFactory(GsonConverterFactory.create()).build();
    public class RequestToServer extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings){
            com.example.itmedbook.SaveInfoService saveInfoService = retrofit.create(com.example.itmedbook.SaveInfoService.class);
            Call<Boolean> call = saveInfoService.saveInfoOfUser(data);
            try{
                Response<Boolean> response = call.execute();
                Boolean log = response.body();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                              if(log == true)
                              {   SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

                                  Toast.makeText(getApplicationContext(),"Данные успешно сохранены",Toast.LENGTH_SHORT).show();
                                  Intent intent = new Intent(SignFormActivity.this,StartActivity.class);
                                  intent.putExtra("dataOfUser",data.get(0));
                                  intent.putExtra("DBUser",data.get(1));
                                  startActivity(intent);
                              }

                        }});
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_form);
        Name = findViewById(R.id.Name);
        DB = findViewById(R.id.DB);
        Region = findViewById(R.id.Region);
        Locality = findViewById(R.id.Locality);
        Adress = findViewById(R.id.Adress);
        SNILS = findViewById(R.id.SNILS);
        OMS = findViewById(R.id.OMS);
        IsDoctor = findViewById(R.id.IsDoctor);
        ScrollView scrollView = new ScrollView(this);

        SecondSignForm_Fragment secondSignForm_fragment = new SecondSignForm_Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.Frame_Second_SignForm, secondSignForm_fragment).commit();


    }

    public String getId(){
        Bundle args = getIntent().getExtras();
        String id = args.get("id_sign").toString();
        return id;
    }
}