package com.imeiinfopy.imeiinfopy.fragmen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imeiinfopy.imeiinfopy.PreguntasActivity;
import com.imeiinfopy.imeiinfopy.R;


import android.content.Intent;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.imeiinfopy.imeiinfopy.Registro_imei;


public class RegistrarFragment extends Fragment {
    private Button signOut;
    Button regis;
    Button perfile;
    Button acticidad;
    Button botoRegistrarImei;
    Button RegistrarImei;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View v = inflater.inflate(R.layout.fragment_registrar, container, false);
//          inflater.inflate(R.layout.fragment_registrar, container, false);
        RegistrarImei=(Button) v.findViewById(R.id.Registrar);

        RegistrarImei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(getActivity(),Registro_imei.class);
//                startActivity(new Intent(getActivity(), Registro_imei.class));
                startActivity(registrar);
            }
        });
        regis=(Button) v.findViewById(R.id.pregunta);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(getActivity(),PreguntasActivity.class);
//                startActivity(new Intent(getActivity(), Registro_imei.class));
                startActivity(registrar);
            }
        });


        return v;



    }




//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

}
