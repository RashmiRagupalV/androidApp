package redflag.capstone.com.redflag;

/**
 * Created by rashm on 3/24/2016.
 */
public class Student {

    public static final String USER_StudID = "Student_ID";
    public static final String USER_CNAME = "ChildName";
    public static final String USER_TNAME = "TeacherName";
    public static final String USER_AGE = "Age";
    public static final String USER_SCHOOL = "School";
    public static final String USER_GRADE = "Grade";
    public static final String USER_DOS = "Date";
    public static final String TESTER = "TesterName";

    public static final String TABLE_NAME = "reg_info";

    public static final String TIMESTAMP = "Timestamp";

    public static final String ZEROETH_COLUMN = "Zero";
    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";
    public static final String THIRD_COLUMN="Third";


    public String getUser_cname() {
        return user_cname;
    }

    public void setUser_cname(String user_cname) {
        this.user_cname = user_cname;
    }

    public String getUser_tname() {
        return user_tname;
    }

    public void setUser_tname(String user_tname) {
        this.user_tname = user_tname;
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public String getUser_school() {
        return user_school;
    }

    public void setUser_school(String user_school) {
        this.user_school = user_school;
    }

    public String getUser_grade() {
        return user_grade;
    }

    public void setUser_grade(String user_grade) {
        this.user_grade = user_grade;
    }

    public String getUser_dos() {
        return user_dos;
    }

    public void setUser_dos(String user_dos) {
        this.user_dos = user_dos;
    }

    private String user_cname;
    private String user_tname;
    private String user_age;
    private String user_school;
    private String user_grade;
    private String user_dos;

}

