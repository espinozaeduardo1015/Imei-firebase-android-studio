package com.imeiinfopy.imeiinfopy.fragmen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.imeiinfopy.imeiinfopy.ActividadActivity;
import com.imeiinfopy.imeiinfopy.Dispositivos;
import com.imeiinfopy.imeiinfopy.LocalizarDispositivo;
import com.imeiinfopy.imeiinfopy.R;
import com.imeiinfopy.imeiinfopy.perfile.EditarPerfil;


public class EncontrarFragment extends Fragment {
    Button localizar;
    Button acticidad;
    Button botoRegistrarImei;
    Button dispositivo;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_encontrar,container,false);

        localizar=(Button) v.findViewById(R.id.localizar);

        localizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(getActivity(),LocalizarDispositivo.class);
//                startActivity(new Intent(getActivity(), Registro_imei.class));
                startActivity(registrar);
            }
        });

        dispositivo=(Button) v.findViewById(R.id.dispositivo);

        dispositivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(getActivity(),Dispositivos.class);
//                startActivity(new Intent(getActivity(), Registro_imei.class));
                startActivity(registrar);
            }
        });

        acticidad=(Button) v.findViewById(R.id.actividad);

        acticidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(getActivity(),ActividadActivity.class);
//                startActivity(new Intent(getActivity(), Registro_imei.class));
                startActivity(registrar);
            }
        });


        return v;
        // Inflate the layout for this fragment

    }


}