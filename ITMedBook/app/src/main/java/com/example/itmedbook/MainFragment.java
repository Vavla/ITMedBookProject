package com.example.itmedbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MainFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MainFragment extends Fragment{
    public static String nameForLabels = "";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MainFragment.
//     */
    // TODO: Rename and change types and number of parameters
//    public static MainFragment newInstance(String param1, String param2) {
//        MainFragment fragment = new MainFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, null);
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            nameForLabels = bundle.getString("nameUser");
        }

        TextView textViewName = view.findViewById(R.id.textViewName);
        textViewName.setText(surName());

        ImageView iconView = view.findViewById(R.id.menuBtn);
        iconView.setImageResource(R.drawable.menu_icon);

        ConstraintLayout layout = view.findViewById(R.id.menuUnvisib);
        FrameLayout frameLayout = view.findViewById(R.id.FrameMain);
        ViewGroup.LayoutParams params = frameLayout.getLayoutParams();
        iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int isVisible = layout.getVisibility();

                Log.d("toggle", String.valueOf(isVisible));

                Transition transition = new Slide(Gravity.LEFT);
                transition.setDuration(200);
                transition.addTarget(R.id.menuUnvisib);

                TransitionManager.beginDelayedTransition(view.findViewById(R.id.rootMenuContainer),transition);

                layout.setVisibility(isVisible == View.VISIBLE? View.GONE:View.VISIBLE);
                ViewGroup.LayoutParams params = frameLayout.getLayoutParams();
                params.height = (isVisible == View.VISIBLE)? 0:ViewGroup.LayoutParams.MATCH_PARENT;
                frameLayout.setLayoutParams(params);


            }
        });

        Button btnExit = view.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(MainActivity.class.getSimpleName().equals(getActivity().getLocalClassName()))){
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }}
        });
        Button btnToStartActiv = view.findViewById(R.id.btnToStartActiv);
        btnToStartActiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),StartActivity.class);
                    startActivity(intent);
                }
        });
        Button btnToMessage = view.findViewById(R.id.btnToMessage);
        btnToMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(MessageActivity.class.getSimpleName().equals(getActivity().getLocalClassName()))){
                Intent intent = new Intent(getActivity(),MessageActivity.class);
                startActivity(intent);
            }   layout.setVisibility(View.GONE);
            }

        });
    }
    public String surName(){
        String[] parts = nameForLabels.split(" "); String res = parts[0] ;
        return res;
    }
}