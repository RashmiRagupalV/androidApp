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

//    public static abstract class RegisterInfo implements BaseColumns
//    {
//        public static final String USER_CNAME = "ChildName";
//        public static final String USER_TNAME = "TeacherName";
//        public static final String USER_AGE = "Age";
//        public static final String USER_SCHOOL = "School";
//        public static final String USER_GRADE = "Grade";
//        public static final String USER_DOS = "Date";
//        public static final String TABLE_NAME = "reg_info";
//
//    }
//
//    private String user_cname;
//    private String user_tname;
//    private String user_age;
//    private String user_school;
//    private String user_grade;
//    private String user_dos;

}
