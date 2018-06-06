package com.eu.fpms.bbap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserAreaActivity extends AppCompatActivity {

    TextView _tvUsername;
    Button _b1, _b2, _b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

//        _b1 = (Button)findViewById(R.id.b1);
//        _b2 = (Button)findViewById(R.id.b2);
//        _b3 = (Button)findViewById(R.id.b3);
//
//        _tvUsername=(TextView)findViewById(R.id.tvUsername);

        Bundle bundle=getIntent().getExtras();

        final String username=bundle.getString("username");

        _tvUsername.setText("Bonjour, " + username + " !");

        _b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                Bundle bundle = new Bundle();
//                bundle.putString("username", username);
//                Intent intent = new Intent(UserAreaActivity.this, UserDrinksheetActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);


            }

        });

        _b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                Intent intent = new Intent(UserAreaActivity.this, UserInfoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);


            }

        });

        _b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                Bundle bundle = new Bundle();
//                bundle.putString("username", username);
//                Intent intent = new Intent(UserAreaActivity.this, UserHistoryActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);


            }

        });



    }

}
