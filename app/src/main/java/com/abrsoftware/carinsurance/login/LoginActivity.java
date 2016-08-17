package com.abrsoftware.carinsurance.login;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abrsoftware.carinsurance.R;
import com.google.android.gms.common.GoogleApiAvailability;

public class LoginActivity extends AppCompatActivity implements LoginFragment.Callback {

    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1;

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

    @Override
    public void onInvokeGooglePlayServices(int codeError) {
        showPlayServicesErrorDialog(codeError);
    }

    private void showPlayServicesErrorDialog(int codeError){
        Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(LoginActivity.this, codeError,REQUEST_GOOGLE_PLAY_SERVICES );
        dialog.show();
    }
}
