package com.stpprojects.einscriptionslms;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.stpprojects.einscriptionslms.interfaces.AlertDialog_OnClickInterface;
import com.stpprojects.einscriptionslms.utils.AppUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/* Created by Smita Pathak on 29/1/2020 */

public class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_SendPassword)
    TextView tv_SendPassword;
    @BindView(R.id.emailEdtTxt)
    TextView emailEdtTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_title.setText("Reset Password");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @OnClick({R.id.tv_SendPassword})
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_SendPassword:
                if (emailEdtTxt.getText().toString().equalsIgnoreCase("")) {
                    AppUtil.showAlertDialogWith1Button(ForgotPasswordActivity.this, getResources().getString(R.string.enteremail), null, "Ok", "", false);
                } else if (!AppUtil.isValidEmail(emailEdtTxt.getText().toString())) {
                    AppUtil.showAlertDialogWith1Button(ForgotPasswordActivity.this, getResources().getString(R.string.entervailidemail), null, "Ok", "", false);
                } else {

                    InputMethodManager inputManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                break;
        }
    }


    //-------Dialog with one positive button----------

    public void showAlertDialog(Context context, String messageToShowOnAlert, final AlertDialog_OnClickInterface
            mAlertDialog_OnClickListener, String buttonText, final String strTAG, boolean isCancellable) {

        if (context == null) {
            return;
        }
        AlertDialog.Builder alertDialog_builder = new AlertDialog.Builder(context);
        alertDialog_builder.setCancelable(isCancellable);
        alertDialog_builder.setMessage(messageToShowOnAlert);

        if (buttonText == null) {
            buttonText = context.getString(android.R.string.ok);
        }
        final String finalButtonText = buttonText;

        alertDialog_builder.setPositiveButton(buttonText, new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mAlertDialog_OnClickListener != null && strTAG != null) {
                            mAlertDialog_OnClickListener.onAlertDialogButtonClicked(
                                    finalButtonText, strTAG);
                        }
                        dialog.dismiss();
                        finish();

                    }
                });
        alertDialog_builder.show();
    }

}
