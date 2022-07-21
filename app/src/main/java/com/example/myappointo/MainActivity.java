package com.example.myappointo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText enterOtp, mobileEdit;
    TextView mobileTextview;
    Button loginBtn, resetLogin, otpLoginButton;

    public void gotoHomepage(View view){
        Intent intent = new Intent(MainActivity.this, Homepage.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showOtpInputEditText(View view) {
        loginBtn = (Button) findViewById(R.id.id_loginButton);
        enterOtp = (EditText) findViewById(R.id.id_otpEditText);
        mobileEdit = (EditText) findViewById(R.id.id_loginPhone);
        resetLogin = (Button) findViewById(R.id.id_resetLogin);
        mobileTextview = (TextView) findViewById(R.id.id_mobileTextView);
        otpLoginButton = (Button) findViewById(R.id.id_OTPloginButton);

        if(mobileEdit.getText().toString().trim().isEmpty() || mobileEdit.getText().toString().trim().length() != 10){
            Toast.makeText(MainActivity.this, Integer.toString(mobileEdit.getText().toString().trim().length()), Toast.LENGTH_SHORT).show();
        }
        else{
            enterOtp.setVisibility(View.VISIBLE);   //set OTP edit text visible
            resetLogin.setVisibility(View.VISIBLE); //set reset login button visible
            mobileEdit.setVisibility(View.GONE);    //set mobile input view invisible
            mobileTextview.setText("+91" + mobileEdit.getText().toString());
            mobileTextview.setVisibility(View.VISIBLE); //set mobile textview visible
            loginBtn.setVisibility(View.GONE);
            otpLoginButton.setVisibility(View.VISIBLE);
        }
    }

    public void resetLogin(View view) {
        resetLogin = (Button) findViewById(R.id.id_resetLogin);
        enterOtp = (EditText) findViewById(R.id.id_otpEditText);
        loginBtn = (Button) findViewById(R.id.id_loginButton);
        mobileTextview = (TextView) findViewById(R.id.id_mobileTextView);
        mobileEdit = (EditText) findViewById(R.id.id_loginPhone);
        otpLoginButton = (Button) findViewById(R.id.id_OTPloginButton);

        mobileEdit.setVisibility(View.VISIBLE);
        mobileTextview.setVisibility(View.GONE);
        resetLogin.setVisibility(View.GONE);
        enterOtp.setVisibility(View.GONE);
        loginBtn.setVisibility(View.VISIBLE);
        otpLoginButton.setVisibility(View.GONE);
        enterOtp.setText("");
    }

}