package com.t.pemrogramanandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText iUsername;
    private EditText iPassword;
    private Button iLogin;

    private String username = "admin";
    private String password = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iUsername = findViewById(R.id.txtUsername);
        iPassword = findViewById(R.id.txtPassword);
        iLogin = findViewById(R.id.btnLogin);

        iLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valdiasiLogin(iUsername.getText().toString(), iPassword.getText().toString());
            }
        });
    }

    private void valdiasiLogin(String uname, String pass) {
        if (uname.equals(username) && pass.equals(password)) {
            Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Username atau Password anda tidak benar!", Toast.LENGTH_SHORT).show();
        }
    }
}