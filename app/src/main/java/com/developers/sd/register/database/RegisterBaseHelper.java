package com.developers.sd.register.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.developers.sd.register.database.RegisterDbSchema.RegisterTable;

/**
 * Created by sohamdeshmukh on 22/06/17.
 */

public class RegisterBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "RegisterBase.db";
    public RegisterBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + RegisterTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                RegisterTable.Cols.UUID + ", " +
                RegisterTable.Cols.DATE + ", " +
                RegisterTable.Cols.PROJECT + ", " +
                RegisterTable.Cols.VENDOR + ", " +
                RegisterTable.Cols.TEAMS + ", " +
                RegisterTable.Cols.TEAMSTOTAL + ", " +
                RegisterTable.Cols.DC + ", " +
                RegisterTable.Cols.HV + ", " +
                RegisterTable.Cols.WORK + ", " +
                RegisterTable.Cols.UNKNOWN + ", " +
                RegisterTable.Cols.DCCOMPL + ", " +
                RegisterTable.Cols.HVCOMPL + ", " +
                RegisterTable.Cols.REMARKS +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
