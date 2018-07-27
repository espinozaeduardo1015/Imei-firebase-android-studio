package com.imeiinfopy.imeiinfopy.fragmen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.imeiinfopy.imeiinfopy.ActividadActivity;
import com.imeiinfopy.imeiinfopy.Dispositivos;
import com.imeiinfopy.imeiinfopy.R;
import com.imeiinfopy.imeiinfopy.login.LoginActivity;
import com.imeiinfopy.imeiinfopy.perfile.EditarPerfil;
import com.imeiinfopy.imeiinfopy.perfile.SignOut;


public class PerfilFragment extends Fragment {
    private Button signOut;
    Button logout;
    Button perfile;
    Button acticidad;
    Button botoRegistrarImei;
    Button dispositivo;



    private static final String TAG = "AccountFragment";

    //firebase
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Button mSignOut;



    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
//          inflater.inflate(R.layout.fragment_registrar, container, false);
        mSignOut = (Button) v.findViewById(R.id.sign_out);

        setupFirebaseListener();

        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: attempting to sign out the user.");
                FirebaseAuth.getInstance().signOut();
            }
        });



//        logout=(Button) v.findViewById(R.id.botoRegistrar);
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent registrar = new Intent(getActivity(),SignOut.class);
////                startActivity(new Intent(getActivity(), Registro_imei.class));
//                startActivity(registrar);
//            }
//        });


        perfile=(Button) v.findViewById(R.id.editar);

        perfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(getActivity(),EditarPerfil.class);
//                startActivity(new Intent(getActivity(), Registro_imei.class));
                startActivity(registrar);
            }
        });

        return v;

    }
    private void setupFirebaseListener(){
        Log.d(TAG, "setupFirebaseListener: setting up the auth state listener.");
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG, "onAuthStateChanged: registrado: " + user.getUid());
                }else{
                    Log.d(TAG, "onAuthStateChanged: Sesión Finalizada");
                    Toast.makeText(getActivity(), "Sesión Finalizada", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthStateListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
    }
}
