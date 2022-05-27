package com.example.itmedbook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

class Researches {
    String TitleRes = new String();
    String ResultRes = new String();
    String doctor = new String();
    Researches(String TitleRes,String ResultRes,String doctor){
        this.TitleRes = TitleRes;
        this.ResultRes = ResultRes;
        this.doctor = doctor;

    }
    String getTitleRes(){return this.TitleRes;}
    String getResultRes(){return this.ResultRes;}
    String getDoctor(){return this.doctor;}
}

public class ResearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        MainFragment fragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.ResearchFrame,fragment).commit();

        Spinner spinnerRes = findViewById(R.id.spinnerRes);
        String [] TypeResearches = {"Общеклинические исследования","УЗИ","Ренген","ЭКГ","МРТ","ЭЭГ","Эндоскопия","Маммография","КТ"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, TypeResearches);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRes.setAdapter(adapter);

        RecyclerView ResData = findViewById(R.id.ResData);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        ResData.setLayoutManager(llm);

        ArrayList<Researches> researches = new ArrayList<>();
        researches.add(new Researches("УЗИ","Всё хорошо","А.М."));
        researches.add(new Researches("УЗИ","Всё хорошо","А.М."));

        ResearchAdapter.OnStateClickListener stateClickListener = new ResearchAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Researches research, int position) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
            }
        };
        ResearchAdapter researchesAdapter = new ResearchAdapter(this,researches,stateClickListener);
        ResData.setAdapter(researchesAdapter);

    }
}