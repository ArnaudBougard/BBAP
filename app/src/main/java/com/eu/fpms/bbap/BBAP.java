package com.eu.fpms.bbap;

import android.app.Application;
import android.os.SystemClock;

public class BBAP extends Application {

    //final DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    public void onCreate() {

        super.onCreate();
        SystemClock.sleep(2000);

       // Drink drink1 = new Drink("Orval","Belgique","6.2","480");
       // Drink drink2 = new Drink("Chimay bleue", "Belgique", "9","810");
        //Drink drink3 = new Drink("Stella Artois", "Belgique", "5.2","460");

        //databaseHelper.insertDrink(drink1);
        //databaseHelper.insertDrink(drink2);
        //databaseHelper.insertDrink(drink3);

    }
}
