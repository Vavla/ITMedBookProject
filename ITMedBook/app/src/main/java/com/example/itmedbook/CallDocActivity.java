package com.example.itmedbook;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.os.Bundle;
import android.view.View;

public class CallDocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_doc);
        MainFragment fragment2 = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mainFramecall, fragment2).commit();

    }



}