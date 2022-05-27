package com.example.itmedbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface SignService{
    @FormUrlEncoded
    @POST("/user/save")
    Call<Integer> save(@Field("login") String login, @Field("password") String password);
}
public class SignActivity extends AppCompatActivity implements TransferFrag{
    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.210:8080")
            .addConverterFactory(GsonConverterFactory.create()).build();
    public  String login = "";
    public  String password = "";
    public void onData(boolean IsMainActiv,String log,String pass){
        if(!IsMainActiv) {
            login = log;
            password = pass;
            Log.d("AAA",login + password);
            new ToServer().execute("");
        }
    }
    public class ToServer extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings){
            com.example.itmedbook.SignService signService = retrofit.create(com.example.itmedbook.SignService.class);
            Call<Integer> call = signService.save(login,password);
            try{
                Response<Integer> response = call.execute();
                Integer log = response.body();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("AAA",log.toString());
                              if(log != 0){
                                 Intent intent = new Intent(SignActivity.this,SignFormActivity.class);
                                 intent.putExtra("id_sign",log);
                                 startActivity(intent);
                              }
                              else Toast.makeText(getApplicationContext(),"Такой логин или пароль уже существует",Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_sign);
        LogFragment logFragment = new LogFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.loginFrame,logFragment).commit();

//        Button btnSignForm = findViewById(R.id.btnSignForm);
//        btnSignForm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SignActivity.this, SignFormActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}