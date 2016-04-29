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

import android.database.Cursor;
/**
 * Created by rashm on 1/10/2016.
 */
public class DatabaseOperations extends SQLiteOpenHelper {

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
    String currentDateTimeString = dateFormat.getDateTimeInstance().format(new Date());

    public static final int database_version = 16;
    public String CREATE_QUERY_LOGIN = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME +
            "(" + TableData.TableInfo.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + TableData.TableInfo.USER_NAME + " TEXT," +
            TableData.TableInfo.USER_PASS + " TEXT," +
            TableData.TableInfo.USER_TYPE + " TEXT );";

    public String CREATE_QUERY_REGISTER = "CREATE TABLE " + Student.TABLE_NAME +
            "(" + Student.USER_StudID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + Student.USER_CNAME + " TEXT NOT NULL," +
            Student.USER_TNAME + " TEXT," +
            Student.USER_AGE + " TEXT," +
            Student.USER_DOS + " TEXT," +
            Student.USER_GRADE + " TEXT NOT NULL," +
            Student.USER_SCHOOL + " TEXT NOT NULL," +
            " UNIQUE ( " + Student.USER_CNAME + "," + Student.USER_GRADE + "," + Student.USER_SCHOOL + "));";

    public String CREATE_QUERY_TRACKING_EYEBALL = "CREATE TABLE " + TrackingEyeball.TABLE_NAME +
            "(" + TrackingEyeball.USER_StudID + " INTEGER," + TrackingEyeball.RECORD_DATE + " TEXT," + TrackingEyeball.HORIZONTAL_PARAMETER + " TEXT," +
            TrackingEyeball.NEED_BEANBAG + " TEXT," + TrackingEyeball.VERTICAL_PARAMETER + " TEXT," +
            " FOREIGN KEY(" + TrackingEyeball.USER_StudID + ") REFERENCES " + Student.TABLE_NAME + "(" +
            Student.USER_StudID + "));";

    public String CREATE_QUERY_BALANCING = "CREATE TABLE " + TrackingBalancing.TABLE_NAME +
            "(" + TrackingEyeball.USER_StudID + " INTEGER," + TrackingBalancing.RECORD_DATE + " TEXT," + TrackingBalancing.EYE_OPEN_RIGHT + " TEXT," +
            TrackingBalancing.EYE_OPEN_LEFT + " TEXT," + TrackingBalancing.EYE_CLOSE_RIGHT + " TEXT," +
            TrackingBalancing.EYE_CLOSE_LEFT + " TEXT," +
            " FOREIGN KEY(" + TrackingBalancing.USER_StudID + ") REFERENCES " + Student.TABLE_NAME + "(" +
            Student.USER_StudID + "));";

    public String CREATE_QUERY_SKIPPING = "CREATE TABLE " + TrackingSkipping.TABLE_NAME +
            "(" + TrackingSkipping.USER_StudID + " INTEGER," + TrackingSkipping.RECORD_DATE + " TEXT," +
            TrackingSkipping.PARAMETER + " TEXT," +
            " FOREIGN KEY(" + TrackingSkipping.USER_StudID + ") REFERENCES " + Student.TABLE_NAME + "(" +
            Student.USER_StudID + "));";

    public String CREATE_QUERY_COMMENTS = "CREATE TABLE " + TrackingSkipping.TABLE_NAME +
            "(" + TrackingSkipping.USER_StudID + " INTEGER," + TrackingSkipping.RECORD_DATE + " TEXT," +
            TrackingSkipping.PARAMETER + " TEXT," +
            " FOREIGN KEY(" + TrackingSkipping.USER_StudID + ") REFERENCES " + Student.TABLE_NAME + "(" +
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
        db.execSQL("DROP TABLE IF EXISTS " + TrackingSkipping.TABLE_NAME);
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
        query = "SELECT * FROM " + TrackingEyeball.TABLE_NAME + " where " + TrackingEyeball.USER_StudID + " = '" + selectedstudId + "' ;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

    }


    public void insertStudentTrackingDetails(DatabaseOperations dop, String studId,String radioButton1,String radioButton2,String radioButton3)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TrackingEyeball.USER_StudID,studId);
        cv.put(TrackingEyeball.HORIZONTAL_PARAMETER, radioButton1);
        cv.put(TrackingEyeball.NEED_BEANBAG,radioButton2);
        cv.put(TrackingEyeball.VERTICAL_PARAMETER,radioButton3);
        cv.put(TrackingEyeball.RECORD_DATE,currentDateTimeString);
        long k = SQ.insert(TrackingEyeball.TABLE_NAME, null, cv);
        Log.d(TrackingEyeball.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }

    public void insertStudentSkippingDetails(DatabaseOperations dop, String studId,String radioButton1)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TrackingSkipping.USER_StudID,studId);
        cv.put(TrackingSkipping.PARAMETER, radioButton1);
        cv.put(TrackingSkipping.RECORD_DATE,currentDateTimeString);
        long k = SQ.insert(TrackingSkipping.TABLE_NAME, null, cv);
        Log.d(TrackingSkipping.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }

    public void insertStudentComments(DatabaseOperations dop, String studId,String comments)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TrackingComments.USER_StudID,studId);
        cv.put(TrackingComments.COMMENTS, comments);
        cv.put(TrackingComments.RECORD_DATE,currentDateTimeString);
        long k = SQ.insert(TrackingComments.TABLE_NAME, null, cv);
        Log.d(TrackingComments.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }

    public void insertStudentBalancingDetails(DatabaseOperations dop, String studId,String time1,String time2,String time3, String time4)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TrackingBalancing.USER_StudID,studId);
        cv.put(TrackingBalancing.EYE_OPEN_RIGHT, time1);
        cv.put(TrackingBalancing.EYE_OPEN_LEFT,time2);
        cv.put(TrackingBalancing.EYE_CLOSE_RIGHT,time3);
        cv.put(TrackingBalancing.EYE_CLOSE_LEFT,time3);
        cv.put(TrackingEyeball.RECORD_DATE,currentDateTimeString);
        long k = SQ.insert(TrackingBalancing.TABLE_NAME, null, cv);
        Log.d(TrackingBalancing.TABLE_NAME, "done");
        Log.d("Database operations", "One row inserted");
        SQ.close();
    }


    public void register_student(DatabaseOperations dop,String struser_cname,String struser_tname,String struser_age,String struser_dos,String struser_school,String struser_grade)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Student.USER_CNAME, struser_cname);
        cv.put(Student.USER_TNAME, struser_tname);
        cv.put(Student.USER_AGE, struser_age);
        cv.put(Student.USER_DOS, struser_dos);
        cv.put(Student.USER_GRADE, struser_grade);
        cv.put(Student.USER_SCHOOL, struser_school);

        long k = SQ.insert(Student.TABLE_NAME, null, cv);
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
        query = "SELECT * FROM " + Student.TABLE_NAME + " ;";

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
        query = "SELECT " + Student.USER_StudID + " FROM " + Student.TABLE_NAME + " WHERE " + Student.USER_CNAME + " = '" + studname + "' ;";

        SQLiteDatabase SQ = dop.getReadableDatabase();
        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
        return cr;

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
