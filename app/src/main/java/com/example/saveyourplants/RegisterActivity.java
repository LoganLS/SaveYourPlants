package com.example.saveyourplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText getTextConfirmPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db=new DatabaseHelper(this);
        mTextUsername=(EditText)findViewById(R.id.username);
        mTextPassword=(EditText)findViewById(R.id.password);
        getTextConfirmPassword=(EditText)findViewById(R.id.confirm_password);
        mButtonRegister=(Button)findViewById(R.id.button_register);
        mTextViewLogin=(TextView)findViewById(R.id.login);

        mTextViewLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent loginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               String username=mTextUsername.getText().toString().trim();
               String password=mTextPassword.getText().toString().trim();
               String confirm_password=getTextConfirmPassword.getText().toString().trim();

               if (username.matches("") || password.matches("")) {
                   Toast.makeText(RegisterActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
               }else {

                   if (password.equals(confirm_password)) {
                       long val = db.addUser(username, password);
                       if (val > 0) {
                           Toast.makeText(RegisterActivity.this, "Compte créé avec succès!", Toast.LENGTH_SHORT).show();
                           Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                           startActivity(moveToLogin);
                       } else {
                           Toast.makeText(RegisterActivity.this, "Une erreur est survenue", Toast.LENGTH_SHORT).show();
                       }
                   } else {
                       Toast.makeText(RegisterActivity.this, "Les mots de passe ne sont pas les mêmes", Toast.LENGTH_SHORT).show();
                   }
               }
           }
        });
    }
}
