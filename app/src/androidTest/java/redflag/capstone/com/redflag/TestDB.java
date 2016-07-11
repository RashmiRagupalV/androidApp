package redflag.capstone.com.redflag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.test.AndroidTestCase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TestDB extends AndroidTestCase {

    public static final String LOG_TAG = TestDB.class.getSimpleName();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");;
    public void testinsertestDb(){

        // Test data to verify inserted data is same as expected data
        String studName = "lisa";
        String teacherName = "nicole";
        String studAge = "5";
        String schoolName = "evergreen";
        String studGrade = "1";
        String testerName = "Admin";
        String createDate = dateFormat.format(new Date());

        DatabaseOperations dbhelper = new DatabaseOperations(mContext);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Student.USER_CNAME, studName);
        cv.put(Student.USER_TNAME, teacherName);
        cv.put(Student.USER_AGE, studAge);
        cv.put(Student.USER_DOS, createDate);
        cv.put(Student.USER_GRADE, studGrade);
        cv.put(Student.USER_SCHOOL, schoolName);
        cv.put(Student.TESTER,testerName);

        long rowid;
        rowid = db.insert(Student.TABLE_NAME, null, cv);

        // Verify we get a row back
        assertTrue(rowid != 1);
        Log.d(LOG_TAG,"New row id: " + rowid);
        Log.d("Database operations", "One row inserted");

        String[] columns = {
                Student.USER_CNAME,
                Student.USER_TNAME,
                Student.USER_AGE,
                Student.USER_GRADE,
                Student.USER_SCHOOL,
                Student.TESTER,
        };

        Cursor cr = db.query(Student.TABLE_NAME,
                columns, null, null, null, null, null);

        if(cr.moveToFirst()){
            int nameindex = cr.getColumnIndex(Student.USER_CNAME);
            String sname = cr.getString(nameindex);

            int tnameindex = cr.getColumnIndex(Student.USER_TNAME);
            String tname = cr.getString(tnameindex);

            int ageindex = cr.getColumnIndex(Student.USER_AGE);
            String age = cr.getString(ageindex);

            int schoolindex = cr.getColumnIndex(Student.USER_SCHOOL);
            String school = cr.getString(schoolindex);

            int gradeindex = cr.getColumnIndex(Student.USER_GRADE);
            String grade = cr.getString(gradeindex);

            int testerindex = cr.getColumnIndex(Student.TESTER);
            String tester = cr.getString(testerindex);

            assertEquals(sname,studName);
            assertEquals(tname,"Admin");
            assertEquals(age,studAge);
            assertEquals(school,schoolName);
            assertEquals(tester,testerName);
            assertEquals(grade,studGrade);
        }
        else
            fail(" No values returned !");

    }
}
