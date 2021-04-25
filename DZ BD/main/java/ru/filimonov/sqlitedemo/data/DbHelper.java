package ru.filimonov.sqlitedemo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ru.filimonov.sqlitedemo.data.StudentDbContract.*;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "studDb.db";
    public static final int DATABASE_VERSION = 1;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ").append(StudentsEntry.TABLE_NAME).append("(")
                .append(StudentsEntry._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(StudentsEntry.COLUMN_SURNAME).append(" TEXT(50) NOT NULL,")
                .append(StudentsEntry.COLUMN_NAME).append(" TEXT(50) NOT NULL")
                .append(");");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sql = new StringBuilder();
        sql.append("DROP TABLE IF EXISTS ").append(StudentsEntry.TABLE_NAME);
        db.execSQL(sql.toString());
        onCreate(db);
    }
}
