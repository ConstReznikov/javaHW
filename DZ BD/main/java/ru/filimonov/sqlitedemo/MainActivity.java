package ru.filimonov.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ru.filimonov.sqlitedemo.data.DbHelper;
import ru.filimonov.sqlitedemo.data.StudentDbContract.*;

public class MainActivity extends AppCompatActivity {

    private DbHelper dbHelper = null;
    private EditText surnameText = null;
    private EditText nameText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(this);
        surnameText = (EditText) findViewById(R.id.SurnameEditText);
        nameText = (EditText) findViewById(R.id.NameEditText);
    }

    public void insertButtonClick(View view) {
        //
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(StudentsEntry.TABLE_NAME).append("(")
                .append(StudentsEntry.COLUMN_SURNAME).append(", ")
                .append(StudentsEntry.COLUMN_NAME)
                .append(") values(")
                .append("'").append(surnameText.getText().toString()).append("'")
                .append(", ")
                .append("'").append(nameText.getText().toString()).append("'")
                .append(")");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql.toString());
    }

    public void viewButtonClick(View view) {
        TextView res = (TextView) findViewById(R.id.textView3);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(StudentsEntry.TABLE_NAME);
        Cursor cursor = db.rawQuery(sql.toString(), null);
        try {
            String surname;
            String name;
            String info;
            int surnameIndex = cursor.getColumnIndex(StudentsEntry.COLUMN_SURNAME);
            int nameIndex = cursor.getColumnIndex(StudentsEntry.COLUMN_NAME);
            while (cursor.moveToNext()){
                surname = cursor.getString(surnameIndex);
                name = cursor.getString(nameIndex);
                info = String.format("Фамилия: %1$s; Имя: %2$s\n", surname, name);
                res.append(info);
            }
        } finally {
            cursor.close();
        }



    }
}