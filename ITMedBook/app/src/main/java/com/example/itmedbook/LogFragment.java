package com.example.itmedbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

///**
//// * A simple {@link Fragment} subclass.
//// * Use the {@link LogFragment#newInstance} factory method to
// * create an instance of this fragment.
// */

public class LogFragment extends Fragment {

      TransferFrag transferFrag;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public LogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static BlankFragment newInstance(String param1, String param2) {
//        BlankFragment fragment = new BlankFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
    @Override
    public void onAttach(Context a) {
        super.onAttach(a);
        transferFrag = (TransferFrag) a;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log, container, false);
    }
    EditText Textlogin,TextPassw;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnLogUp = view.findViewById(R.id.btnLogUp);
        Button btnSign = view.findViewById(R.id.btnSign);
        Button btnLogSign = view.findViewById(R.id.btnLogSign);
        btnLogSign.setText(MainActivity.class.getSimpleName().equals(getActivity().getLocalClassName())?"Вход":"Регистрация");
        Textlogin = view.findViewById(R.id.Textlogin);
        TextPassw = view.findViewById(R.id.TextPassw);
        btnLogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MainActivity.class.getSimpleName().equals(getActivity().getLocalClassName())) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnSign.setOnClickListener(view1 -> {
            if(!SignActivity.class.getSimpleName().equals(getActivity().getLocalClassName())) {
                Intent intent = new Intent(getActivity(), SignActivity.class);
                startActivity(intent);
            }});

        btnLogSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.class.getSimpleName().equals(getActivity().getLocalClassName()))
                transferFrag.onData(true,GetLog(),GetPass());
                else transferFrag.onData(false,GetLog(),GetPass());
            }
        });
    }


    public String GetLog(){return Textlogin.getText().toString();}
    public String GetPass(){return TextPassw.getText().toString();}
    }
