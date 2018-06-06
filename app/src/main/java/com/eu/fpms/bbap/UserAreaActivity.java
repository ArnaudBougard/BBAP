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

    TextView _tv1, _tv2;
    Button _b1, _b2, _b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        _b1 = (Button)findViewById(R.id.b1);
        _b2 = (Button)findViewById(R.id.b2);
        _b3 = (Button)findViewById(R.id.b3);

        _tv1=(TextView)findViewById(R.id.tv1);
        _tv2=(TextView)findViewById(R.id.tv2);

        Bundle bundle=getIntent().getExtras();

        final String username=bundle.getString("username");

        _tv1.setText("Bonjour, " + username + " !");

        _tv2.setText("Que souhaitez-vous faire ?");

        _b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                Intent intent = new Intent(UserAreaActivity.this, UserDrinksheetActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);


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

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                Intent intent = new Intent(UserAreaActivity.this, UserHistoryActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);


            }

        });



    }

}
