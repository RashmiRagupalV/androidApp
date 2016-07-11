package redflag.capstone.com.redflag;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.MatrixCursor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.database.Cursor;
/**
 * Created by rashm on 1/10/2016.
 */
public class DatabaseOperations extends SQLiteOpenHelper {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentDateTimeString = dateFormat.format(new Date());

//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//    Date date = new Date();
//    String currentDateTimeString = dateFormat.format(date);
//

    public static final int database_version = 38;
    public String CREATE_QUERY_LOGIN = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME +
            "(" + TableData.TableInfo.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + TableData.TableInfo.USER_NAME + " TEXT," +
            TableData.TableInfo.USER_PASS + " TEXT," +
            TableData.TableInfo.USER_TYPE + " TEXT );";

    public String CREATE_QUERY_REGISTER = "CREATE TABLE " + Student.TABLE_NAME +
            "(" + Student.USER_StudID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + Student.USER_CNAME + " TEXT NOT NULL," +
            Student.USER_TNAME + " TEXT," +
            Student.USER_AGE + " TEXT," +
            Student.USER_DOS + " TEXT, " +
            Student.USER_GRADE + " TEXT," +
            Student.USER_SCHOOL + " TEXT," +
            Student.TESTER + " TEXT," +
            " UNIQUE ( " + Student.USER_CNAME + "," + Student.USER_GRADE + "," + Student.USER_SCHOOL + "));";

    public String CREATE_QUERY_TRACKING_EYEBALL = "CREATE TABLE " + TrackingEyeball.TABLE_NAME +
            "(" + TrackingEyeball.USER_StudID + " INTEGER," + TrackingEyeball.RECORD_DATE + " TEXT," + TrackingEyeball.HORIZONTAL_PARAMETER + " TEXT," +
            TrackingEyeball.NEED_BEANBAG + " TEXT," + TrackingEyeball.VERTICAL_PARAMETER + " TEXT," +
            TrackingEyeball.TESTER + " TEXT," +
            " FOREIGN KEY(" + TrackingEyeball.USER_StudID + ") REFERENCES " + Student.TABLE_NAME + "(" +
            Student.USER_StudID + "));";

    public String CREATE_QUERY_BALANCING = "CREATE TABLE " + TrackingBalancing.TABLE_NAME +
            "(" + TrackingEyeball.USER_StudID + " INTEGER," + TrackingBalancing.RECORD_DATE + " TEXT," + TrackingBalancing.EYE_OPEN_RIGHT + " TEXT," +
            TrackingBalancing.EYE_OPEN_LEFT + " TEXT," + TrackingBalancing.EYE_CLOSE_RIGHT + " TEXT," +
            TrackingBalancing.EYE_CLOSE_LEFT + " TEXT," +
            TrackingBalancing.TESTER + " TEXT," +
            " FOREIGN KEY(" + TrackingBalancing.USER_StudID + ") REFERENCES " + Student.TABLE_NAME + "(" +
            Student.USER_StudID + "));";

    public String CREATE_QUERY_SKIPPING = "CREATE TABLE " + TrackingSkipping.TABLE_NAME +
            "(" + TrackingSkipping.USER_StudID + " INTEGER," + TrackingSkipping.RECORD_DATE + " TEXT," +
            TrackingSkipping.PARAMETER + " TEXT," +
            TrackingSkipping.TESTER + " TEXT," +
            " FOREIGN KEY(" + TrackingSkipping.USER_StudID + ") REFERENCES " + Student.TABLE_NAME + "(" +
            Student.USER_StudID + "));";

    public String CREATE_QUERY_TEAMING = "CREATE TABLE " + TrackingTeaming.TABLE_NAME +
            "(" + TrackingTeaming.USER_StudID + " INTEGER," + TrackingTeaming.RECORD_DATE + " TEXT," +
            TrackingTeaming.ROUND1 + " TEXT," + TrackingTeaming.ROUND2 + " TEXT," +
            TrackingTeaming.TESTER + " TEXT," +
            " FOREIGN KEY(" + TrackingTeaming.USER_StudID + ") REFERENCES " + Student.TABLE_NAME + "(" +
            Student.USER_StudID + "));";

