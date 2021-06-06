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
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameET, cityET, emailET, passwordET, repasswordET;
    private Button signupBtn;
    private TextView loginLink;
    private FirebaseDatabase db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        nameET = findViewById(R.id.nameET);
        cityET = findViewById(R.id.cityET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        repasswordET = findViewById(R.id.repasswordET);
        signupBtn = findViewById(R.id.signupBtn);
        loginLink = findViewById(R.id.loginLink);

        loginLink.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginLink:
                finish();
                break;
            case R.id.signupBtn:
                auth.createUserWithEmailAndPassword(emailET.getText().toString(), passwordET.getText().toString()).addOnSuccessListener(
                        response -> {
                            String uid = auth.getCurrentUser().getUid();
                            User user = new User(uid, nameET.getText().toString(), cityET.getText().toString(), emailET.getText().toString());
                            db.getReference().child("parcial2").child("users").child(user.id).setValue(user).addOnSuccessListener(
                                    dbresponse -> {
                                        Intent i = new Intent(this, ProfileActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                            );
                        }
                ).addOnFailureListener(
                        error -> {
                            Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                );
                break;
        }
    }

}