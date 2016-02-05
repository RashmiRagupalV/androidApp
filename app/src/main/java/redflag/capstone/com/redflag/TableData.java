package redflag.capstone.com.redflag;

import android.provider.BaseColumns;

/**
 * Created by rashm on 1/10/2016.
 */
public class TableData {
    public TableData(){}

    // Codes:
    // Admin - 1
    // Tester - 2
    public static abstract class TableInfo implements BaseColumns
    {
        public static final String USER_NAME = "UserName";
        public static final String USER_PASS = "Password";
        public static final String USER_TYPE = "UserType";
        public static final String TABLE_NAME = "login_user";
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