    public String CREATE_QUERY_VD = "CREATE TABLE " + TrackingVisualDiscrimination.TABLE_NAME +
            "(" + TrackingVisualDiscrimination.USER_StudID + " INTEGER," + TrackingVisualDiscrimination.RECORD_DATE + " TEXT," +
            TrackingVisualDiscrimination.PARAMETER + " TEXT," +
            TrackingVisualDiscrimination.TESTER + " TEXT," +
            " FOREIGN KEY(" + TrackingVisualDiscrimination.USER_StudID + ") REFERENCES " + Student.TABLE_NAME + "(" +
            Student.USER_StudID + "));";


    public String CREATE_QUERY_CRAWLING = "CREATE TABLE " + TrackingCrawling.TABLE_NAME +
            "(" + TrackingCrawling.USER_StudID + " INTEGER," + TrackingCrawling.RECORD_DATE + " TEXT," +
            TrackingCrawling.PARAMETER + " TEXT," +
            TrackingCrawling.TESTER + " TEXT," +
            " FOREIGN KEY(" + TrackingCrawling.USER_StudID + ") REFERENCES " + Student.TABLE_NAME + "(" +
            Student.USER_StudID + "));";


    public String CREATE_QUERY_COMMENTS = "CREATE TABLE " + TrackingComments.TABLE_NAME +
            "(" + TrackingComments.USER_StudID + " INTEGER," + TrackingComments.RECORD_DATE + " TEXT," +
            TrackingComments.COMMENTS + " TEXT," +
            TrackingComments.TESTER + " TEXT," +
            " FOREIGN KEY(" + TrackingComments.USER_StudID + ") REFERENCES " + Student.TABLE_NAME + "(" +
            Student.USER_StudID + "));";


    public static final String DATABASE_NAME = "redflag.db";

    public DatabaseOperations(Context context) {
        super(context, DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created !");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY_LOGIN);
        db.execSQL(CREATE_QUERY_REGISTER);
        db.execSQL(CREATE_QUERY_TRACKING_EYEBALL);
        db.execSQL(CREATE_QUERY_BALANCING);
        db.execSQL(CREATE_QUERY_SKIPPING);
        db.execSQL(CREATE_QUERY_CRAWLING);
        db.execSQL(CREATE_QUERY_TEAMING);
        db.execSQL(CREATE_QUERY_VD);
        db.execSQL(CREATE_QUERY_COMMENTS);
        insert_user_admin(db);
        Log.d("Database operations", "Tables created !");
       // db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableData.TableInfo.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TrackingEyeball.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TrackingBalancing.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TrackingSkipping.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TrackingComments.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TrackingCrawling.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TrackingVisualDiscrimination.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TrackingTeaming.TABLE_NAME);
        onCreate(db);
    }

    public void insert_user_admin(SQLiteDatabase dop)
    {

        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME, "Admin");
        cv.put(TableData.TableInfo.USER_PASS, "admin");
        cv.put(TableData.TableInfo.USER_TYPE, "Admin");
        long k = dop.insert(TableData.TableInfo.TABLE_NAME, null, cv);
        Log.d("Database operations", "One row inserted");

    }


