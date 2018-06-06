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
    private static final int VERSION = 1;

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


    public DatabaseHelper(Context context) {

        super(context, TABLE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
//        db.execSQL(CREATE_DRINKSHEET_TABLE);
//        db.execSQL(CREATE_DRINK_TABLE);
        Log.i("Database","onCreate invoqué");

    }

    @Override
    public void onUpgrade(SQLiteDatabase bdd, int oldVersion, int newVersion) {

        bdd.execSQL(" DROP TABLE " + TABLE_NAME);
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

        return this.getWritableDatabase().insert(TABLE_NAME, null, content);

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

    public User fetchUserInfo( String username ) {
        Cursor c = this.getReadableDatabase().query(TABLE_NAME, new String[] { COL_ID, COL_USERNAME, COL_PASSWORD, COL_EMAIL, COL_SEX,
                COL_AGE, COL_HEIGHT, COL_WEIGHT}, COL_USERNAME + " =? ", new String[]{username}, null, null, null, null);

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
                    currentUser.setAge(c.getString(NUM_COL_AGE));
                    currentUser.setHeight(c.getString(NUM_COL_HEIGHT));
                    currentUser.setWeight(c.getString(NUM_COL_WEIGHT));


                }while(c.moveToNext());

            }
        }

        c.close();
        return currentUser;
    }

}