package com.developers.sd.register.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import com.developers.sd.register.Register;
import com.developers.sd.register.database.RegisterDbSchema.RegisterTable;

/**
 * Created by sohamdeshmukh on 22/06/17.
 */

public class RegisterCursorWrapper extends CursorWrapper {

    public RegisterCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Register getRegister() {
        String uuidString = getString(getColumnIndex(RegisterTable.Cols.UUID));
        String date = getString(getColumnIndex(RegisterTable.Cols.DATE));
        String project = getString(getColumnIndex(RegisterTable.Cols.PROJECT));
        String vendor = getString(getColumnIndex(RegisterTable.Cols.VENDOR));
        String teams = getString(getColumnIndex(RegisterTable.Cols.TEAMS));
        String teamstotal = getString(getColumnIndex(RegisterTable.Cols.TEAMSTOTAL));
        String dc = getString(getColumnIndex(RegisterTable.Cols.DC));
        String hv = getString(getColumnIndex(RegisterTable.Cols.HV));
        String work = getString(getColumnIndex(RegisterTable.Cols.WORK));
        String unknown = getString(getColumnIndex(RegisterTable.Cols.UNKNOWN));
        String dccompl = getString(getColumnIndex(RegisterTable.Cols.DCCOMPL));
        String hvcompl = getString(getColumnIndex(RegisterTable.Cols.HVCOMPL));
        String remarks = getString(getColumnIndex(RegisterTable.Cols.REMARKS));


        Register register = new Register(UUID.fromString(uuidString));
        register.setDate(date);
        register.setProject(project);
        register.setVendor(vendor);
        register.setTeams(teams);
        register.setTeamstotal(teamstotal);
        register.setDC(dc);
        register.setHV(hv);
        register.setWork(work);
        register.setUnknown(unknown);
        register.setDCCompl(dccompl);
        register.setHVCompl(hvcompl);
        register.setRemarks(remarks);
        return register;
    }
}
