package redflag.capstone.com.redflag;

import android.provider.BaseColumns;

/**
 * Created by rashm on 1/10/2016.
 */
public class TableData {
    public TableData(){}

    public static abstract class TableInfo implements BaseColumns
    {
        public static final String USER_NAME = "admin";
        public static final String USER_PASS = "admin";

        public static final String TABLE_NAME = "reg_data";
    }

    public static abstract class RegisterInfo implements BaseColumns
    {
        public static final String USER_FNAME = "FirstName";
        public static final String USER_LNAME = "LastName";
        public static final String USER_EMAIl = "Email";
        public static final String USER_AGE = "Age";
        public static final String USER_SCHOOL = "School";
        public static final String USER_CLASS = "Class";
        public static final String USER_GUARDIAN = "GuardianName";
        public static final String TABLE_NAME = "reg_info";
       // public static final String DATABASE_NAME = "user_info";


    }
}
