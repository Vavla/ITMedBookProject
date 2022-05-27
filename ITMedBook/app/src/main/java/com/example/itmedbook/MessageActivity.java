package com.example.itmedbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

class Messanges{
    String FromTo = new String();
    String time;
    String text = new String();
    Messanges(String FromTo,String time,String text){
        this.FromTo = FromTo;
        this.time   = time;
        this.text   = text;
    }
    String getFromTo(){return this.FromTo;}
    String getText(){return this.text;}
    String getTime(){return this.time;}
}
public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        MainFragment fragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.MessFrame,fragment).commit();

        ArrayList<Messanges> messanges = new ArrayList<>();
        RecyclerView RecyclerMess = findViewById(R.id.RecyclerMess);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerMess.setLayoutManager(layoutManager);
        MessAdapter.OnStateClickListener onStateListener = new MessAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Messanges messange, int position) {
                Toast.makeText(getApplicationContext(), position, Toast.LENGTH_SHORT).show();
            }
        };
        MessAdapter messAdapter = new MessAdapter(this,messanges, onStateListener);
        RecyclerMess.setAdapter(messAdapter);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
        Date date = new Date();
        messanges.add(new Messanges("УЗИ", dateFormat.format(date) ,"А.М."));
        messanges.add(new Messanges("УЗИ",dateFormat.format(date) ,"А.М."));
    }
}