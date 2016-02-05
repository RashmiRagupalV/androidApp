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
    public static final int database_version = 10;
    public String CREATE_QUERY_LOGIN = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME +
            "(" + TableData.TableInfo.USER_NAME + " TEXT," +
            TableData.TableInfo.USER_PASS + " TEXT," +
            TableData.TableInfo.USER_TYPE + " TEXT );";
    public String CREATE_QUERY_REGISTER = "CREATE TABLE " + TableData.RegisterInfo.TABLE_NAME +
            "(" + TableData.RegisterInfo.USER_FNAME + " TEXT," +
            TableData.RegisterInfo.USER_LNAME + " TEXT," +
            TableData.RegisterInfo.USER_EMAIl + " TEXT," +
            TableData.RegisterInfo.USER_AGE + " TEXT," +
            TableData.RegisterInfo.USER_SCHOOL + " TEXT," +
            TableData.RegisterInfo.USER_CLASS + " TEXT, " +
            TableData.RegisterInfo.USER_GUARDIAN + " TEXT );";
    public static final String DATABASE_NAME = "redflag.db";

    public DatabaseOperations(Context context) {
        super(context, DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created !");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY_LOGIN);
        db.execSQL(CREATE_QUERY_REGISTER);
        insert_user_admin(db);
        Log.d("Database operations", "Tables created !");
        // I can insert some values in the oncreate for the admin table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS " + CREATE_QUERY);

        // Create tables again
//        db.execSQL(CREATE_QUERY_LOGIN);
//        db.execSQL(CREATE_QUERY_REGISTER);
//        Log.d("Database operations", "Tables created !");
 //       insert_user_admin(db);
        onCreate(db);

    }

    /*public void putInformation(DatabaseOperations dop, String name, String pass)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME, name);
        cv.put(TableData.TableInfo.USER_PASS, pass);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);
        Log.d("Database operations","One row inserted");

    }*/

    public void insert_user_admin(SQLiteDatabase dop)
    {
       // SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME, "Rashmi");
        cv.put(TableData.TableInfo.USER_PASS, "rashmi");
        cv.put(TableData.TableInfo.USER_TYPE, "Admin");
        long k = dop.insert(TableData.TableInfo.TABLE_NAME, null, cv);
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

    public Cursor isValidUser(DatabaseOperations dop, String struser_uname, String struser_pwd, String is_admin)
    {
        String query = null;
        query = "SELECT * FROM " + TableData.TableInfo.TABLE_NAME + " WHERE "
                    + TableData.TableInfo.USER_NAME + " = '" + struser_uname + "' AND "
                    + TableData.TableInfo.USER_PASS + " = '" + struser_pwd + "' AND "
                    + TableData.TableInfo.USER_TYPE + " = '" + is_admin + "'";


      //  = database.rawQuery(query,null);

        /*if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;*/
        SQLiteDatabase SQ = dop.getWritableDatabase();
        String[] columns = new String[3];

        Cursor cr = SQ.rawQuery(query, null);
        Log.d("Database operations", "rows retrieved");
       // SQ.close();
        return cr;
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
