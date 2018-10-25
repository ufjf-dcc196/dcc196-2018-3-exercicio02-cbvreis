package com.example.cassi.exercicio_series;

    
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class SerieDbHelper extends SQLiteOpenHelper {

        public final static int DATABASE_VERSION = 1;
        public final static String DATABASE_NAME = "Serie.db";

        public SerieDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SerieContract.Serie.CREATE_SERIE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SerieContract.Serie.DROP_SERIE);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

    }