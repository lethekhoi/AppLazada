package com.example.applazada.Model.DangNhap;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken LayTokenFacebookHienTai() {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;
    }

    public GoogleApiClient LayGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener) {

        GoogleApiClient mGoogleApiCLient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        mGoogleApiCLient = new GoogleApiClient.Builder(context)
                .enableAutoManage((AppCompatActivity) context, (GoogleApiClient.OnConnectionFailedListener) failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        return mGoogleApiCLient;
    }


    public GoogleSignInResult LayThongTinDangNhapGoogle(GoogleApiClient googleApiClient) {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            return opr.get();
        } else {
            return null;
        }
    }


    public void HuyTokenTracker() {
        accessTokenTracker.stopTracking();
    }
}
