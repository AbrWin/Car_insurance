package com.abrsoftware.carinsurance.login;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.abrsoftware.carinsurance.R;
import com.abrsoftware.carinsurance.notifications.PushNotificationsActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment implements LoginContract.View {
    private String TAG = LoginFragment.class.getSimpleName();
    private LoginContract.Presenter mPresenter;
    private View mLoginForm;
    private View mLoginProgress;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private TextInputLayout mEmailError;
    private TextInputLayout mPasswordError;
    private Callback mCallback;
    private Button mSingInBtn;
    private FirebaseAuth mFireBase;
    private FirebaseAuth.AuthStateListener mAuthListener;

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

        //Se obtiene la instancia de FireBase
        mFireBase = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, user.getEmail());
                } else {
                    Log.e(TAG, "No user");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mFireBase.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mFireBase.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        setupLoginBackground(rootView);
        setupTitle(rootView);

        mLoginForm = rootView.findViewById(R.id.login_form);
        mLoginProgress = rootView.findViewById(R.id.login_progress);

        mEmail = (TextInputEditText) rootView.findViewById(R.id.tv_mail);
        mPassword = (TextInputEditText) rootView.findViewById(R.id.tv_password);
        mEmailError = (TextInputLayout) rootView.findViewById(R.id.til_email_error);
        mPasswordError = (TextInputLayout) rootView.findViewById(R.id.til_password_error);
        mSingInBtn = (Button) rootView.findViewById(R.id.b_sign_in);

        mButtonListener(mSingInBtn);
        mailTextListener(mEmail);
        passwordTextListener(mPassword);

        return rootView;
    }

    private void setupLoginBackground(final View root) {
        Glide.with(this)
                .load(R.drawable.login_background)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new SimpleTarget<GlideDrawable>() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        final int sdk = android.os.Build.VERSION.SDK_INT;
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            root.setBackgroundDrawable(resource);
                        } else {
                            root.setBackground(resource);
                        }
                    }
                });
    }


    private void setupTitle(View root) {
        ((TextView) root.findViewById(R.id.tv_logo))
                .setTypeface(Typeface.createFromAsset(
                        getActivity().getAssets(), "fonts/fjalla_on.otf"));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        } else {
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
        mPresenter.start();
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
        mPresenter.attemptLogin(mEmail.getText().toString(), mPassword.getText().toString());
    }

    @Override
    public void showprogress(Boolean show) {
        mLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
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
        if (presenter != null) {
            mPresenter = presenter;
        } else {
            new RuntimeException("El presentador no puede ser nulo");
        }

    }

    interface Callback {
        void onInvokeGooglePlayServices(int codeError);
    }

    public void mButtonListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }
}
