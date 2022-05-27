package com.example.itmedbook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

class SignFormat {
    public int id;
    public String login ;
    public String password;
}
interface LogService{
    @FormUrlEncoded
    @POST("/user/logup")
    Call<List<SignFormat>> getUser(@Field("login") String login,@Field("password") String password);

    @FormUrlEncoded
    @POST("/user/found-acc")
    Call<List<MainInfo>> getAcc(@Field("signForm_id") int signForm_id);

}
class MainInfo{
    public Integer id;
    public String Name;
    public Date DB;
    public String region;
    public String locality;
    public String adress;
    public Long SNILS;
    public Long OMS;
    public Boolean IsDoc;
    public String number;
    public String email;
    public int signForm_id;


}
public class MainActivity extends AppCompatActivity implements TransferFrag{

    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.210:8080")
            .addConverterFactory(GsonConverterFactory.create()).build();
    public String login = "";
    public String password = "";
    public void onData(boolean IsMainActiv,String log,String pass){
        if(IsMainActiv){
        login = log;
        password = pass;
        LogUp();
        new RequestToServer().execute("");
        if(Ok)
        new RequestToMainInfo().execute("");
    }}
   public int signForm_id;
   public boolean Ok = true;
    public class RequestToServer extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings){
            com.example.itmedbook.LogService logService = retrofit.create(com.example.itmedbook.LogService.class);
            Call <List<SignFormat>> call = logService.getUser(login,password);
            try{
                Response<List<SignFormat>> response = call.execute();
                List<SignFormat> log = response.body();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(log.isEmpty()){
                            Log.d("mylogs","No");
                            Toast.makeText(getApplicationContext(),"Неверный логин или пароль",Toast.LENGTH_SHORT).show();
                            Ok = false;
                        }
                        else {
                            Log.d("myLogs", String.valueOf(log.get(0).id));
                            signForm_id = log.get(0).id;
                            Ok = true;
                        } }});
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }
    public class RequestToMainInfo extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings){
            com.example.itmedbook.LogService logService = retrofit.create(com.example.itmedbook.LogService.class);
            Call <List<MainInfo>> call = logService.getAcc(signForm_id);
            try{
                Response<List<MainInfo>> response = call.execute();
                List<MainInfo> acc = response.body();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy",Locale.ENGLISH);
//                        Log.d("myLogs", String.valueOf(sdf.format(acc.get(0).DB)));
                        Intent intent = new Intent(MainActivity.this,StartActivity.class);
                        intent.putExtra("dataOfUser",acc.get(0).Name);
                        intent.putExtra("DBUser",String.valueOf(sdf.format(acc.get(0).DB)));
                        startActivity(intent);

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
        setContentView(R.layout.activity_main);
        LogFragment fragment = new LogFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.loginFormWrap, fragment).commit();
        findViewById(R.id.Textlogin);
        TextView recoverTextView = findViewById(R.id.recoverText);

//        Button btnEnter  = findViewById(R.id.btnEnter);
//
//        btnEnter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Bundle bundle = new Bundle();
////                bundle.putString("edttext", "1");
////                LogFragment frag = new LogFragment();
////                frag.setArguments(bundle);
//                Intent intent = new Intent(MainActivity.this, StartActivity.class);
//                startActivity(intent);
//
//            }
//        });



//        recoverTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }
    public void LogUp(){
        Log.d("myLogs", String.valueOf("Login" + login + "Pass"+ password));
    }

}