package com.stpprojects.einscriptionslms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.stpprojects.einscriptionslms.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/* Created by Smita Pathak on 29/1/2020 */

public class LandingActivity extends AppCompatActivity {

    @BindView(R.id.tv_login)
    TextView tv_login;

    @BindView(R.id.tv_signup)
    TextView tv_signup;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(LandingActivity.this);

    }

    @OnClick({R.id.tv_login, R.id.tv_signup})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(LandingActivity.this,LoginActivity.class));
                break;
            case R.id.tv_signup:
                startActivity(new Intent(LandingActivity.this,RegisterActivity.class));
                break;
        }
    }

}
