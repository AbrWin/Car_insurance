package com.abrsoftware.carinsurance.login;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abrsoftware.carinsurance.R;


public class LoginFragment extends Fragment {

    private View mLoginForm;
    private View mLoginProgress;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private TextInputLayout mEmailError;
    private TextInputLayout mPasswordError;
    private Button mSingInBtn;

    public LoginFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
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

        mEmail = (TextInputEditText)rootView.findViewById(R.id.tv_mail);
        mPassword = (TextInputEditText)rootView.findViewById(R.id.tv_password);
        mEmailError = (TextInputLayout)rootView.findViewById(R.id.til_email_error);
        mPasswordError = (TextInputLayout)rootView.findViewById(R.id.til_password_error);
        mSingInBtn = (Button)rootView.findViewById(R.id.b_sign_in);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
