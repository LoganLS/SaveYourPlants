package com.example.saveyourplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Manager
        session = new SessionManager(getApplicationContext());

        db=new DatabaseHelper(this);
        mTextUsername=(EditText)findViewById(R.id.username);
        mTextPassword=(EditText)findViewById(R.id.password);
        mButtonLogin=(Button)findViewById(R.id.button_login);
        mTextViewRegister=(TextView)findViewById(R.id.register);

        mTextViewRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String username=mTextUsername.getText().toString().trim();
                String password=mTextPassword.getText().toString().trim();
                Boolean res=db.checkUser(username,password);

                if(res){
                    // Creating user login session
                    session.createLoginSession(username);

                    Toast.makeText(LoginActivity.this,"Connecté !", Toast.LENGTH_SHORT).show();
                    Intent HomeActivityScreen=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(HomeActivityScreen);
                }else{
                    Toast.makeText(LoginActivity.this,"Erreur : Mauvais identifiants.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
