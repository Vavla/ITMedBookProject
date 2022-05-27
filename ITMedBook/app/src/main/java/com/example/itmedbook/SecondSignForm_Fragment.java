package com.example.itmedbook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link SecondSignForm_Fragment#newInstance} factory method to
// * create an instance of this fragment.
// */

interface TransferSignData{
    public void getSignData(String one,String two);
}
public class SecondSignForm_Fragment extends Fragment {
TransferSignData transferSignData;
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public SecondSignForm_Fragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Second_SignForm_Fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static SecondSignForm_Fragment newInstance(String param1, String param2) {
//        SecondSignForm_Fragment fragment = new SecondSignForm_Fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
    @Override
    public void onAttach(Context a) {
       super.onAttach(a);
       transferSignData = (TransferSignData) a;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_sign_form_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText Number = view.findViewById(R.id.Number);
        EditText Email  = view.findViewById(R.id.Email);
        Button btnSaveSign = view.findViewById(R.id.btnSaveSign);

        btnSaveSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferSignData.getSignData(Number.getText().toString(),Email.getText().toString());
            }
        });
    }
}