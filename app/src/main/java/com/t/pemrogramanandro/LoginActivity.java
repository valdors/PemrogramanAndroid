package com.t.pemrogramanandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText iUsername;
    private EditText iPassword;
    private Button iLogin;
    private TextView iDaftar;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iUsername = findViewById(R.id.txtUsername);
        iPassword = findViewById(R.id.txtPassword);
        iLogin = findViewById(R.id.btnLogin);
        iDaftar = findViewById(R.id.tvDaftar);
        db = new DatabaseHelper(this);

        iDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        iLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = iUsername.getText().toString().trim();
                String pass = iPassword.getText().toString().trim();
                Boolean result = db.checkUser(uname, pass);
                if(result == true){
                    Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                    Intent intentMoveToHome = new Intent(LoginActivity.this, FirebaseActivity.class);
                    startActivity(intentMoveToHome);
                }else{
                    Toast.makeText(LoginActivity.this, "Username atau Password anda tidak benar!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}