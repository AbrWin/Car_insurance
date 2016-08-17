package com.abrsoftware.carinsurance.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.abrsoftware.carinsurance.R;
import com.abrsoftware.carinsurance.notifications.PushNotificationsActivity;


public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;
    private View mLoginForm;
    private View mLoginProgress;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private TextInputLayout mEmailError;
    private TextInputLayout mPasswordError;
    private Callback mCallback;
    private Button mSingInBtn;

    public LoginFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        //Setear argunemtos en caso de que los haya
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //Extraer argumentos si existen
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        mLoginForm = rootView.findViewById(R.id.login_form);
        mLoginProgress = rootView.findViewById(R.id.login_progress);

        mEmail = (TextInputEditText) rootView.findViewById(R.id.tv_mail);
        mPassword = (TextInputEditText) rootView.findViewById(R.id.tv_password);
        mEmailError = (TextInputLayout) rootView.findViewById(R.id.til_email_error);
        mPasswordError = (TextInputLayout) rootView.findViewById(R.id.til_password_error);
        mSingInBtn = (Button) rootView.findViewById(R.id.b_sign_in);

        mailTextListener(mEmail);

        passwordTextListener(mPassword);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Callback){
            mCallback = (Callback)context;
        }else {
            throw new RuntimeException(context.toString()
                    + " debe implementar Callback");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void mailTextListener(final TextInputEditText mEmail) {
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private static void passwordTextListener(final TextInputEditText mPassword) {
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void attemptLogin() {
    }

    @Override
    public void showprogress(Boolean show) {
        mLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginProgress.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void setMailError(String error) {
        mEmailError.setError(error);
    }

    @Override
    public void setPasswordError(String error) {
        mPasswordError.setError(error);
    }

    @Override
    public void showLoginError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPushNotificaton() {
       startActivity(new Intent(getActivity(), PushNotificationsActivity.class));
        getActivity().finish();
    }

    @Override
    public void showGooglePlayServicesDialog(int errorCode) {
       mCallback.onInvokeGooglePlayServices(errorCode);
    }

    @Override
    public void showGooglePlayServicesError() {
        Toast.makeText(getActivity(),
                "Se requiere Google Play Services para usar la app", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getActivity(),
                "La red no está disponible. Conéctese y vuelva a intentarlo", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
        }else{
            new RuntimeException("El presentador no puede ser nulo");
        }

    }
    interface Callback{
        void onInvokeGooglePlayServices(int codeError);
    }
}
