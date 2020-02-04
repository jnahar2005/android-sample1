package com.stpprojects.einscriptionslms;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.facebook.CallbackManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.stpprojects.einscriptionslms.bean.UserInformationBean;
import com.stpprojects.einscriptionslms.rest.ApiClient;
import com.stpprojects.einscriptionslms.rest.ApiInterface;
import com.stpprojects.einscriptionslms.utils.AppConstants;
import com.stpprojects.einscriptionslms.utils.AppLog;
import com.stpprojects.einscriptionslms.utils.AppUtil;
import com.stpprojects.einscriptionslms.utils.SessionManager;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/* Created by Smita Pathak on 29/1/2020 */

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.emailEdtTxt)
    EditText emailEdtTxt;

    @BindView(R.id.passwordEdtTxt)
    EditText passwordEdtTxt;

    @BindView(R.id.tv_login)
    TextView tv_login;

    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;

    @BindView(R.id.ivGoogleplusLogin)
    ImageView ivGoogleplusLogin;

    @BindView(R.id.ivFacebookLogin)
    ImageView ivFacebookLogin;

    SessionManager sessionManager;
    private CallbackManager callbackManager;

    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(LoginActivity.this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_title.setText(getResources().getString(R.string.login));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        callbackManager = CallbackManager.Factory.create();
    }

    @OnClick({R.id.tv_login, R.id.tvForgotPassword, R.id.ivGoogleplusLogin, R.id.ivFacebookLogin})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                if (emailEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showToast(LoginActivity.this, getResources().getString(R.string.enteremail), true);
                } else if (!AppUtil.isValidEmail(emailEdtTxt.getText().toString())) {
                    AppUtil.showAlertDialogWith1Button(LoginActivity.this, getResources().getString(R.string.entervailidemail), null, "Ok", "", false);
                } else if (passwordEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(LoginActivity.this, getResources().getString(R.string.enterpassword), null, "Ok", "", false);
                } else {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
//                    if (AppUtil.isInternetAvailable(LoginActivity.this)) {
//                        CallLoginApi(emailEdtTxt.getText().toString().trim(), passwordEdtTxt.getText().toString().trim(),"0","","deviceid","","Android","");
//                    } else {
//                        AppUtil.showToast(LoginActivity.this, getResources().getString(R.string.PleaseCheckIntenetconection), true);
//                    }
                }
                break;

            case R.id.tvForgotPassword:
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                break;

            case R.id.ivFacebookLogin:
                break;

            case R.id.ivGoogleplusLogin:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /*-------------------- Login Api calling Method ----------------*/
    public void CallLoginApi(String email, String password, final String loginType, String social_id, String device_id, String device_token, String device_type, String name) {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle(getResources().getString(R.string.pleasewait));
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.userLogin(AppConstants.USER_ACTION, AppConstants.USER_LOGIN_METHOD, email, password, loginType, social_id, device_id, device_token, device_type, name);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                tv_login.setEnabled(true);
                progressDialog.dismiss();
                try {
                    String json = response1.body().toString();
                    JSONObject response = null;
                    response = new JSONObject(json);
                    if (response.getString("response_code").equalsIgnoreCase("101")) {
                        JSONObject dataObject = response.getJSONObject("details");
                        Gson gson = new GsonBuilder().serializeNulls().create();
                        UserInformationBean userInformationBean = gson.fromJson(String.valueOf(dataObject), UserInformationBean.class);
                        sessionManager.setApiToken(response.getString("token"));
                        sessionManager.createSession(userInformationBean.getID(),
                                userInformationBean.getFirstname(),
                                userInformationBean.getLastname(),
                                userInformationBean.getAddress(),
                                userInformationBean.getCountry(),
                                userInformationBean.getCity(),
                                userInformationBean.getPostalcode(),
                                userInformationBean.getMobile(),
                                userInformationBean.getEmail(),
                                userInformationBean.getSource(),
                                userInformationBean.getImageurl());

                        sessionManager.setLoginType(response.getString("loginType"));
                        sessionManager.setSessiontoken(userInformationBean.getApp_token(), userInformationBean.getLast_login_id());

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        AppUtil.showToast(LoginActivity.this, response.getString("message"), true);

                    } else {
                        AppUtil.showToast(LoginActivity.this, response.getString("message"), true);
                    }
                } catch (Exception e) {
                    AppUtil.showToast(LoginActivity.this, getResources().getString(R.string.WhoopsThereWasAnErrorPleaseTryAgainLater), true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                AppUtil.showToast(LoginActivity.this, getResources().getString(R.string.WhoopsThereWasAnErrorPleaseTryAgainLater), true);
            }
        });
    }

    //------------------------------------------------


    /*----------- Method to Generate hash key--------------*/
    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("KeyHash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    //-------------------------------------------------

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
