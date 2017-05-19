package com.example.binht.testvocabulary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by binht on 4/20/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "vocabulary.db";
    public static final String TABLE_NAME_TOPIC = "topic_table";
    public static final String TOPIC_ID_COLUMN = "ID_TOPIC_COLUMN";
    public static final String TOPIC_NAME_COLUMN = "NAME_TOPIC_COLUMN";

    public static final String TABLE_NAME_VOCA = "vocabulary_table";
    public static final String VOCA_ID_COLUMN = "ID_COLUMN";
    public static final String VOCA_WORD_COLUMN = "WORD_COLUMN";
    public static final String VOCA_TYPE_COLUMN = "TYPE_COLUMN";
    public static final String VOCA_TOPIC_COLUMN = "ID_TOPIC_COLUMN";
    public static final String VOCA_PRONOUNCE_COLUMN = "PRONOUNCE_COLUMN";

    public static final String CREATE_TOPIC = "CREATE TABLE "+ TABLE_NAME_TOPIC
            + "(" +TOPIC_ID_COLUMN +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            TOPIC_NAME_COLUMN +" TEXT)";

    public static final String CREATE_VOCA = "CREATE TABLE "+ TABLE_NAME_VOCA
            + "(" +VOCA_ID_COLUMN +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            VOCA_WORD_COLUMN +" TEXT,"
            + VOCA_TYPE_COLUMN + " INTEGER, "
            + VOCA_TOPIC_COLUMN + " INTEGER, "
            + VOCA_PRONOUNCE_COLUMN +" TEXT, "
             // Set up the location column as a foreign key to location table.
            +    " FOREIGN KEY (" +VOCA_TOPIC_COLUMN + ") REFERENCES " +
            TABLE_NAME_TOPIC+ " (" + TOPIC_ID_COLUMN + "))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TOPIC);
        db.execSQL(CREATE_VOCA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_TOPIC);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_VOCA);
        onCreate(db);
    }

    public boolean insertData(String word, int type, String pron){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VOCA_WORD_COLUMN,word);
        contentValues.put(VOCA_PRONOUNCE_COLUMN,pron);
        contentValues.put(VOCA_TYPE_COLUMN,type);

        long result = db.insert(TABLE_NAME_VOCA,null, contentValues);
        if(result == -1) return false;
        return true;
    }
    public Cursor getAllTopic(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("Select * from "+TABLE_NAME_TOPIC, null);
        return res;
    }
    public Cursor getAllVoca(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("Select * from "+TABLE_NAME_VOCA, null);
        return res;
    }
}
