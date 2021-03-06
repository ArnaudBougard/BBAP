package com.eu.fpms.bbap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Arnaud on 07-05-18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "bbap.db";
    //private static final String DB_NAME = "bob.db";
    private static final int VERSION = 3;



    // Création de la table USER



    public static final String TABLE_NAME="user";

    public static final String COL_ID="ID";
    private static final int NUM_COL_ID = 0;

    public static final String COL_USERNAME="Username";
    private static final int NUM_COL_USERNAME = 1;

    public static final String COL_PASSWORD="Password";
    private static final int NUM_COL_PASSWORD = 2;

    public static final String COL_EMAIL="Email";
    private static final int NUM_COL_EMAIL = 3;

    public static final String COL_SEX="Sex";
    private static final int NUM_COL_SEX = 4;

    public static final String COL_AGE="Age";
    private static final int NUM_COL_AGE = 5;

    public static final String COL_HEIGHT="Height";
    private static final int NUM_COL_HEIGHT = 6;

    public static final String COL_WEIGHT="Weight";
    private static final int NUM_COL_WEIGHT = 7;

    private static final String CREATE_USER_TABLE = " CREATE TABLE " + TABLE_NAME + " ("
                                            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                            + COL_USERNAME + " TEXT NOT NULL, "
                                            + COL_PASSWORD + " TEXT NOT NULL, "
                                            + COL_EMAIL + " TEXT NOT NULL, "
                                            + COL_SEX + " TEXT NOT NULL, "
                                            + COL_AGE + " TEXT NOT NULL, "
                                            + COL_HEIGHT + " TEXT NOT NULL, "
                                            + COL_WEIGHT + " TEXT NOT NULL );";



    // Création de la table DRINKSHEET



    public static final String TABLE_2_NAME="drinksheet";

    public static final String COL_SHEET_ID="Sheet_ID";
    private static final int NUM_COL_SHEET_ID = 0;

    public static final String COL_HOUR="Hour";
    private static final int NUM_COL_HOUR = 1;

    public static final String COL_DATE="Date";
    private static final int NUM_COL_DATE = 2;

    public static final String COL_DRINK_LIST="Drink_List";
    private static final int NUM_COL_DRINK_LIST = 3;

    public static final String COL_AMOUNT_LIST="Amount_List";
    private static final int NUM_COL_AMOUNT_LIST = 4;

    private static final String CREATE_DRINKSHEET_TABLE = " CREATE TABLE " + TABLE_2_NAME + " ("
                                            + COL_SHEET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                            + COL_HOUR + " TEXT NOT NULL, "
                                            + COL_DATE + " TEXT NOT NULL, "
                                            + COL_DRINK_LIST + " TEXT, "
                                            + COL_AMOUNT_LIST + " TEXT );";



    // Création de la table DRINK



    public static final String TABLE_3_NAME="drink";

    public static final String COL_DRINK_ID="Drink_ID";
    private static final int NUM_COL_DRINK_ID = 0;

    public static final String COL_NAME="Name";
    private static final int NUM_COL_NAME = 1;

    public static final String COL_COUNTRY="Country";
    private static final int NUM_COL_COUNTRY = 2;

    public static final String COL_VOL="Vol";
    private static final int NUM_COL_VOL = 3;

    public static final String COL_KCAL="Kcal";
    private static final int NUM_COL_KCAL = 4;

    private static final String CREATE_DRINK_TABLE = " CREATE TABLE " + TABLE_3_NAME + " ("
                                            + COL_DRINK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                            + COL_NAME + " TEXT NOT NULL, "
                                            + COL_COUNTRY + " TEXT NOT NULL, "
                                            + COL_VOL + " TEXT NOT NULL, "
                                            + COL_KCAL + " TEXT NOT NULL );";




    public DatabaseHelper(Context context) {

        //super(context, TABLE_NAME, null, VERSION);
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_DRINKSHEET_TABLE);
        db.execSQL(CREATE_DRINK_TABLE);
        Log.i("Database","onCreates invoqués");

    }

    @Override
    public void onUpgrade(SQLiteDatabase bdd, int oldVersion, int newVersion) {

        bdd.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        bdd.execSQL(" DROP TABLE IF EXISTS " + TABLE_2_NAME);
        bdd.execSQL(" DROP TABLE IF EXISTS " + TABLE_3_NAME);
        onCreate(bdd);

    }

    public Cursor LoginCheck (String username, String password){

        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_USERNAME + " =? AND " + DatabaseHelper.COL_PASSWORD + " =? ", new String[]{username, password});

        return cursor;
    }

    public long insertUser(User user) {

        ContentValues content = new ContentValues();
        content.put(COL_USERNAME, user.getUsername());
        content.put(COL_PASSWORD, user.getPassword());
        content.put(COL_EMAIL, user.getEmail());
        content.put(COL_SEX, user.getSex());
        content.put(COL_AGE, user.getAge());
        content.put(COL_HEIGHT, user.getHeight());
        content.put(COL_WEIGHT, user.getWeight());

        return this.getWritableDatabase().insert(TABLE_NAME, null, content);

    }

    public long insertDrinksheet(Drinksheet drinksheet) {

        ContentValues content = new ContentValues();
        content.put(COL_HOUR, drinksheet.getHour());
        content.put(COL_DATE, drinksheet.getDate());

        return this.getWritableDatabase().insert(TABLE_2_NAME, null, content);

    }

    public long insertDrink(Drink drink) {

        ContentValues content = new ContentValues();
        content.put(COL_NAME, drink.getName());
        content.put(COL_COUNTRY, drink.getCountry());
        content.put(COL_VOL, drink.getVol());
        content.put(COL_KCAL, drink.getKcal());

        return this.getWritableDatabase().insert(TABLE_3_NAME, null, content);

    }

    public boolean updateUserInformations(String username, String password, String email, String sex, String age, String height, String weight) {

            ContentValues content = new ContentValues();
            content.put(COL_PASSWORD, password);
            content.put(COL_EMAIL, email);
            content.put(COL_SEX, sex);
            content.put(COL_AGE, age);
            content.put(COL_HEIGHT, height);
            content.put(COL_WEIGHT, weight);

           this.getWritableDatabase().update(TABLE_NAME, content, COL_USERNAME + " =? ", new String[]{username});

           return true;
    }

    public boolean updateDrinksheetContent(int sheet_id, String drink_list, String amount_list){

        ContentValues content = new ContentValues();
        content.put(COL_DRINK_LIST, drink_list);
        content.put(COL_AMOUNT_LIST, amount_list);

        this.getWritableDatabase().update(TABLE_2_NAME, content, COL_SHEET_ID + " =? " + sheet_id, null);

        return true;

    }

    public User fetchUserInfo( String username ) {

        Cursor c = this.getReadableDatabase().query(TABLE_NAME, new String[] { COL_ID, COL_USERNAME, COL_PASSWORD, COL_EMAIL, COL_SEX,
                COL_AGE, COL_HEIGHT, COL_WEIGHT }, COL_USERNAME + " =? ", new String[]{username}, null, null, null, null);

        User currentUser = new User();

        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        else{

            c.moveToFirst();

            if(c.getCount() >= 1){
                do{

                    currentUser.setId(c.getInt(NUM_COL_ID));
                    currentUser.setUsername(c.getString(NUM_COL_USERNAME));
                    currentUser.setPassword(c.getString(NUM_COL_PASSWORD));
                    currentUser.setEmail(c.getString(NUM_COL_EMAIL));
                    currentUser.setSex(c.getString(NUM_COL_SEX));
                    currentUser.setAge(c.getInt(NUM_COL_AGE));
                    currentUser.setHeight(c.getInt(NUM_COL_HEIGHT));
                    currentUser.setWeight(c.getInt(NUM_COL_WEIGHT));

                }while(c.moveToNext());

            }
        }

        c.close();
        return currentUser;
    }

    public Drinksheet fetchDrinksheetInfo(int sheet_id){

        Cursor c = this.getReadableDatabase().query(TABLE_2_NAME, new String[] { COL_SHEET_ID, COL_HOUR, COL_DATE, COL_DRINK_LIST, COL_AMOUNT_LIST }, COL_SHEET_ID + " =? " + sheet_id,
                null, null, null, null, null);

        Drinksheet currentDrinksheet = new Drinksheet();

        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        else{

            c.moveToFirst();

            if(c.getCount() >= 1){
                do{

                    currentDrinksheet.setId(c.getInt(NUM_COL_SHEET_ID));
                    currentDrinksheet.setHour(c.getString(NUM_COL_HOUR));
                    currentDrinksheet.setDate(c.getString(NUM_COL_DATE));
                    currentDrinksheet.setDrink_list(c.getString(NUM_COL_DRINK_LIST));
                    currentDrinksheet.setDrink_list(c.getString(NUM_COL_AMOUNT_LIST));

                }while(c.moveToNext());

            }
        }

        c.close();

        return currentDrinksheet;

    }


    public Cursor fetchDrinkInfo(){

        String[]columns =  {COL_DRINK_ID, COL_NAME, COL_COUNTRY, COL_VOL, COL_KCAL};

        Cursor cursor = this.getReadableDatabase().query(TABLE_3_NAME,columns,null,null,null,null,null);

        return cursor;
    }

    public Drink getAllDrinkInfo(String beername){

        Cursor c = this.getReadableDatabase().query(TABLE_3_NAME, new String[] { COL_DRINK_ID, COL_NAME, COL_COUNTRY, COL_VOL, COL_KCAL }, COL_NAME + " =? " + beername,
                null, null, null, null, null);

        Drink currentDrink = new Drink();

        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        else{

            c.moveToFirst();

            if(c.getCount() >= 1){
                do{

                    currentDrink.setId(c.getInt(NUM_COL_DRINK_ID));
                    currentDrink.setName(c.getString(NUM_COL_NAME));
                    currentDrink.setCountry(c.getString(NUM_COL_COUNTRY));
                    currentDrink.setVol(c.getString(NUM_COL_VOL));
                    currentDrink.setKcal(c.getString(NUM_COL_KCAL));

                }while(c.moveToNext());

            }
        }

        c.close();

        return currentDrink;

    }

}