    public Cursor getStudentDetails(DatabaseOperations dop, String selectedstudId)
    {
        String query = null;
        query = "SELECT * FROM " + Student.TABLE_NAME + " where " + Student.USER_StudID + " = '" + selectedstudId + "' ;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }


    public Cursor getStudentTracking(DatabaseOperations dop, String selectedstudId)
    {
        String query = null;
        query = "SELECT * FROM " + TrackingEyeball.TABLE_NAME + " where " + TrackingEyeball.USER_StudID + " = '" + selectedstudId + "' ORDER BY "
        + TrackingEyeball.RECORD_DATE + " DESC LIMIT 1;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }

    public Cursor getStudentBalancing(DatabaseOperations dop, String selectedstudId)
    {
        String query = null;
        query = "SELECT * FROM " + TrackingBalancing.TABLE_NAME + " where " + TrackingBalancing.USER_StudID + " = '" + selectedstudId + "' ORDER BY "
                + TrackingBalancing.RECORD_DATE + " DESC LIMIT 1;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }

    public Cursor getStudentSkipping(DatabaseOperations dop, String selectedstudId)
    {
        String query = null;
        query = "SELECT * FROM " + TrackingSkipping.TABLE_NAME + " where " + TrackingSkipping.USER_StudID + " = '" + selectedstudId + "' ORDER BY "
                + TrackingSkipping.RECORD_DATE + " DESC LIMIT 1;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }

    public Cursor getStudentVD(DatabaseOperations dop, String selectedstudId)
    {
        String query = null;
        query = "SELECT * FROM " + TrackingVisualDiscrimination.TABLE_NAME + " where " + TrackingVisualDiscrimination.USER_StudID + " = '" + selectedstudId + "' ORDER BY "
                + TrackingVisualDiscrimination.RECORD_DATE + " DESC LIMIT 1;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }

    public Cursor getStudentCrawling(DatabaseOperations dop, String selectedstudId)
    {
        String query = null;
        query = "SELECT * FROM " + TrackingCrawling.TABLE_NAME + " where " + TrackingCrawling.USER_StudID + " = '" + selectedstudId + "' ORDER BY "
                + TrackingCrawling.RECORD_DATE + " DESC LIMIT 1;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }

    public Cursor getStudentComments(DatabaseOperations dop, String selectedstudId)
    {
        String query = null;
        query = "SELECT * FROM " + TrackingComments.TABLE_NAME + " where " + TrackingComments.USER_StudID + " = '" + selectedstudId + "' ORDER BY "
                + TrackingComments.RECORD_DATE + " DESC LIMIT 1;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }



    public Cursor getStudentteaming(DatabaseOperations dop, String selectedstudId)
    {
        String query = null;
        query = "SELECT * FROM " + TrackingTeaming.TABLE_NAME + " where " + TrackingTeaming.USER_StudID + " = '" + selectedstudId + "' ORDER BY "
                + TrackingTeaming.RECORD_DATE + " DESC LIMIT 1;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }



    public void insertStudentTrackingDetails(DatabaseOperations dop, String studId,String radioButton1,String radioButton2,String radioButton3,String TesterName)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TrackingEyeball.USER_StudID,studId);
        cv.put(TrackingEyeball.HORIZONTAL_PARAMETER, radioButton1);
        cv.put(TrackingEyeball.NEED_BEANBAG,radioButton2);
        cv.put(TrackingEyeball.VERTICAL_PARAMETER,radioButton3);
        cv.put(TrackingEyeball.RECORD_DATE,currentDateTimeString);
        cv.put(TrackingEyeball.TESTER,TesterName);
        long k = SQ.insert(TrackingEyeball.TABLE_NAME, null, cv);
        Log.d(TrackingEyeball.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }

    public void insertStudentSkippingDetails(DatabaseOperations dop, String studId,String radioButton1, String TesterName )
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TrackingSkipping.USER_StudID,studId);
        cv.put(TrackingSkipping.PARAMETER, radioButton1);
        cv.put(TrackingSkipping.RECORD_DATE,currentDateTimeString);
        cv.put(TrackingSkipping.TESTER,TesterName);

        long k = SQ.insert(TrackingSkipping.TABLE_NAME, null, cv);
        Log.d(TrackingSkipping.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }

    public void insertStudentTeamingDetails(DatabaseOperations dop, String studId,String Radiobuttonteaming1,String Radiobuttonteaming2, String TesterName)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TrackingTeaming.USER_StudID,studId);
        cv.put(TrackingTeaming.ROUND1, Radiobuttonteaming1);
        cv.put(TrackingTeaming.ROUND2, Radiobuttonteaming2);
        cv.put(TrackingTeaming.RECORD_DATE, currentDateTimeString);
        cv.put(TrackingTeaming.TESTER,TesterName);

        long k = SQ.insert(TrackingTeaming.TABLE_NAME, null, cv);
        Log.d(TrackingTeaming.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }

    public void insertStudentVDDetails(DatabaseOperations dop, String studId,String Radiobuttonvd, String TesterName)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TrackingVisualDiscrimination.USER_StudID,studId);
        cv.put(TrackingVisualDiscrimination.PARAMETER, Radiobuttonvd);
        cv.put(TrackingVisualDiscrimination.RECORD_DATE, currentDateTimeString);
        cv.put(TrackingVisualDiscrimination.TESTER,TesterName);

        long k = SQ.insert(TrackingVisualDiscrimination.TABLE_NAME, null, cv);
        Log.d(TrackingVisualDiscrimination.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }

    public void insertStudentCrawlingDetails(DatabaseOperations dop, String studId,String Crawlingtext, String TesterName)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TrackingCrawling.USER_StudID,studId);
        cv.put(TrackingCrawling.PARAMETER, Crawlingtext);
        cv.put(TrackingCrawling.RECORD_DATE, currentDateTimeString);
        cv.put(TrackingCrawling.TESTER,TesterName);
        long k = SQ.insert(TrackingCrawling.TABLE_NAME, null, cv);
        Log.d(TrackingCrawling.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }

    public void insertStudentComments(DatabaseOperations dop, String studId,String comments, String TesterName)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TrackingComments.USER_StudID,studId);
        cv.put(TrackingComments.COMMENTS, comments);
        cv.put(TrackingComments.RECORD_DATE,currentDateTimeString);
        cv.put(TrackingComments.TESTER,TesterName);

        long k = SQ.insert(TrackingComments.TABLE_NAME, null, cv);
        Log.d(TrackingComments.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }

    public void insertStudentBalancingDetails(DatabaseOperations dop, String studId,String time1,String time2,String time3, String time4, String TesterName)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TrackingBalancing.USER_StudID,studId);
        cv.put(TrackingBalancing.EYE_OPEN_RIGHT, time1);
        cv.put(TrackingBalancing.EYE_OPEN_LEFT,time2);
        cv.put(TrackingBalancing.EYE_CLOSE_RIGHT,time3);
        cv.put(TrackingBalancing.EYE_CLOSE_LEFT,time4);
        cv.put(TrackingBalancing.RECORD_DATE,currentDateTimeString);
        cv.put(TrackingBalancing.TESTER,TesterName);

        long k = SQ.insert(TrackingBalancing.TABLE_NAME, null, cv);
        Log.d(TrackingBalancing.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }

    public Cursor checkifRecordedToday(DatabaseOperations dop, String Sid){

        String query = null;
        query = "SELECT * FROM " + TrackingEyeball.TABLE_NAME + " where date(" + TrackingEyeball.RECORD_DATE + ") = date('" + currentDateTimeString + "') AND " +
            TrackingEyeball.USER_StudID + " = '" + Sid + "' ;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;
    }


    public void register_student(DatabaseOperations dop,String struser_cname,String struser_tname,String struser_age,String struser_dos,String struser_school,String struser_grade, String TesterName)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Student.USER_CNAME, struser_cname);
        cv.put(Student.USER_TNAME, struser_tname);
        cv.put(Student.USER_AGE, struser_age);
        cv.put(Student.USER_DOS, struser_dos);
        cv.put(Student.USER_GRADE, struser_grade);
        cv.put(Student.USER_SCHOOL, struser_school);
        cv.put(Student.TESTER,TesterName);

