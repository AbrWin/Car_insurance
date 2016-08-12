package com.abrsoftware.carinsurance.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abrsoftware.carinsurance.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LoginFragment loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.login_container);
        if(loginFragment == null){
            loginFragment = LoginFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_container, loginFragment)
                    .commit();
        }
    }
}
