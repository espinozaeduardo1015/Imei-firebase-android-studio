package com.imeiinfopy.imeiinfopy;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registro_imei extends AppCompatActivity {
    TelephonyManager manager;
    TextView txtmensaje, input_imei;

    TextInputEditText operadora, modelo, marca, bloqueado;

    Button botoRegistrarImei;
    DatabaseReference databaseReference;

    ///DatabaseReference nDataReference= FirebaseDatabase.getInstance().getReference();
    //DatabaseReference nRootChild=nDataReference.child("texto");
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ///DatabaseReference myRef = database.getReference("message");




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.READ_PHONE_STATE );
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso.");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_PHONE_STATE }, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso!");
        }
        setContentView(R.layout.activity_registro_imei);
        txtmensaje = (TextView) findViewById(R.id.txtmensaje);
        input_imei=(TextView) findViewById(R.id.input_imei);
        operadora=(TextInputEditText) findViewById(R.id.operadora);
        marca=(TextInputEditText)findViewById(R.id.marca);
        modelo=(TextInputEditText)findViewById(R.id.modelo);

        manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        databaseReference=FirebaseDatabase.getInstance().getReference();
        botoRegistrarImei=(Button) findViewById(R.id.botoRegistrarImei);
        botoRegistrarImei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String imieRegistro=input_imei.getText().toString();
                String operadoraRegistro=operadora.getText().toString();
                String modeloRegistro=modelo.getText().toString();
                String marcaRegistro=marca.getText().toString();
                String emailRegistro=user.getEmail();

                // String id=databaseReference.push().getKey();
                //String id=user.getEmail();
//                a user=new a(imieRegistro);
                //databaseReference.child("usuario").child("fdg").setValue();
                //DatabaseReference myRef = database.getReference(marcaRegistro);
                //myRef.setValue("Hello, World!");


                //databaseReference.child("users").child(marcaRegistro).setValue(modeloRegistro, operadoraRegistro);

                //------------------------------
                //databaseReference.child("imei").child(imieRegistro).setValue(operadoraRegistro);
                //databaseReference.child("imei").child(imieRegistro).setValue(modeloRegistro);
                //-----------------------



                DatabaseReference dbRef =
                        FirebaseDatabase.getInstance().getReference()
                                .child("imei");

                Map<String, String> domingo = new HashMap<>();
                domingo.put("imei", imieRegistro);
                domingo.put("email", emailRegistro);
                domingo.put("operadora", operadoraRegistro);
                domingo.put("modelo", modeloRegistro);
                domingo.put("marca", marcaRegistro);
                domingo.put("bloqueado", ("0"));


                dbRef.child(imieRegistro).setValue(domingo);





                Toast.makeText(getApplicationContext(),
                        "Imei registrado"+emailRegistro,
                        Toast.LENGTH_SHORT).show();

            }
        });

        StringBuilder builder = new StringBuilder();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String modeloDispositivo = android.os.Build.MODEL;
        String marcaDispositivo = android.os.Build.MANUFACTURER;
        builder.append("Imei: ").append(manager.getDeviceId()).append("\n");
        builder.append("Nombre operador").append(manager.getNetworkOperatorName()).append("\n");





        ///myRef.setValue("Hello, World!");

        input_imei.setText(manager.getDeviceId());
        operadora.setText(manager.getNetworkOperatorName());
        modelo.setText(modeloDispositivo);
        marca.setText(marcaDispositivo.toUpperCase());

//        bloqueado.setText("0");
        //marca.setText(marcaDispositivo);

        modelo.setFocusable(false);
        modelo.setEnabled(false);

        marca.setFocusable(false);
        marca.setEnabled(false);

        operadora.setFocusable(false);
        operadora.setEnabled(false);

        input_imei.setFocusable(false);
        input_imei.setEnabled(false);



//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

}

