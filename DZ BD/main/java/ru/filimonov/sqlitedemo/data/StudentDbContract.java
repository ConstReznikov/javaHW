package ru.filimonov.sqlitedemo.data;

import android.provider.BaseColumns;

public final class StudentDbContract {
    private StudentDbContract(){}

    public static class StudentsEntry implements BaseColumns{
        public static final String TABLE_NAME = "students";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_NAME = "name";

    }

}
