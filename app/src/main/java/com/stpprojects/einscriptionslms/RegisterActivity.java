package com.stpprojects.einscriptionslms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* Created by Smita Pathak on 29/1/2020 */

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.emailEdtTxt)
    EditText emailEdtTxt;

    @BindView(R.id.passwordEdtTxt)
    EditText passwordEdtTxt;

    @BindView(R.id.cfrmPasswordEdtTxt)
    EditText cfrmPasswordEdtTxt;

    @BindView(R.id.firstNameEdtTxt)
    EditText firstNameEdtTxt;

    @BindView(R.id.lastNameEdtTxt)
    EditText lastNameEdtTxt;

    @BindView(R.id.mobileNumEdtTxt)
    EditText mobileNumEdtTxt;

    @BindView(R.id.tv_register)
    TextView tv_register;

    @BindView(R.id.addressEdtTxt)
    TextView addressEdtTxt;

    @BindView(R.id.cityEdtTxt)
    TextView cityEdtTxt;

    @BindView(R.id.conuntryEdtTxt)
    TextView conuntryEdtTxt;

    @BindView(R.id.postalcodeEdtTxt)
    TextView postalcodeEdtTxt;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(RegisterActivity.this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_title.setText(getResources().getString(R.string.registration));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.tv_register})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                if (emailEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.enteremail), null, "Ok", "", false);
                } else if (!AppUtil.isValidEmail(emailEdtTxt.getText().toString())) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.entervailidemail), null, "Ok", "", false);
                } else if (passwordEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.enterpassword), null, "Ok", "", false);
                } else if (cfrmPasswordEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.enterconfirmpassword), null, "Ok", "", false);
                } else if (!passwordEdtTxt.getText().toString().contentEquals(cfrmPasswordEdtTxt.getText().toString())) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.passwordnotmatch), null, "Ok", "", false);
                } else if (firstNameEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.enterfirstname), null, "Ok", "", false);
                } else if (firstNameEdtTxt.getText().toString().length() < 3) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.FirstNameshouldbeminimulatters), null, "Ok", "", false);
                } else if (lastNameEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.enterlastname), null, "Ok", "", false);
                } else if (mobileNumEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.entermono), null, "Ok", "", false);
                } else if (addressEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.enteraddress), null, "Ok", "", false);
                } else if (cityEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.entercity), null, "Ok", "", false);
                } else if (conuntryEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.entercountry), null, "Ok", "", false);
                } else if (postalcodeEdtTxt.getText().toString().trim().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(RegisterActivity.this, getResources().getString(R.string.enterpostalcode), null, "Ok", "", false);
                } else {
                    if (AppUtil.isInternetAvailable(RegisterActivity.this)) {
                        callRegistrationApi(firstNameEdtTxt.getText().toString().trim(),
                                lastNameEdtTxt.getText().toString().trim(),
                                emailEdtTxt.getText().toString().trim(),
                                passwordEdtTxt.getText().toString().trim(),
                                cfrmPasswordEdtTxt.getText().toString().trim(),
                                cityEdtTxt.getText().toString().trim(),
                                conuntryEdtTxt.getText().toString().trim(),
                                passwordEdtTxt.getText().toString().trim(),
                                "",
                                addressEdtTxt.getText().toString().trim(),
                                mobileNumEdtTxt.getText().toString().trim());
                    } else {
                        AppUtil.showToast(RegisterActivity.this, getResources().getString(R.string.PleaseCheckIntenetconection), true);
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /*-------------------- Registration Api calling Method ----------------*/
    private void callRegistrationApi(String firstname, String lastname,
                                     String email, String password,
                                     String password_2, String city,
                                     String country, String postalcode, String source,
                                     String address, String mobile) {
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle(getResources().getString(R.string.pleasewait));
        progressDialog.setCancelable(false);
        progressDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<JsonObject> call = apiService.userRegitration(AppConstants.USER_ACTION,
                AppConstants.REGISTER_USER_ACTION,
                firstname,
                lastname,
                email,
                password,
                password_2,
                city,
                country,
                postalcode,
                source,
                address,
                mobile);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                progressDialog.dismiss();
                try {
                    if (response1.isSuccessful()) {
                        try {
                            String json = response1.body().toString();
                            JSONObject response = null;
                            response = new JSONObject(json);
                            if (response.getString("response_code").equalsIgnoreCase("101")) {
                                JSONObject jsonObject = response.getJSONObject("details");
                                Gson gson = new GsonBuilder().serializeNulls().create();
                                UserInformationBean userInformationBean = gson.fromJson(String.valueOf(jsonObject), UserInformationBean.class);
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
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                AppUtil.showToast(RegisterActivity.this, response.getString("message"), true);
                            } else if (response.getString("response_code").equalsIgnoreCase("102")) {
                                AppUtil.showToast(RegisterActivity.this, response.getString("message"), true);
                            } else {
                                AppUtil.showToast(RegisterActivity.this, response.getString("message"), true);
                            }
                        } catch (Exception e) {
                            AppUtil.showToast(RegisterActivity.this, getResources().getString(R.string.RuntimeException), true);
                            e.printStackTrace();
                        }
                    } else {
                        AppLog.v("userregiter", " not found");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    AppUtil.showToast(RegisterActivity.this, getResources().getString(R.string.ServerError), true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                AppUtil.showToast(RegisterActivity.this, getResources().getString(R.string.ApiFailureError), true);
            }
        });
    }

    /* ---------------------------------------------------------- */
}
