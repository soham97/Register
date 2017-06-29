package com.developers.sd.register;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by sohamdeshmukh on 21/06/17.
 */

public class Register {

    private UUID mId;
    private String mDate;
    private String mProject = "";
    private String mVendor="";
    private String mTeams;
    private String mTeamstotal;
    private String mDC="0";
    private String mHV="0";
    private String mWork;
    private String mUnknown="0";
    private String mDCCompl="0";
    private String mHVCompl="0";
    private String mRemarks;

    public Register(){
        this(UUID.randomUUID());
    }


    public Register(UUID id){
        mId = id;
//        mDate = new SimpleDateFormat("MMM dd,yyyy  hh:mm a").format(new Date());
        mDate = new SimpleDateFormat("MMM dd,yyyy").format(new Date());
    }

    public UUID getId() {
        return mId;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String Date) {
        mDate = Date;
    }

    public String getRemarks() {
        return mRemarks;
    }

    public void setRemarks(String Remarks) {
        mRemarks = Remarks;
    }

    public String getHVCompl() {
        return mHVCompl;
    }

    public void setHVCompl(String HVCompl) {
        mHVCompl = HVCompl;
    }

    public String getDCCompl() {
        return mDCCompl;
    }

    public void setDCCompl(String DCCompl) {
        mDCCompl = DCCompl;
    }

    public String getUnknown() {
        return mUnknown;
    }

    public void setUnknown(String Unknown) {
        mUnknown = Unknown;
    }

    public String getWork() {
        return mWork;
    }

    public void setWork(String Work) {
        mWork = Work;
    }

    public String getHV() {
        return mHV;
    }

    public void setHV(String HV) {
        mHV = HV;
    }

    public String getDC() {
        return mDC;
    }

    public void setDC(String DC) {
        mDC = DC;
    }

    public String getTeamstotal() {
        return mTeamstotal;
    }

    public void setTeamstotal(String Teamstotal) {
        mTeamstotal = Teamstotal;
    }

    public String getTeams() {
        return mTeams;
    }

    public void setTeams(String Teams) {
        mTeams = Teams;
    }

    public String getVendor() {
        return mVendor;
    }

    public void setVendor(String Vendor) {
        mVendor = Vendor;
    }

    public String getProject() {
        return mProject;
    }

    public void setProject(String Project) {
        mProject = Project;
    }
}
