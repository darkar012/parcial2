package com.example.parcial2android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private EditText usernameET;
    private EditText passwordET;
    private TextView singupLink;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        loginBtn = findViewById(R.id.loginBtn);
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        singupLink = findViewById(R.id.signupLink);

        loginBtn.setOnClickListener(this);
        singupLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
auth.signInWithEmailAndPassword(usernameET.getText().toString(), passwordET.getText().toString()).addOnSuccessListener(
        command -> {
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);
        }
).addOnFailureListener(
        error ->{
            Toast.makeText(this,error.getMessage(),Toast.LENGTH_LONG).show();
        }
);
                break;

            case R.id.signupLink:
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                break;
        }
    }
}