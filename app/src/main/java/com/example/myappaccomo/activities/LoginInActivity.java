package com.example.myappaccomo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myappaccomo.MainActivity;
import com.example.myappaccomo.R;

public class LoginInActivity extends AppCompatActivity {
    TextView textView;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);

        textView = findViewById(R.id.signUpTextView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();

            }
        });

        textView = findViewById(R.id.forgetPasswordTextView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgetPassword();
            }
        });

        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewPage();
            }
        });


    }


    private void openNewPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    private void openForgetPassword() {
        Intent intent = new Intent(this, ForgetPassword.class);
        startActivity(intent);
    }


    public void openSignUpActivity(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

    }


}
