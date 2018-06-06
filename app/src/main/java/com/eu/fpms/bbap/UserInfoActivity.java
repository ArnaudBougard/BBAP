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

public class UserInfoActivity extends AppCompatActivity {

    TextView _tvUsername;
    EditText _etPassword, _etEmail, _etAge, _etHeight, _etWeight;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button _bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);

        _etPassword = (EditText)findViewById(R.id.etPassword);
        _etEmail = (EditText)findViewById(R.id.etEmail);
        _etAge = (EditText)findViewById(R.id.etAge);
        _etHeight = (EditText)findViewById(R.id.etHeight);
        _etWeight = (EditText)findViewById(R.id.etWeight);

        _bSave = (Button)findViewById(R.id.bSave);

        radioGroup=findViewById(R.id.radioGroup);

        _tvUsername=(TextView)findViewById(R.id.tvUsername);

        Bundle bundle=getIntent().getExtras();

        final String username=bundle.getString("username");

        _tvUsername.setText("Bonjour, " + username + " !");

        _bSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String password=_etPassword.getText().toString();
                String email=_etEmail.getText().toString();
                String age=_etAge.getText().toString();
                String height=_etHeight.getText().toString();
                String weight=_etWeight.getText().toString();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String sex=radioButton.getText().toString();

                databaseHelper.updateUserInformations(username,password,email,sex,age,height,weight);

                Toast.makeText(getApplicationContext(), "Enregistré avec succès !", Toast.LENGTH_LONG).show();

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                Intent intent = new Intent(UserInfoActivity.this, UserAreaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }

        });



    }

}
