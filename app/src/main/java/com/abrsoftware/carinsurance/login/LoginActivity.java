package com.abrsoftware.carinsurance.login;

import android.app.Dialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.abrsoftware.carinsurance.R;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements LoginFragment.Callback {

    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupTranslucentStatusBar();

        LoginFragment loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.login_container);
        if(loginFragment == null){
            loginFragment = LoginFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_container, loginFragment)
                    .commit();
        }

        LoginInteractor loginInteractor = new LoginInteractor(
                getApplicationContext(), FirebaseAuth.getInstance());

        new LoginPresenter(loginFragment, loginInteractor);
    }

    @Override
    public void onInvokeGooglePlayServices(int codeError) {
        showPlayServicesErrorDialog(codeError);
    }

    private void showPlayServicesErrorDialog(int codeError){
        Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(LoginActivity.this, codeError,REQUEST_GOOGLE_PLAY_SERVICES );
        dialog.show();
    }

    private void setupTranslucentStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
