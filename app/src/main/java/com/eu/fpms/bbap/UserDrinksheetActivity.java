package com.eu.fpms.bbap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class UserDrinksheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_drinksheet);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);

    }
}