        long k = SQ.insertOrThrow(Student.TABLE_NAME, null, cv);
        Log.d(Student.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }

    public Cursor isValidUser(DatabaseOperations dop)
    {
        String query = null;
        query = "SELECT * FROM " + TableData.TableInfo.TABLE_NAME + " ;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;
    }

    public Cursor getData(DatabaseOperations dop)
    {
        String query = null;
        query = "SELECT * FROM " + Student.TABLE_NAME + " ORDER BY " + Student.USER_CNAME + " ASC, " + Student.USER_SCHOOL
                   + " ASC, " + Student.USER_GRADE + " ASC ;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }

//   SELECT r.*, te.*,  FROM reg_info r
//INNER JOIN tracking_eyeball te ON r.Student_ID = te.StudentID
//    INNER JOIN balancing b ON r.Student_ID = b.StudentID
//    INNER JOIN tracking_skipping ts ON r.Student_ID = ts.StudentID
//    INNER JOIN crawling c ON r.Student_ID = c.StudentID
//    INNER JOIN teaming t ON r.Student_ID = t.StudentID
//    INNER JOIN visualdiscrimination vd ON r.Student_ID = vd.StudentID
//    WHERE te.RecordDate = '2016-05-11' ;
    public Cursor getAllData(DatabaseOperations dop, String spinnervalue)
    {

        String query = null;
//        query = "SELECT r.*,te.*,b.*,ts.*,c.*,t.*,vd.*,cm.* FROM " + Student.TABLE_NAME + " r INNER JOIN " + TrackingEyeball.TABLE_NAME + " te ON r." +
//                Student.USER_StudID + " = te." + TrackingEyeball.USER_StudID +
//                " INNER JOIN " + TrackingBalancing.TABLE_NAME + " b ON r." +
//                Student.USER_StudID + " = b." + TrackingBalancing.USER_StudID +
//                " INNER JOIN " + TrackingSkipping.TABLE_NAME + " ts ON r." +
//                Student.USER_StudID + " = ts." + TrackingSkipping.USER_StudID +
//                " INNER JOIN " + TrackingCrawling.TABLE_NAME + " c ON r." +
//                Student.USER_StudID + " = c." + TrackingCrawling.USER_StudID +
//                " INNER JOIN " + TrackingTeaming.TABLE_NAME + " t ON r." +
//                Student.USER_StudID + " = t." + TrackingTeaming.USER_StudID +
//                " INNER JOIN " + TrackingVisualDiscrimination.TABLE_NAME + " vd ON r." +
//                Student.USER_StudID + " = vd." + TrackingVisualDiscrimination.USER_StudID +
//                " INNER JOIN " + TrackingComments.TABLE_NAME + " cm ON r." +
//                Student.USER_StudID + " = cm." + TrackingComments.USER_StudID +
//                " WHERE date(te." + TrackingEyeball.RECORD_DATE + ") = '" + spinnervalue + "' ;";
        query = "SELECT r.*,te.*,b.*,ts.*,c.*,t.*,vd.*,cm.* FROM " + Student.TABLE_NAME + " r INNER JOIN " + TrackingEyeball.TABLE_NAME + " te ON r." +
                Student.USER_StudID + " = te." + TrackingEyeball.USER_StudID +
                " INNER JOIN " + TrackingBalancing.TABLE_NAME + " b ON te." +
                TrackingEyeball.RECORD_DATE + " = b." + TrackingBalancing.RECORD_DATE + " AND te." + TrackingEyeball.USER_StudID + " = b." + TrackingBalancing.USER_StudID +
                " AND te." + TrackingEyeball.RECORD_DATE + " >= Datetime ('" + spinnervalue + " 00:00:00') AND te." +
                TrackingEyeball.RECORD_DATE + " <= Datetime ('" + spinnervalue + " 23:59:59') " +
                " INNER JOIN " + TrackingSkipping.TABLE_NAME + " ts ON te." +
                TrackingEyeball.RECORD_DATE + " = ts." + TrackingSkipping.RECORD_DATE + " AND te." + TrackingEyeball.USER_StudID + " = ts." + TrackingSkipping.USER_StudID +
                " AND te." + TrackingEyeball.RECORD_DATE + " >= Datetime ('" + spinnervalue + " 00:00:00') AND te." +
                TrackingEyeball.RECORD_DATE + " <= Datetime ('" + spinnervalue + " 23:59:59') " +
                " INNER JOIN " + TrackingCrawling.TABLE_NAME + " c ON te." +
                 TrackingEyeball.RECORD_DATE + " = c." + TrackingCrawling.RECORD_DATE + " AND te." + TrackingEyeball.USER_StudID + " = c." + TrackingCrawling.USER_StudID +
                " AND te." + TrackingEyeball.RECORD_DATE + " >= Datetime ('" + spinnervalue + " 00:00:00') AND te." +
                TrackingEyeball.RECORD_DATE + " <= Datetime ('" + spinnervalue + " 23:59:59') " +
                " INNER JOIN " + TrackingTeaming.TABLE_NAME + " t ON te." +
                TrackingEyeball.RECORD_DATE + " = t." + TrackingTeaming.RECORD_DATE + " AND te." + TrackingEyeball.USER_StudID + " = t." + TrackingTeaming.USER_StudID +
                " AND te." + TrackingEyeball.RECORD_DATE + " >= Datetime ('" + spinnervalue + " 00:00:00') AND te." +
                TrackingEyeball.RECORD_DATE + " <= Datetime ('" + spinnervalue + " 23:59:59') " +
                " INNER JOIN " + TrackingVisualDiscrimination.TABLE_NAME + " vd ON te." +
                TrackingEyeball.RECORD_DATE + " = vd." + TrackingVisualDiscrimination.RECORD_DATE + " AND te." + TrackingEyeball.USER_StudID + " = vd." + TrackingVisualDiscrimination.USER_StudID +
                " AND te." + TrackingEyeball.RECORD_DATE + " >= Datetime ('" + spinnervalue + " 00:00:00') AND te." +
                TrackingEyeball.RECORD_DATE + " <= Datetime ('" + spinnervalue + " 23:59:59') " +
                " INNER JOIN " + TrackingComments.TABLE_NAME + " cm ON te." +
                TrackingEyeball.RECORD_DATE + " = cm." + TrackingComments.RECORD_DATE + " AND te." + TrackingEyeball.USER_StudID + " = cm." + TrackingComments.USER_StudID +
                " AND te." + TrackingEyeball.RECORD_DATE + " >= Datetime ('" + spinnervalue + " 00:00:00') AND te." +
                TrackingEyeball.RECORD_DATE + " <= Datetime ('" + spinnervalue + " 23:59:59'); ";




                SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }

    public Cursor getSchoolNames(DatabaseOperations dop, String studname)
    {
        String query = null;
        query = "SELECT DISTINCT " + Student.USER_SCHOOL + " FROM " + Student.TABLE_NAME + " WHERE " + Student.USER_CNAME + " = '" + studname + "' ;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }

    public Cursor getStudentNumber(DatabaseOperations dop, String studname)
    {
        String query = null;
        query = "SELECT  " + Student.USER_StudID + " FROM " + Student.TABLE_NAME + " WHERE " + Student.USER_CNAME + " = '" + studname + "' ;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }

    public List<String> getAllDates(DatabaseOperations dop)
    {
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String query = "SELECT DISTINCT date(" + TrackingEyeball.RECORD_DATE  + ") FROM " + TrackingEyeball.TABLE_NAME + " ;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        Date date = new Date();
//        String currentDateTimeString = dateFormat.format(date);
//        String string = "";
//        SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//        Date date = format.parse(currentDateTimeString);
//        System.out.println(date); //
//        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
//                if(cursor.getString(0)) {
                    labels.add(cursor.getString(0));
               // }
            } while (cursor.moveToNext());
        }

        return labels;
    }


    public List<String> getAllSchoolNames(DatabaseOperations dop){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT " + Student.USER_SCHOOL + " FROM " + Student.TABLE_NAME + " ;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
//        cursor.close();
//        db.close();

        // returning lables

        labels.add("Other");
        return labels;

    }


    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }

}
