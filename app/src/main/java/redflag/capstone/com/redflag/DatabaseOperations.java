package redflag.capstone.com.redflag;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.MatrixCursor;
import java.util.ArrayList;
import android.database.Cursor;
/**
 * Created by rashm on 1/10/2016.
 */
public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version = 4;
    public String CREATE_QUERY = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME + "(" + "User Name" + " TEXT," + "Password" + " TEXT );";
    public String CREATE_QUERY_REGISTER = "CREATE TABLE " + TableData.RegisterInfo.TABLE_NAME + "(" + TableData.RegisterInfo.USER_FNAME + " TEXT," + TableData.RegisterInfo.USER_LNAME + " TEXT," + TableData.RegisterInfo.USER_EMAIl + " TEXT," + TableData.RegisterInfo.USER_AGE + " TEXT," + TableData.RegisterInfo.USER_SCHOOL + " TEXT," + TableData.RegisterInfo.USER_CLASS + " TEXT, " + TableData.RegisterInfo.USER_GUARDIAN + " TEXT );";
    public static final String DATABASE_NAME = "user_info";

    public DatabaseOperations(Context context) {
        super(context, DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created !");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      //  db.execSQL(CREATE_QUERY);
      //  db.execSQL(CREATE_QUERY_REGISTER);
        Log.d("Database operations", "Tables created !");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS " + CREATE_QUERY);
        db.execSQL(CREATE_QUERY_REGISTER);
        // Create tables again
        onCreate(db);

    }

    public void putInformation(DatabaseOperations dop, String name, String pass)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME, name);
        cv.put(TableData.TableInfo.USER_PASS, pass);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);
        Log.d("Database operations","One row inserted");

    }

    public void register_student(DatabaseOperations dop, String struser_fname, String struser_lname,String struser_email, String struser_age,String struser_school, String struser_class, String struser_guardian)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.RegisterInfo.USER_FNAME, struser_fname);
        cv.put(TableData.RegisterInfo.USER_LNAME, struser_lname);
        cv.put(TableData.RegisterInfo.USER_EMAIl, struser_email);
        cv.put(TableData.RegisterInfo.USER_AGE, struser_age);
        cv.put(TableData.RegisterInfo.USER_SCHOOL, struser_school);
        cv.put(TableData.RegisterInfo.USER_CLASS, struser_class);
        cv.put(TableData.RegisterInfo.USER_GUARDIAN, struser_guardian);
        long k = SQ.insert(TableData.RegisterInfo.TABLE_NAME, null, cv);
        Log.d(TableData.RegisterInfo.TABLE_NAME,"done");
        Log.d("Database operations","One row inserted");
        SQ.close();
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
