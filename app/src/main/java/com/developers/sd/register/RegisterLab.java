package com.developers.sd.register;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.developers.sd.register.database.RegisterDbSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.developers.sd.register.database.RegisterBaseHelper;
import com.developers.sd.register.database.RegisterCursorWrapper;
import com.developers.sd.register.database.RegisterDbSchema.RegisterTable;

/**
 * Created by sohamdeshmukh on 21/06/17.
 */

public class RegisterLab {

    private static RegisterLab sRegisterLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public void addRegister(Register c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(RegisterTable.NAME, null, values);
    }

    public void deleteRegister(Register c)
    {
        ContentValues values = getContentValues(c);
        mDatabase.delete(RegisterTable.NAME, "UUID =?", new String[]{c.getId().toString()});
    }
//
//    public void deleteEmptyRegister(Register c)
//    {
//        ContentValues values = getContentValues(c);
//        mDatabase.delete(RegisterTable.NAME, "UUID =?", new String[]{c.getId().toString()});
//    }
//
    public void Delete_all()
    {
        RegisterCursorWrapper cursor = queryRegisters(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                mDatabase.delete(RegisterTable.NAME, "UUID =?", new String[]{cursor.getRegister().getId().toString()});
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
    }
//
//    public void deleteRegisters(List<Register> mRegisters) {
//        for (int i = 0; i < mRegisters.size(); i++) {
//            mDatabase.delete(RegisterTable.NAME, "UUID =?", new String[]{mRegisters.get(i).getId().toString()});
//        }
//    }

    public static RegisterLab get(Context context) {
        if (sRegisterLab == null) {
            sRegisterLab = new RegisterLab(context);
        }
        return sRegisterLab;
    }

    private RegisterLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new RegisterBaseHelper(mContext)
                .getWritableDatabase();
    }

    public Register getRegister(UUID id) {
        Log.e("OK", "lab3 started");
        RegisterCursorWrapper cursor = queryRegisters(
                RegisterTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        Log.e("OK", "lab4 started");


        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            Log.e("OK", "lab5 started");
            return cursor.getRegister();
        } finally {
            cursor.close();
        }
    }


    public List<Register> getRegisters() {
        List<Register> registers = new ArrayList<>();
        RegisterCursorWrapper cursor = queryRegisters(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                registers.add(cursor.getRegister());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return registers;
    }

    public void updateRegister(Register register) {
        String uuidString = register.getId().toString();
        ContentValues values = getContentValues(register);

        mDatabase.update(RegisterTable.NAME, values,
                RegisterTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private static ContentValues getContentValues(Register register) {
        ContentValues values = new ContentValues();
        values.put(RegisterTable.Cols.UUID, register.getId().toString());
        values.put(RegisterTable.Cols.DATE, register.getDate());
        values.put(RegisterTable.Cols.PROJECT, register.getProject());
        values.put(RegisterTable.Cols.VENDOR, register.getVendor());
        values.put(RegisterTable.Cols.TEAMS, register.getTeams());
        values.put(RegisterTable.Cols.TEAMSTOTAL, register.getTeamstotal());
        values.put(RegisterTable.Cols.DC, register.getDC());
        values.put(RegisterTable.Cols.HV, register.getHV());
        values.put(RegisterTable.Cols.WORK, register.getWork());
        values.put(RegisterTable.Cols.UNKNOWN, register.getUnknown());
        values.put(RegisterTable.Cols.DCCOMPL, register.getDCCompl());
        values.put(RegisterTable.Cols.HVCOMPL, register.getHVCompl());
        values.put(RegisterTable.Cols.REMARKS, register.getRemarks());
        return values;
    }

    private RegisterCursorWrapper queryRegisters(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                RegisterTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new RegisterCursorWrapper(cursor);
    }

    public List<String> RegisterDate() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<String>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String date = c.getString(c.getColumnIndex(RegisterTable.Cols.DATE));
                Log.e("String",date);
                array.add(date);
                c.moveToNext();
            }
                }
                finally{
                    c.close();
                }
        return array;
        }

    public List<String> RegisterProject() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<String>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String project = c.getString(c.getColumnIndex(RegisterTable.Cols.PROJECT));
//                Log.e("String1",project);
                array.add(project);
                c.moveToNext();
            }
        }
        finally{
            c.close();
        }
        return array;
    }

    public List<String> RegisterVendor() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<String>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String vendor = c.getString(c.getColumnIndex(RegisterTable.Cols.VENDOR));
//                Log.e("String3",vendor);
                array.add(vendor);
                c.moveToNext();
            }
        }
        finally{
            c.close();
        }
        return array;
    }

    public List<String> RegisterTeams() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<String>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String teams = c.getString(c.getColumnIndex(RegisterTable.Cols.TEAMS));
//                Log.e("String",teams);
                array.add(teams);
                c.moveToNext();
            }
        }
        finally{
            c.close();
        }
        return array;
    }

    public List<String> RegisterTeamstotal() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<String>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String teamstotal = c.getString(c.getColumnIndex(RegisterTable.Cols.TEAMSTOTAL));
//                Log.e("String",teamstotal);
                array.add(teamstotal);
                c.moveToNext();
            }
        }
        finally{
            c.close();
        }
        return array;
    }

    public List<String> RegisterDC() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String dc = c.getString(c.getColumnIndex(RegisterTable.Cols.DC));
//                Log.e("String",dc);
                array.add(dc);
                c.moveToNext();
            }
        }
        finally{
            c.close();
        }
        return array;
    }

    public List<String> RegisterHV() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<String>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String hv = c.getString(c.getColumnIndex(RegisterTable.Cols.HV));
//                Log.e("String",hv);
                array.add(hv);
                c.moveToNext();
            }
        }
        finally{
            c.close();
        }
        return array;
    }

    public List<String> RegisterWork() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<String>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String work = c.getString(c.getColumnIndex(RegisterTable.Cols.WORK));
//                Log.e("String",work);
                array.add(work);
                c.moveToNext();
            }
        }
        finally{
            c.close();
        }
        return array;
    }

    public List<String> RegisterUnknown() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<String>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String unknown = c.getString(c.getColumnIndex(RegisterTable.Cols.UNKNOWN));
//                Log.e("String",unknown);
                array.add(unknown);
                c.moveToNext();
            }
        }
        finally{
            c.close();
        }
        return array;
    }

    public List<String> RegisterDCCompl() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<String>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String dccompl = c.getString(c.getColumnIndex(RegisterTable.Cols.DCCOMPL));
//                Log.e("String",dccompl);
                array.add(dccompl);
                c.moveToNext();
            }
        }
        finally{
            c.close();
        }
        return array;
    }

    public List<String> RegisterHVCompl() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<String>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String hvcompl = c.getString(c.getColumnIndex(RegisterTable.Cols.HVCOMPL));
//                Log.e("String",hvcompl);
                array.add(hvcompl);
                c.moveToNext();
            }
        }
        finally{
            c.close();
        }
        return array;
    }

    public List<String> RegisterRemarks() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM register", null);
        List<String> array = new ArrayList<String>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String remarks = c.getString(c.getColumnIndex(RegisterTable.Cols.REMARKS));
//                Log.e("String",remarks);
                array.add(remarks);
                c.moveToNext();
            }
        }
        finally{
            c.close();
        }
        return array;
    }

    }
