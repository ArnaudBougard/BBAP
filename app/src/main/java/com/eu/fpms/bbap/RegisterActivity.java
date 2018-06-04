package com.eu.fpms.bbap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button _bRegister;
    EditText _etName, _etUsername, _etPassword;
    TextView _tvGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);

        _etName = (EditText)findViewById(R.id.etName);
        _etUsername = (EditText)findViewById(R.id.etUsername);
        _etPassword = (EditText)findViewById(R.id.etPassword);
        _bRegister=(Button)findViewById(R.id.bRegister);
        _tvGoLogin=(TextView)findViewById(R.id.tvGoLogin);

        _bRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name=_etName.getText().toString();
                String username=_etUsername.getText().toString();
                String password=_etPassword.getText().toString();

                User user = new User(name, username, password);

                databaseHelper.insertUser(user);

                Toast.makeText(getApplicationContext(), "Enregistré avec succès !", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }

        });

        _tvGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }

}