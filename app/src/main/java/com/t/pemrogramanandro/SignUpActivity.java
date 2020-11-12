package com.t.pemrogramanandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText iUsername;
    private EditText iPassword;
    private EditText iConfirmPassword;
    private TextView iLogin;
    private Button iSignUp;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        iUsername = findViewById(R.id.txtInputUsername);
        iPassword = findViewById(R.id.txtInputPassword);
        iConfirmPassword = findViewById(R.id.txtConfirmPassword);
        iLogin = findViewById(R.id.tvLogIn);
        iSignUp = findViewById(R.id.btnSignUp);
        db = new DatabaseHelper(this);

        iLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        iSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = iUsername.getText().toString().trim();
                String pass = iPassword.getText().toString().trim();
                String cPass = iConfirmPassword.getText().toString().trim();

                if(pass.equals(cPass)){
                    long val = db.addUser(uname, pass);
                    if(val > 0){
                        Toast.makeText(SignUpActivity.this, "Anda telah terdaftar!", Toast.LENGTH_SHORT).show();
                        Intent intentMoveToLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intentMoveToLogin);
                    }else{
                        Toast.makeText(SignUpActivity.this, "Password tidak sama!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignUpActivity.this, "Password tidak sama!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}