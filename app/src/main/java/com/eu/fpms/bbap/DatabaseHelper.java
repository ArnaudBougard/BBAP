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

    private static final String DB_NAME = "invibes.db";
    private static final int VERSION = 5;
    public static final String TABLE_NAME="user";
    public static final String COL_ID="ID";
    private static final int NUM_COL_ID = 0;
    public static final String COL_NAME="Name";
    private static final int NUM_COL_NAME = 1;
    public static final String COL_USERNAME="Username";
    private static final int NUM_COL_USERNAME = 2;
    public static final String COL_PASSWORD="Password";
    private static final int NUM_COL_PASSWORD = 3;

    public static final String COL_TMIN="Tmin";
    public static final String COL_TMAX="Tmax";
    public static final String COL_HMIN="Hmin";
    public static final String COL_HMAX="Hmax";
    public static final String COL_BMIN="Bmin";
    public static final String COL_BMAX="Bmax";
    public static final String COL_PHONE="Phone";
    private static final int NUM_COL_TMIN = 4;
    private static final int NUM_COL_TMAX = 5;
    private static final int NUM_COL_HMIN = 6;
    private static final int NUM_COL_HMAX = 7;
    private static final int NUM_COL_BMIN = 8;
    private static final int NUM_COL_BMAX = 9;
    private static final int NUM_COL_PHONE = 10;

    private static final String CREATE_DB = " CREATE TABLE " + TABLE_NAME + " ("
                                            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                            + COL_NAME + " TEXT NOT NULL, "
                                            + COL_USERNAME + " TEXT NOT NULL, "
                                            + COL_PASSWORD + " TEXT NOT NULL, "
                                            + COL_TMIN + " TEXT, "
                                            + COL_TMAX + " TEXT, "
                                            + COL_HMIN + " TEXT, "
                                            + COL_HMAX + " TEXT, "
                                            + COL_BMIN + " TEXT, "
                                            + COL_BMAX + " TEXT, "
                                            + COL_PHONE + " TEXT );";

    public DatabaseHelper(Context context) {

        super(context, TABLE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_DB);
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
        content.put(COL_NAME, user.getName());
        content.put(COL_USERNAME, user.getUsername());
        content.put(COL_PASSWORD, user.getPassword());

        return this.getWritableDatabase().insert(TABLE_NAME, null, content);

    }

    public boolean updateUserPreferences(String username, String Tmin, String Tmax, String Hmin, String Hmax, String Bmin, String Bmax, String Phone) {
            ContentValues content = new ContentValues();
            content.put(COL_TMIN, Tmin);
            content.put(COL_TMAX, Tmax);
            content.put(COL_HMIN, Hmin);
            content.put(COL_HMAX, Hmax);
            content.put(COL_BMIN, Bmin);
            content.put(COL_BMAX, Bmax);
            content.put(COL_PHONE, Phone);

           this.getWritableDatabase().update(TABLE_NAME, content, COL_USERNAME + " =? ", new String[]{username});

           return true;
    }

    public User fetchUserInfo( String username ) {
        Cursor c = this.getReadableDatabase().query(TABLE_NAME, new String[] { COL_ID, COL_NAME, COL_USERNAME, COL_PASSWORD, COL_TMIN, COL_TMAX,
                COL_HMIN, COL_HMAX, COL_BMIN, COL_BMAX, COL_PHONE }, COL_USERNAME + " =? ", new String[]{username}, null, null, null, null);

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
                    currentUser.setName(c.getString(NUM_COL_NAME));
                    currentUser.setUsername(c.getString(NUM_COL_USERNAME));
                    currentUser.setPassword(c.getString(NUM_COL_PASSWORD));
                    currentUser.setTmin(c.getString(NUM_COL_TMIN));
                    currentUser.setTmax(c.getString(NUM_COL_TMAX));
                    currentUser.setHmin(c.getString(NUM_COL_HMIN));
                    currentUser.setHmax(c.getString(NUM_COL_HMAX));
                    currentUser.setBmin(c.getString(NUM_COL_BMIN));
                    currentUser.setBmax(c.getString(NUM_COL_BMAX));
                    currentUser.setPhone(c.getString(NUM_COL_PHONE));

                }while(c.moveToNext());

            }
        }

        c.close();
        return currentUser;
    }

}