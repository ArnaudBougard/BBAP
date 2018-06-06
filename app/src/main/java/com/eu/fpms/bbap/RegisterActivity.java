package com.eu.fpms.bbap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button _bRegister;
    EditText _etUsername, _etPassword, _etEmail, _etAge, _etHeight, _etWeight;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView _tvGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);

        _etUsername = (EditText)findViewById(R.id.etUsername);
        _etPassword = (EditText)findViewById(R.id.etPassword);
        _etEmail = (EditText)findViewById(R.id.etEmail);
        _etAge = (EditText)findViewById(R.id.etAge);
        _etHeight = (EditText)findViewById(R.id.etHeight);
        _etWeight = (EditText)findViewById(R.id.etWeight);
        _bRegister=(Button)findViewById(R.id.bRegister);

        radioGroup=findViewById(R.id.radioGroup);

        _tvGoLogin=(TextView)findViewById(R.id.tvGoLogin);

        _bRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String username=_etUsername.getText().toString();
                String password=_etPassword.getText().toString();
                String email=_etEmail.getText().toString();
                String age=_etAge.getText().toString();
                String height=_etHeight.getText().toString();
                String weight=_etWeight.getText().toString();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String sex=radioButton.getText().toString();

                User user = new User(username, password, email, sex, age, height, weight);

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