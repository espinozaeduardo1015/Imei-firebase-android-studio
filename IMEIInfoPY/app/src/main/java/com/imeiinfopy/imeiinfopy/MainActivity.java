package com.imeiinfopy.imeiinfopy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.imeiinfopy.imeiinfopy.fragmen.EncontrarFragment;
import com.imeiinfopy.imeiinfopy.fragmen.InicioFragment;
import com.imeiinfopy.imeiinfopy.fragmen.PerfilFragment;
import com.imeiinfopy.imeiinfopy.fragmen.RegistrarFragment;
import com.imeiinfopy.imeiinfopy.fragmen.ReportarFragment;
import com.imeiinfopy.imeiinfopy.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainnActivity";
    private Button btnChangeEmail, btnChangePassword, btnSendResetEmail, btnRemoveUser,
            changeEmail, changePassword, sendEmail, remove, signOut, idconatel;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
//    private SectionsPageAdapter mSectionsPageAdapter;


    private ViewPager mViewPager;


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
        setContentView(R.layout.activity_main);

        //loading the default fragment
        loadFragment(new InicioFragment());

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

//        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
//
//        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        setupViewPager(mViewPager);
//

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);



        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_home_black_24dp);
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_home_black_24dp);

    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_inicio:
                fragment = new InicioFragment();
                break;

            case R.id.navigation_registrar:
                fragment = new RegistrarFragment();
                break;

            case R.id.navigation_reportar:
                fragment = new ReportarFragment();
                break;

            case R.id.navigation_conatel:
                fragment = new EncontrarFragment();
                break;

            case R.id.navigation_perfil:
                fragment = new PerfilFragment();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;


    }
    //    sign out method
    public void signOut() {
        auth.signOut();

    }
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }




}