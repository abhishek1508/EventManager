package com.abhishek.eventmanager.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.abhishek.eventmanager.Model.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek on 2/23/2016.
 */
public class DBEventHelper extends SQLiteOpenHelper {

    private Context mContext;
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "EventsManager";
    // Table Names
    private static final String TABLE_EMAIL = "TABLE_EMAIL";
    // Email table - Column names
    private static final String KEY_ID = "EmailID";
    private static final String KEY_EMAIL_ID = "EmailTo";
    private static final String KEY_SUBJECT = "Subject";
    private static final String KEY_BODY = "Body";

    //Create Email table
    private static final String CREATE_TABLE_EMAIL = "CREATE TABLE "
            + TABLE_EMAIL + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_EMAIL_ID
            + " TEXT," + KEY_SUBJECT + " TEXT," + KEY_BODY
            + " TEXT" + ")";


    public DBEventHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EMAIL);
        Toast.makeText(mContext, "Table is created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_EMAIL);
        // create new tables
        onCreate(db);
    }

    public void addEmail(Email email){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL_ID, email.getTo());
        values.put(KEY_SUBJECT, email.getSubject());
        values.put(KEY_BODY, email.getBody());

        db.insert(TABLE_EMAIL, null, values);
        db.close();
        Toast.makeText(mContext, "1st entry added to the table", Toast.LENGTH_SHORT).show();
    }

    public List<Email> getAllEmailEvents(){
        List<Email> emailList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EMAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Email email = new Email();
                email.setId(Integer.parseInt(cursor.getString(0)));
                email.setTo(cursor.getString(1));
                email.setSubject(cursor.getString(2));
                email.setBody(cursor.getString(3));
                // Adding contact to list
                emailList.add(email);
            } while (cursor.moveToNext());
        }
        // return contact list
        return emailList;
    }
}
