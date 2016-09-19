package com.abrsoftware.carinsurance.login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by AbrWin on 11/08/16.
 */
public class LoginInteractor {

    private final Context mContext;
    private FirebaseAuth mFirebaseAuth;

    public LoginInteractor(Context context, FirebaseAuth firebaseAuth) {
        mContext = context;
        if (firebaseAuth != null) {
            mFirebaseAuth = firebaseAuth;
        } else {
            throw new RuntimeException("La instancia de FirebaseAuth no puede ser null");
        }
    }

    public void login(String mail, String password, final Callback callback) {
        boolean c1 = isValidateEmail(mail, callback);
        boolean c2 = isValidPassword(password, callback);

        //Check validate input text
        if (!(c1 && c2)) {
            return;
        }

        //Check isNetworkAvailable
        if(!isNetworkAvailable()){
            callback.onNetworkConnectFailed();
        }

        //Check GooglePlayServices
        if(!isGooglePlayServicesAvailable(callback)){
            return;
        }

        // Consultar Firebase Authentication
        signInUser(mail, password, callback);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private boolean isGooglePlayServicesAvailable(Callback callback){
        int statuscode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(mContext);
        if(GoogleApiAvailability.getInstance().isUserResolvableError(statuscode)){
            callback.onBeUserResolvableError(statuscode);
            return false;
        }else if(statuscode != ConnectionResult.SUCCESS){
            callback.onGooglePlayServicesFailed();
            return false;
        }
        return true;
    }

    private boolean isValidateEmail(String email, Callback callback) {
        boolean isValid = true;
        if (TextUtils.isEmpty(email)) {
            callback.onEmailError("Escribe tu correo");
            isValid = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            callback.onEmailError("Correo no válido");
            isValid = false;
        }
        // Más reglas de negocio...
        return isValid;
    }

    private boolean isValidPassword(String password, Callback callback) {
        boolean isValid = true;
        if (TextUtils.isEmpty(password)) {
            callback.onPasswordError("Escribe tu contraseña");
            isValid = false;
        }

        // Más reglas de negocio...
        return isValid;
    }

    private void signInUser(String mail, String password, final Callback callback){
        mFirebaseAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    callback.onAuthFailed(task.getException().getMessage());
                }else{
                    callback.onAuthSuccess();
                }
            }
        });
    }

    interface Callback {

        void onEmailError(String msg);

        void onPasswordError(String msg);

        void onNetworkConnectFailed();

        void onBeUserResolvableError(int errorCode);

        void onGooglePlayServicesFailed();

        void onAuthFailed(String msg);

        void onAuthSuccess();
    }
}
