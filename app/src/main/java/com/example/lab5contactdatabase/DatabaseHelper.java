package com.example.lab5contactdatabase;

import android.app.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contact_details";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "contact_database";
    private static final String ID_COLUMN_NAME = "person_id";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String DOB_COLUMN_NAME = "dob";
    private static final String EMAIL_COLUMN_NAME = "email";
    private static final String AVATAR_COLUMN_NAME = "avatar";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COLUMN_NAME + " TEXT,"
                + DOB_COLUMN_NAME + " TEXT,"
                + EMAIL_COLUMN_NAME + " TEXT,"
                + AVATAR_COLUMN_NAME + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Alter table, if needed.
        if (newVersion > oldVersion) {
            String upgradeQuery = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + AVATAR_COLUMN_NAME
                    + " INTEGER DEFAULT 0";
            db.execSQL(upgradeQuery);
        }
    }

    public void insertDetails(String name, String dob, String email, int avatar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues rowValues = new ContentValues();
        rowValues.put(NAME_COLUMN_NAME, name);
        rowValues.put(DOB_COLUMN_NAME, dob);
        rowValues.put(EMAIL_COLUMN_NAME, email);
        rowValues.put(AVATAR_COLUMN_NAME, avatar);
        db.insert(TABLE_NAME, null, rowValues);
        db.close();
    }

    public ArrayList<PersonModal> getDetails(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<PersonModal> listPeople = new ArrayList<>();

        if (results != null && results.moveToFirst()) {
            int idColumnIndex = results.getColumnIndex(ID_COLUMN_NAME);
            int nameColumnIndex = results.getColumnIndex(NAME_COLUMN_NAME);
            int dobColumnIndex = results.getColumnIndex(DOB_COLUMN_NAME);
            int emailColumnIndex = results.getColumnIndex(EMAIL_COLUMN_NAME);
            int avatarColumnIndex = results.getColumnIndex(AVATAR_COLUMN_NAME);

            if (idColumnIndex >= 0 && nameColumnIndex >= 0 && dobColumnIndex >= 0 &&
                    emailColumnIndex >= 0 && avatarColumnIndex != -1) {
                do {
                    listPeople.add(new PersonModal(
                            results.getInt(idColumnIndex),
                            results.getString(nameColumnIndex),
                            results.getString(dobColumnIndex),
                            results.getString(emailColumnIndex),
                            results.getInt(avatarColumnIndex)
                    ));
                } while (results.moveToNext());
            }
        }
        if (results != null) {
            results.close();
        }
        return listPeople;
    }
}
