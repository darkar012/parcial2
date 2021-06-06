package com.example.parcial2android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signoutBtn;
    private ListView listV;
    private baseAdapter alv;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        signoutBtn = findViewById(R.id.signoutBtn);
        listV = findViewById(R.id.listPel);

        alv = new baseAdapter();

        listV.setAdapter(alv);



        signoutBtn.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        loadDatabase();
        if (auth.getCurrentUser() == null) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        } else {
            db.getReference().child("parcial2").child("users").child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot data) {
                            user = data.getValue(User.class);
                            Toast.makeText(ProfileActivity.this, user.nombre, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    }
            );
        }

    }

    public void loadDatabase() {
        DatabaseReference ref = db.getReference().child("parcial2").child("votos");
        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot data) {
                        alv.clear();
                        for (DataSnapshot child : data.getChildren()){
                            Pelicula pel = child.getValue(Pelicula.class);
                            alv.addTareas(pel);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.signoutBtn:
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                break;

        }
    }
}