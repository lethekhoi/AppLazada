package com.example.applazada.View.DangNhap_DangKy.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.applazada.Model.DangNhap_DangKy.ModelDangNhap;
import com.example.applazada.R;
import com.example.applazada.View.TrangChu.TrangChuActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


import java.util.Arrays;

public class FragmentDangNhap extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    Button btnDangNhapFacebook, btnDangNhapGoogle;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiCLient;
    public static int SIGN_IN_GOOGLE = 111;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.layout_fragment_dang_nhap, container, false);

        ModelDangNhap modelDangNhap = new ModelDangNhap();
        mGoogleApiCLient = modelDangNhap.LayGoogleApiClient(getContext(), (GoogleApiClient.OnConnectionFailedListener) this);

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        btnDangNhapFacebook = view.findViewById(R.id.btnDangNhapFacebook);
        btnDangNhapGoogle = view.findViewById(R.id.btnDangNhapGoogle);
        btnDangNhapGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGooglePlus = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiCLient);
                startActivityForResult(iGooglePlus, SIGN_IN_GOOGLE);
                showProcessDialog();
            }
        });
        btnDangNhapFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile", "email"));
            }
        });
        return view;
    }


    private void showProcessDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);


        if (requestCode == SIGN_IN_GOOGLE) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                progressDialog.cancel();
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }

        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
