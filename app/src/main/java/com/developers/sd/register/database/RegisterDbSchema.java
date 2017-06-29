package com.developers.sd.register.database;

/**
 * Created by sohamdeshmukh on 22/06/17.
 */

public class RegisterDbSchema {
    public  static final class RegisterTable {
        public static final String NAME = "register";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String DATE = "date";
            public static final String PROJECT = "project";
            public static final String VENDOR = "vendor";
            public static final String TEAMS = "teams";
            public static final String TEAMSTOTAL = "teamstotal";
            public static final String DC = "dc";
            public static final String HV = "hv";
            public static final String WORK = "work";
            public static final String UNKNOWN = "unknown";
            public static final String DCCOMPL = "dccompl";
            public static final String HVCOMPL = "hvcompl";
            public static final String REMARKS = "remarks";
        }
    }
}
