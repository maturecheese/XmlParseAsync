package com.example.mark_i5.xmlparseasync.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mark-i5 on 27/08/2014.
 */
public class ArticleDatabase {
    private static final String LOGTAG = "ArticleDatabase";
    private Context context;

    public ArticleDatabase(Context context){
        this.context = context;
    }

    public void insertTrack (Article article, String country){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ArticleDatabaseHelper.TITLE, track.getArtist());
        contentValues.put(ArticleDatabaseHelper.TRACK_NAME, track.getName());
        contentValues.put(ArticleDatabaseHelper.ARTIST_URL, track.getArtistUrl());
        contentValues.put(ArticleDatabaseHelper.TRACK_URL, track.getTrackUrl());
        contentValues.put(ArticleDatabaseHelper.COUNTRY, country);
        long id = db.insert(ArticleDatabaseHelper.TABLE_NAME, null,contentValues);
        // long id  = db.insertWithOnConflict(TrackDatabaseHelper.TABLE_NAME,null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d(LOGTAG, "track_id: " + id);

    }

    private class ArticleDatabaseHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "articles.db";
        private static final String TABLE_NAME = "article_table";
        private static final int DATABASE_VERSION = 1;

        private static final String TITLE = "title";
        private static final String DESCRIPTION = "description";
        private static final String IMAGE_PATH = "image_path";
        private static final String CREATED_AT = "created_at";

        private static final String TID = "_id";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME +" ("+TID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+ TITLE +" TEXT," + DESCRIPTION + " TEXT,"+
                IMAGE_PATH +" TEXT," + CREATED_AT + "TEXT)";

        public ArticleDatabaseHelper(Context context){
            super(context,DATABASE_NAME, null, DATABASE_VERSION);

        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        }
    }
}
