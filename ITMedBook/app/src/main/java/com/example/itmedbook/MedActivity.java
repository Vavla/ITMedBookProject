package com.example.itmedbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
 class allMedRecords {
    public Integer id;
    public Date dateRecord;
    public String hospital;
    public String typeRecord;
    public String patientName;
    public Date patientDB;
    public String appeal;
    public String diagnosis;
    public String doctor;


}
interface MedRecordsService{
    @FormUrlEncoded
    @POST("user/get-medbook")
    Call<ArrayList<allMedRecords>>  InMedBook(@Field("patientName") String patientName,@Field("patientDB") String patientDB);
}
public class MedActivity extends AppCompatActivity {
     Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.210:8080")
             .addConverterFactory(GsonConverterFactory.create()).build();

    public String name = "";
    public String DB = "";
    public ArrayList<Meddoc> meddocs;
    public MedListAdapter adapter;
    public RecyclerView RecyclerMed;
    public LinearLayoutManager llm;

    public void getMed(){
        name = StartActivity.nameUser;
        DB = StartActivity.DB;
    }
    @SuppressWarnings("deprecation")
     public class RequestToServer extends AsyncTask<String,String,String> {
        String m = "";
        @SuppressWarnings("deprecation")
        @Override
        protected String doInBackground (String...strings){

            com.example.itmedbook.MedRecordsService medRecordsService = retrofit.create(com.example.itmedbook.MedRecordsService.class);
            Call<ArrayList<allMedRecords>> call = medRecordsService.InMedBook(name, DB);
            try {
                Response<ArrayList<allMedRecords>> response = call.execute();
                ArrayList<allMedRecords> log = response.body();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("mylogs",log.get(0).hospital + " " + log.get(0).doctor );
                        for(int i = 0; i< log.size();i++){
                            Log.d("mylogs",log.get(i).hospital + " " + log.get(i).doctor );
                            meddocs.add(new Meddoc(log.get(i).hospital,log.get(i).typeRecord,"Жалобы: "+log.get(i).appeal,"Врач: "+log.get(i).doctor));

                        }

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            return m;


        }
        @Override
        protected void onPostExecute(String m){
            if(m!=null){
            adapter = new MedListAdapter(MedActivity.this, meddocs, stateClickListener);
            RecyclerMed.setAdapter(adapter);
        }}


    }
    @SuppressWarnings("deprecation")
public MedListAdapter.OnStateClickListener stateClickListener;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);
        MainFragment fragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.MedFrame,fragment).commit();
        RecyclerMed  = findViewById(R.id.RecyclerMed);
        meddocs = new ArrayList<>();
        stateClickListener = new MedListAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Meddoc state, int position) {

                Toast.makeText(getApplicationContext(), "Был выбран пункт " + state.getTypeRecord(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        llm = new LinearLayoutManager(this);
        RecyclerMed.setLayoutManager(llm);
        getMed();

        new RequestToServer().execute("");





    }



}

