package redflag.capstone.com.redflag.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Anshu on 11/1/2014.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = DatabaseHelper.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "screening_records.db";

    // Table Names
    public static final String TABLE_STUDENT = "student";
    private static final String TABLE_GRADES = "grades";
    private static final String TABLE_SCHOOL = "school";
    private static final String TABLE_CLASS_INFO = "class_info";
    private static final String TABLE_CLASSROOM = "classroom";
    private static final String TABLE_PARENTS = "parents";
    private static final String TABLE_SCREENER = "screener";
    private static final String TABLE_ADDRESS = "address";
    private static final String TABLE_STATE_INFO = "state_info";
    private static final String TABLE_RECORDS = "records";
    private static final String TABLE_SCREENING_TEST = "screening_test";

    // STUDENT Table - column names
    public static final String KEY_STUDENT_ID = "studentID";
    public static final String KEY_STUDENT_PARENT_ID = "parentID";
    public static final String KEY_STUDENT_FIRST_NAME = "firstName";
    public static final String KEY_STUDENT_MIDDLE_NAME = "middleName";
    public static final String KEY_STUDENT_LAST_NAME = "lastName";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_DOB = "dateOfBirth";
    public static final String KEY_AGE_GROUP = "ageGroup";
    private static final String KEY_STUDENT_ADDRESS_ID = "addressID";
    private static final String KEY_USE_GLASSES = "useGlasses";
    private static final String KEY_STUDENT_RECORD_ID = "recordID";
    public static final String KEY_STUDENT_SCHOOL_ID = "schoolID";
    public static final String KEY_STUDENT_GRADE = "grade";
    public static final String KEY_STUDENT_CLASS = "class";

    // GRADES Table - column names
    private static final String KEY_GRADE = "grade";

    // SCHOOL Table - column names
    private static final String KEY_SCHOOL_ID = "schoolID";
    private static final String KEY_SCHOOL_NAME = "schoolName";
    private static final String KEY_SCHOOL_ADDRESS_ID = "addressID";

    // CLASS_INFO Table - column names
    private static final String KEY_CLASS = "class";
    private static final String KEY_CLASS_INFO_STUDENT_ID = "studentID";

    // CLASSROOM Table - column names
    private static final String KEY_CLASSROOM_ID = "classRoomID";
    private static final String KEY_CLASSROOM_GRADE = "grade";
    private static final String KEY_SECTION = "section";

    // PARENTS Table - column names
    private static final String KEY_PARENT_ID = "parentID";
    private static final String KEY_PARENTS_STUDENT_ID = "studentID";
    private static final String KEY_FATHER_NAME = "fatherName";
    private static final String KEY_MOTHER_NAME = "motherName";
    private static final String KEY_FATHER_CONTACT = "fatherContact";
    private static final String KEY_MOTHER_CONTACT = "motherContact";
    private static final String KEY_PARENTS_EMAIL_ADDRESS = "emailAddress";
    private static final String KEY_PARENTS_ADDRESS_ID = "addressID";

    // SCREENER Table - column names
    private static final String KEY_SCREENER_ID = "screenerID";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_MIDDLE_NAME = "middleName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_CONTACT = "contact";
    private static final String KEY_EMAIL_ADDRESS = "emailAddress";
    private static final String KEY_SCREENER_ADDRESS_ID = "addressID";

    // ADDRESS Table - column names
    private static final String KEY_ADDRESS_ID = "addressID";
    private static final String KEY_STREET_ADDRESS = "streetAddress";
    private static final String KEY_CITY = "city";
    private static final String KEY_ADDRESS_STATE_CODE = "stateCode";
    private static final String KEY_ZIP_CODE = "zipCode";

    // STATE_INFO Table - column names
    private static final String KEY_STATE_CODE = "stateCode";
    private static final String KEY_STATE_NAME = "stateName";

    // RECORDS Table - column names
    private static final String KEY_RECORD_ID = "recordID";
    private static final String KEY_RECORDS_STUDENT_ID = "studentID";
    private static final String KEY_RECORDS_TEST_ID = "testID";
    private static final String KEY_LAST_MODIFIED_DATE = "dateModified";
    private static final String KEY_LAST_MODIFIED_TIME = "timeModified";

    // SCREENING_TEST Table - column names
    private static final String KEY_TEST_ID = "testID";
    private static final String KEY_SCREENING_TEST_RECORD_ID = "recordID";
    private static final String KEY_SCREENING_TEST_SCREENER_ID = "screenerID";
    private static final String KEY_DATE_CONDUCTED = "dateConducted";
    private static final String KEY_SANDBAG = "sandbag";
    private static final String KEY_TRACKING_HORIZONTAL = "trackingHorizontal";
    private static final String KEY_TRACKING_VERTICAL = "trackingVertical";
    private static final String KEY_BALANCE_EYES_OPEN_RIGHT_FOOT = "balanceEyesOpenRightFoot";
    private static final String KEY_BALANCE_EYES_OPEN_LEFT_FOOT = "balanceEyesOpenLeftFoot";
    private static final String KEY_BALANCE_EYES_CLOSE_RIGHT_FOOT = "balanceEyesCloseRightFoot";
    private static final String KEY_BALANCE_EYES_CLOSE_LEFT_FOOT = "balanceEyesCloseLeftFoot";
    private static final String KEY_TEAMING_ROUND1 = "teamingRound1";
    private static final String KEY_TEAMING_ROUND2 = "teamingRound2";
    private static final String KEY_SKIPPING = "skipping";
    private static final String KEY_CRAWLING = "crawling";
    private static final String KEY_SCORES = "scores";


    // Table Create Statements
    // student table create statement
   /* private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + " ("
           // + KEY_STUDENT_ID + " INT PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_STUDENT_PARENT_ID + " INT NOT NULL, "
            + KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_STUDENT_PARENT_ID + " INT NULL, "
           // + KEY_STUDENT_RECORD_ID + " INT NOT NULL, " + KEY_STUDENT_FIRST_NAME + " TEXT NOT NULL,"
            + KEY_STUDENT_RECORD_ID + " INT NULL, " + KEY_STUDENT_FIRST_NAME + " TEXT NOT NULL,"
           // + KEY_STUDENT_MIDDLE_NAME + " TEXT NULL," + KEY_STUDENT_LAST_NAME + " TEXT NOT NULL, " + KEY_GENDER
            + KEY_STUDENT_MIDDLE_NAME + " TEXT NULL," + KEY_STUDENT_LAST_NAME + " TEXT NOT NULL, " + KEY_GENDER
           // + " TEXT NOT NULL," + KEY_DOB + " NUMERIC NOT NULL, " + KEY_AGE_GROUP + " TEXT  NOT NULL," +
            + " TEXT NOT NULL," + KEY_DOB + " NUMERIC NULL, " + KEY_AGE_GROUP + " TEXT  NOT NULL," +
           // KEY_STUDENT_ADDRESS_ID + " INT NOT NULL, " + KEY_USE_GLASSES + " TEXT  NOT NULL," + KEY_STUDENT_SCHOOL_ID +
            KEY_STUDENT_ADDRESS_ID + " INT NULL, " + KEY_USE_GLASSES + " TEXT  NULL," + KEY_STUDENT_SCHOOL_ID +
           // " INT  NOT NULL," + KEY_STUDENT_GRADE + " INT  NOT NULL," + KEY_STUDENT_CLASS + " TEXT  NOT NULL," +
            " INT  NOT NULL," + KEY_STUDENT_GRADE + " INT  NULL," + KEY_STUDENT_CLASS + " TEXT  NULL" +
           // " FOREIGN KEY(" + KEY_STUDENT_PARENT_ID + ")" + " REFERENCES  PARENTS(" + KEY_PARENT_ID + "),"
           // + " FOREIGN  KEY(" + KEY_STUDENT_GRADE + ")" + " REFERENCES  GRADES(" + KEY_GRADE + ")," +
           // " FOREIGN  KEY(" + KEY_STUDENT_CLASS + ") REFERENCES CLASS_INFO(" + KEY_CLASS + ")," +
           // " FOREIGN  KEY(" + KEY_STUDENT_SCHOOL_ID + ")" + " REFERENCES  SCHOOL(" + KEY_SCHOOL_ID + ")," +
           // " FOREIGN  KEY(" + KEY_STUDENT_RECORD_ID + ")" + " REFERENCES  RECORDS(" + KEY_RECORD_ID + ")" + ")";
            ")";*/

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + " ("
            + KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_STUDENT_PARENT_ID + " INT NULL, "
            + KEY_STUDENT_RECORD_ID + " INT NULL, " + KEY_STUDENT_FIRST_NAME + " TEXT NOT NULL,"
            + KEY_STUDENT_MIDDLE_NAME + " TEXT NULL," + KEY_STUDENT_LAST_NAME + " TEXT NOT NULL, " + KEY_GENDER
            + " TEXT NOT NULL," + KEY_DOB + " NUMERIC NULL, " + KEY_AGE_GROUP + " TEXT  NOT NULL," +
            KEY_STUDENT_ADDRESS_ID + " INT NULL, " + KEY_USE_GLASSES + " TEXT  NULL," + KEY_STUDENT_SCHOOL_ID +
            " TEXT  NOT NULL," + KEY_STUDENT_GRADE + " INT  NULL," + KEY_STUDENT_CLASS + " TEXT  NULL" +
            ")";

    // grade table create statement
    private static final String CREATE_TABLE_GRADES = "CREATE TABLE " + TABLE_GRADES + "(" +
            KEY_GRADE + " INT  PRIMARY KEY NOT NULL" + ")";

    // school table create statement
    private static final String CREATE_TABLE_SCHOOL = "CREATE TABLE " + TABLE_SCHOOL + "(" +
            KEY_SCHOOL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_SCHOOL_NAME + " TEXT  NOT NULL," +
            KEY_SCHOOL_ADDRESS_ID + " INT  NOT NULL" + ")";

    // class_info table create statement
    private static final String CREATE_TABLE_CLASS_INFO = "CREATE TABLE " + TABLE_CLASS_INFO + "("
            + KEY_CLASS + " TEXT  PRIMARY KEY NOT NULL," + KEY_CLASS_INFO_STUDENT_ID + " INT  NOT NULL" + ")";

    // classroom table create statement
    private static final String CREATE_TABLE_CLASSROOM = "CREATE TABLE " + TABLE_CLASSROOM + "("
            + KEY_CLASSROOM_ID + " INT  PRIMARY KEY NOT NULL," + KEY_CLASSROOM_GRADE + " INT  NOT NULL," + KEY_SECTION +
            " TEXT  NULL" + ")";

    // parents table create statement
    private static final String CREATE_TABLE_PARENTS = "CREATE TABLE " + TABLE_PARENTS + "("
            + KEY_PARENT_ID + " INT  PRIMARY KEY NOT NULL," + KEY_PARENTS_STUDENT_ID + " INT  NOT NULL," + KEY_FATHER_NAME
            + " TEXT  NOT NULL," + KEY_MOTHER_NAME + " TEXT NOT NULL," + KEY_FATHER_CONTACT + " TEXT  NULL," +
            KEY_MOTHER_CONTACT + " TEXT  NULL," + KEY_PARENTS_EMAIL_ADDRESS + " TEXT  NOT NULL," + KEY_PARENTS_ADDRESS_ID +
            " INT  NOT NULL," + " FOREIGN  KEY(" + KEY_PARENTS_ADDRESS_ID + ") REFERENCES ADDRESS(" + KEY_ADDRESS_ID + ")" + ")";

    // screener table create statement
    private static final String CREATE_TABLE_SCREENER = "CREATE TABLE " + TABLE_SCREENER + "(" +
            KEY_SCREENER_ID + " INT  PRIMARY KEY NOT NULL," + KEY_FIRST_NAME + " TEXT  NOT NULL," + KEY_MIDDLE_NAME
            + " TEXT  NULL," + KEY_LAST_NAME + " TEXT  NOT NULL," + KEY_CONTACT + " TEXT  NULL," + KEY_EMAIL_ADDRESS +
            " TEXT NOT NULL," + KEY_SCREENER_ADDRESS_ID + " INT  NOT NULL," + " FOREIGN  KEY(" + KEY_SCREENER_ADDRESS_ID + ")" +
            " REFERENCES  ADDRESS(" + KEY_ADDRESS_ID + ")" + ")";

    // address table create statement
    private static final String CREATE_TABLE_ADDRESS = "CREATE TABLE " + TABLE_ADDRESS + "("
            + KEY_ADDRESS_ID + " INT  PRIMARY KEY NOT NULL," + KEY_STREET_ADDRESS + " TEXT  NOT NULL," + KEY_CITY +
            " TEXT  NOT NULL," + KEY_ADDRESS_STATE_CODE + " TEXT  NOT NULL," + KEY_ZIP_CODE + " INT  NULL," +
            " FOREIGN  KEY(" + KEY_ADDRESS_STATE_CODE + ") REFERENCES STATE_INFO(" + KEY_STATE_CODE + ")" + ")";

    // state_info table create statement
    private static final String CREATE_TABLE_STATE_INFO = "CREATE TABLE " + TABLE_STATE_INFO + "("
            + KEY_STATE_CODE + " TEXT  PRIMARY KEY NOT NULL," + KEY_STATE_NAME + " TEXT  NOT NULL" + ")";

    // records table create statement
    private static final String CREATE_TABLE_RECORDS = "CREATE TABLE " + TABLE_RECORDS + "(" +
            KEY_RECORD_ID + " INT  PRIMARY KEY NOT NULL," + KEY_RECORDS_STUDENT_ID + " INT  NOT NULL," + KEY_RECORDS_TEST_ID +
            " INT  NOT NULL," + KEY_LAST_MODIFIED_DATE + " NUMERIC NULL," + KEY_LAST_MODIFIED_TIME +
            " NUMERIC NULL," + " FOREIGN  KEY(" + KEY_RECORDS_STUDENT_ID + ") REFERENCES STUDENT(" + KEY_STUDENT_ID + ")," +
            " FOREIGN  KEY(" + KEY_RECORDS_TEST_ID + ") REFERENCES SCREENING_TEST(" + KEY_TEST_ID + ")" + ")";


    // screening_test table create statement
    private static final String CREATE_TABLE_SCREENING_TEST = "CREATE TABLE " + TABLE_SCREENING_TEST +
            "(" + KEY_TEST_ID + " INT  PRIMARY KEY NOT NULL," + KEY_SCREENING_TEST_RECORD_ID + " INT  NOT NULL," + KEY_SCREENING_TEST_SCREENER_ID +
            " INT  NOT NULL," + KEY_DATE_CONDUCTED + " NUMERIC NULL," + KEY_SANDBAG + " NUMERIC NULL,"
            + KEY_TRACKING_HORIZONTAL + " TEXT  NULL," + KEY_TRACKING_VERTICAL + " TEXT  NULL," +
            KEY_BALANCE_EYES_OPEN_RIGHT_FOOT + " NUMERIC NULL," + KEY_BALANCE_EYES_OPEN_LEFT_FOOT +
            " NUMERIC NULL," + KEY_BALANCE_EYES_CLOSE_RIGHT_FOOT + " NUMERIC NULL," +
            KEY_BALANCE_EYES_CLOSE_LEFT_FOOT + " NUMERIC NULL," + KEY_TEAMING_ROUND1 + " TEXT  NULL," +
            KEY_TEAMING_ROUND2 + " TEXT  NULL," + KEY_SKIPPING + " TEXT  NULL," + KEY_CRAWLING + " TEXT  NULL," +
            KEY_SCORES + " INT  NULL," + " FOREIGN  KEY(" + KEY_SCREENING_TEST_RECORD_ID + ") REFERENCES RECORDS(" + KEY_RECORD_ID + ")," +
            " FOREIGN  KEY(" + KEY_SCREENING_TEST_SCREENER_ID + ") REFERENCES SCREENER(" + KEY_SCREENER_ID + ")" + ")";

    public DatabaseHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        // Creating required tables
       /* db.execSQL(CREATE_TABLE_GRADES);
        db.execSQL(CREATE_TABLE_SCHOOL);
        db.execSQL(CREATE_TABLE_CLASSROOM);
        db.execSQL(CREATE_TABLE_STATE_INFO);
        db.execSQL(CREATE_TABLE_ADDRESS);
        db.execSQL(CREATE_TABLE_SCREENER);
        db.execSQL(CREATE_TABLE_PARENTS);
        db.execSQL(CREATE_TABLE_SCREENING_TEST);*/
        db.execSQL(CREATE_TABLE_STUDENT);
       /* db.execSQL(CREATE_TABLE_RECORDS);
        db.execSQL(CREATE_TABLE_CLASS_INFO);*/
        Log.v("Query", CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRADES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHOOL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCREENER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATE_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCREENING_TEST);

        // Create new tables
        onCreate(db);
    }

    public void InsertRecord(ContentValues content, String tableName) {
        //mExampleHelper = new ExampleSQLiteOpenHelper(this);
        //mExampleHelper.getWritableDatabase();
        SQLiteDatabase wdb = this.getWritableDatabase();
        long d = 0;

        try {
            d = wdb.insert(tableName, null, content);
        } catch (Exception e) {
            Log.v("DatabaseHelper", e.getMessage());
        }
        wdb.close(); // Closing database connection
    }

    public Cursor search(String searchText) {
        Cursor studentCursor;
        SQLiteDatabase db = this.getReadableDatabase();
        // || is the concatenation operation in SQLite

        studentCursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT + " WHERE " + KEY_STUDENT_FIRST_NAME + " LIKE '%" + searchText + "%'", null);

        // looping through all rows and adding to list
        if (studentCursor != null) {
            studentCursor.moveToFirst();
        }

        // return student list
        db.close();
        return studentCursor;
    }

    /********** CRUD operations for Address table *************/

    public Address getAddress(final String addressId) {
        Log.d("getAddress", addressId);
        final SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        final Cursor cursor = db.query(TABLE_ADDRESS,
                null, // Null ensures cursor returns all the columns
                " addressId = ?",
                new String[]{addressId},
                null, null, null, null);

        // Read result and build address object
        Address address = null;
        if (cursor != null && cursor.moveToFirst()) {
            address = new Address();
            address.setAddressId(cursor.getInt(cursor.getColumnIndex(KEY_SCHOOL_ID)));
            address.setStateCode(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_STATE_CODE)));
            address.setCity(cursor.getString(cursor.getColumnIndex(KEY_CITY)));
            address.setStreetAddress(cursor.getString(cursor.getColumnIndex(KEY_STREET_ADDRESS)));
            address.setZipCode(cursor.getInt(cursor.getColumnIndex(KEY_ZIP_CODE)));
        }

        // Close cursor
        Utils.closeCursor(cursor);
        return address;
    }


    public List<Address> getAllAddress() {
        final List<Address> addresses = new ArrayList<Address>();

        // Construct query
        String query = "SELECT  * FROM " + TABLE_ADDRESS;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);

        // Iterate over results and populate list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                final Address address = new Address();
                address.setAddressId(cursor.getInt(cursor.getColumnIndex(KEY_SCHOOL_ID)));
                address.setStateCode(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_STATE_CODE)));
                address.setCity(cursor.getString(cursor.getColumnIndex(KEY_CITY)));
                address.setStreetAddress(cursor.getString(cursor.getColumnIndex(KEY_STREET_ADDRESS)));
                address.setZipCode(cursor.getInt(cursor.getColumnIndex(KEY_ZIP_CODE)));

                addresses.add(address);
            } while (cursor.moveToNext());
        }

        Utils.closeCursor(cursor);
        return addresses;
    }

    public void addAddress(final Address address) {
        Log.d("addAddress", address.toString());

        // Create content values to insert in table
        final ContentValues values = new ContentValues();
        values.put(KEY_ADDRESS_ID, address.getAddressId());
        values.put(KEY_ADDRESS_STATE_CODE, address.getStateCode());
        values.put(KEY_CITY, address.getCity());
        values.put(KEY_ZIP_CODE, address.getZipCode());
        values.put(KEY_STREET_ADDRESS, address.getStreetAddress());

        // Insert
        final SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_ADDRESS, null, values);
        db.close();
    }

    public int updateAddress(final Address address) {
        Log.d("updateAddress", address.toString());

        // Create content values to update in table
        final ContentValues values = new ContentValues();
        values.put(KEY_ADDRESS_ID, address.getAddressId());
        values.put(KEY_ADDRESS_STATE_CODE, address.getStateCode());
        values.put(KEY_CITY, address.getCity());
        values.put(KEY_ZIP_CODE, address.getZipCode());
        values.put(KEY_STREET_ADDRESS, address.getStreetAddress());

        // Update
        final SQLiteDatabase db = this.getWritableDatabase();
        int numRows = db.update(TABLE_ADDRESS, values,
                KEY_ADDRESS_ID + " = ?",
                new String[] {String.valueOf(address.getAddressId())});

        db.close();

        return numRows;
    }

    public void deleteAddress(final Address address) {
        Log.d("deleteAddress", address.toString());
        // Delete
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ADDRESS, KEY_ADDRESS_ID + " = ?",
                new String[] {String.valueOf(address.getAddressId())});

        db.close();
    }
    /********** CRUD operations for Class table *************/

    public Class getClass(final String classInfo) {
        Log.d("getClass", classInfo);
        final SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        final Cursor cursor = db.query(TABLE_CLASS_INFO,
                null, // Null ensures cursor returns all the columns
                " classInfo = ?",
                new String[]{classInfo},
                null, null, null, null);

        // Read result and build class object
        Class classes = null;
        if (cursor != null && cursor.moveToFirst()) {
            classes = new Class();
            classes.setClassInfo(cursor.getString(cursor.getColumnIndex(KEY_CLASS)));
            classes.setStudentId(cursor.getInt(cursor.getColumnIndex(KEY_CLASS_INFO_STUDENT_ID)));
        }

        // Close cursor
        Utils.closeCursor(cursor);
        return classes;
    }


    public List<Class> getAllClass() {
        final List<Class> classs = new ArrayList<Class>();

        // Construct query
        String query = "SELECT  * FROM " + TABLE_CLASS_INFO;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);

        // Iterate over results and populate list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                final Class classes = new Class();
                classes.setClassInfo(cursor.getString(cursor.getColumnIndex(KEY_CLASS)));
                classes.setStudentId(cursor.getInt(cursor.getColumnIndex(KEY_CLASS_INFO_STUDENT_ID)));

                classs.add(classes);
            } while (cursor.moveToNext());
        }

        Utils.closeCursor(cursor);
        return classs;
    }

    public void addClass(final Class classes) {
        Log.d("addClass", classes.toString());

        // Create content values to insert in table
        final ContentValues values = new ContentValues();
        values.put(KEY_CLASS, classes.getClassInfo());
        values.put(KEY_CLASS_INFO_STUDENT_ID, classes.getStudentId());

        // Insert
        final SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CLASS_INFO, null, values);
        db.close();
    }

    public int updateClass(final Class classes) {
        Log.d("updateClass", classes.toString());

        // Create content values to update in table
        final ContentValues values = new ContentValues();
        values.put(KEY_CLASS, classes.getClassInfo());
        values.put(KEY_CLASS_INFO_STUDENT_ID, classes.getStudentId());

        // Update
        final SQLiteDatabase db = this.getWritableDatabase();
        int numRows = db.update(TABLE_CLASS_INFO, values,
                KEY_CLASS + " = ?",
                new String[] {String.valueOf(classes.getClassInfo())});

        db.close();

        return numRows;
    }

    public void deleteClass(final Class classes) {
        Log.d("deleteClass", classes.toString());
        // Delete
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLASS_INFO, KEY_CLASS + " = ?",
                new String[] {String.valueOf(classes.getClassInfo())});

        db.close();
    }

    /********** CRUD operations for Classroom table *************/

    public Classroom getClassroom(final String classroomId) {
        Log.d("getClassroom", classroomId);
        final SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        final Cursor cursor = db.query(TABLE_CLASSROOM,
                null, // Null ensures cursor returns all the columns
                " classroomId = ?",
                new String[]{classroomId},
                null, null, null, null);

        // Read result and build classroom object
        Classroom classroom = null;
        if (cursor != null && cursor.moveToFirst()) {
            classroom = new Classroom();
            classroom.setClassroomId(cursor.getInt(cursor.getColumnIndex(KEY_CLASSROOM_ID)));
            classroom.setGrade(cursor.getInt(cursor.getColumnIndex(KEY_CLASSROOM_GRADE)));
            classroom.setSection(cursor.getString(cursor.getColumnIndex(KEY_SECTION)));
        }

        // Close cursor
        Utils.closeCursor(cursor);
        return classroom;
    }


    public List<Classroom> getAllClassrooms() {
        final List<Classroom> classrooms = new ArrayList<Classroom>();

        // Construct query
        String query = "SELECT  * FROM " + TABLE_CLASSROOM;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);

        // Iterate over results and populate list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                final Classroom classroom = new Classroom();
                classroom.setClassroomId(cursor.getInt(cursor.getColumnIndex(KEY_CLASSROOM_ID)));
                classroom.setGrade(cursor.getInt(cursor.getColumnIndex(KEY_CLASSROOM_GRADE)));
                classroom.setSection(cursor.getString(cursor.getColumnIndex(KEY_SECTION)));
                classrooms.add(classroom);

            } while (cursor.moveToNext());
        }

        Utils.closeCursor(cursor);
        return classrooms;
    }

    public void addClassroom(final Classroom classroom) {
        Log.d("addClassroom", classroom.toString());

        // Create content values to insert in table
        final ContentValues values = new ContentValues();
        values.put(KEY_CLASSROOM_ID, classroom.getClassroomId());
        values.put(KEY_CLASSROOM_GRADE, classroom.getGrade());
        values.put(KEY_SECTION, classroom.getSection());

        // Insert
        final SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CLASSROOM, null, values);
        db.close();
    }

    public int updateClassroom(final Classroom classroom) {
        Log.d("updateClassroom", classroom.toString());

        // Create content values to update in table
        final ContentValues values = new ContentValues();
        values.put(KEY_CLASSROOM_ID, classroom.getClassroomId());
        values.put(KEY_CLASSROOM_GRADE, classroom.getGrade());
        values.put(KEY_SECTION, classroom.getSection());


        // Update
        final SQLiteDatabase db = this.getWritableDatabase();
        int numRows = db.update(TABLE_CLASSROOM, values,
                KEY_CLASSROOM_ID + " = ?",
                new String[] {String.valueOf(classroom.getClassroomId())});

        db.close();

        return numRows;
    }

    public void deleteClassroom(final Classroom classroom) {
        Log.d("deleteClassroom", classroom.toString());
        // Delete
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLASSROOM, KEY_CLASSROOM_ID + " = ?",
                new String[] {String.valueOf(classroom.getClassroomId())});

        db.close();
    }
    /********** CRUD operations for Grades table *************/

    public Grades getGrades(final String grade) {
        Log.d("getGrades", grade);
        final SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        final Cursor cursor = db.query(TABLE_GRADES,
                null, // Null ensures cursor returns all the columns
                " grade = ?",
                new String[]{grade},
                null, null, null, null);

        // Read result and build grades object
        Grades grades = null;
        if (cursor != null && cursor.moveToFirst()) {
            grades = new Grades();
            grades.setGrade(cursor.getInt(cursor.getColumnIndex(KEY_GRADE)));
        }

        // Close cursor
        Utils.closeCursor(cursor);
        return grades;
    }


    public List<Grades> getAllGrades() {
        final List<Grades> gradess = new ArrayList<Grades>();

        // Construct query
        String query = "SELECT  * FROM " + TABLE_GRADES;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);

        // Iterate over results and populate list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                final Grades grades = new Grades();
                grades.setGrade(cursor.getInt(cursor.getColumnIndex(KEY_GRADE)));
                gradess.add(grades);
            } while (cursor.moveToNext());
        }

        Utils.closeCursor(cursor);
        return gradess;
    }

    public void addGrades(final Grades grades) {
        Log.d("addGrades", grades.toString());

        // Create content values to insert in table
        final ContentValues values = new ContentValues();
        values.put(KEY_GRADE, grades.getGrade());

        // Insert
        final SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_GRADES, null, values);
        db.close();
    }

    public int updateGrades(final Grades grades) {
        Log.d("updateGrades", grades.toString());

        // Create content values to update in table
        final ContentValues values = new ContentValues();
        values.put(KEY_GRADE, grades.getGrade());

        // Update
        final SQLiteDatabase db = this.getWritableDatabase();
        int numRows = db.update(TABLE_GRADES, values,
                KEY_GRADE + " = ?",
                new String[] {String.valueOf(grades.getGrade())});

        db.close();

        return numRows;
    }

    public void deleteGrades(final Grades grades) {
        Log.d("deleteGrades", grades.toString());
        // Delete
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GRADES, KEY_GRADE + " = ?",
                new String[] {String.valueOf(grades.getGrade())});

        db.close();
    }
    /********** CRUD operations for Parents table *************/

    public Parents getParents(final String parentsId) {
        Log.d("getParents", parentsId);
        final SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        final Cursor cursor = db.query(TABLE_PARENTS,
                null,
                " parentsId = ?",
                new String[]{parentsId},
                null, null, null, null);

        // Read result and build parents object
        Parents parents = null;
        if (cursor != null && cursor.moveToFirst()) {
            parents = new Parents();
            parents.setParentId(cursor.getInt(cursor.getColumnIndex(KEY_PARENT_ID)));
            parents.setEmailAddress(cursor.getString(cursor.getColumnIndex(KEY_PARENTS_EMAIL_ADDRESS)));
            parents.setAddressId(cursor.getInt(cursor.getColumnIndex(KEY_PARENTS_ADDRESS_ID)));
            parents.setFatherName(cursor.getString(cursor.getColumnIndex(KEY_FATHER_NAME)));
            parents.setMotherName(cursor.getString(cursor.getColumnIndex(KEY_MOTHER_NAME)));
            parents.setFatherContact(cursor.getString(cursor.getColumnIndex(KEY_FATHER_CONTACT)));
            parents.setMotherContact(cursor.getString(cursor.getColumnIndex(KEY_MOTHER_CONTACT)));
            parents.setStudentId(cursor.getInt(cursor.getColumnIndex(KEY_PARENTS_STUDENT_ID)));
        }

        // Close cursor
        Utils.closeCursor(cursor);
        return parents;
    }


    public List<Parents> getAllParentss() {
        final List<Parents> parentss = new ArrayList<Parents>();

        // Construct query
        String query = "SELECT  * FROM " + TABLE_PARENTS;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);

        // Iterate over results and populate list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                final Parents parents = new Parents();
                parents.setParentId(cursor.getInt(cursor.getColumnIndex(KEY_PARENT_ID)));
                parents.setEmailAddress(cursor.getString(cursor.getColumnIndex(KEY_PARENTS_EMAIL_ADDRESS)));
                parents.setAddressId(cursor.getInt(cursor.getColumnIndex(KEY_PARENTS_ADDRESS_ID)));
                parents.setFatherName(cursor.getString(cursor.getColumnIndex(KEY_FATHER_NAME)));
                parents.setMotherName(cursor.getString(cursor.getColumnIndex(KEY_MOTHER_NAME)));
                parents.setFatherContact(cursor.getString(cursor.getColumnIndex(KEY_FATHER_CONTACT)));
                parents.setMotherContact(cursor.getString(cursor.getColumnIndex(KEY_MOTHER_CONTACT)));
                parents.setStudentId(cursor.getInt(cursor.getColumnIndex(KEY_PARENTS_STUDENT_ID)));

                parentss.add(parents);
            } while (cursor.moveToNext());
        }

        Utils.closeCursor(cursor);
        return parentss;
    }

    public void addParents(final Parents parents) {
        Log.d("addParents", parents.toString());

        // Create content values to insert in table
        final ContentValues values = new ContentValues();
        values.put(KEY_PARENT_ID, parents.getParentId());
        values.put(KEY_PARENTS_ADDRESS_ID, parents.getAddressId());
        values.put(KEY_PARENTS_EMAIL_ADDRESS, parents.getEmailAddress());
        values.put(KEY_PARENTS_STUDENT_ID, parents.getStudentId());
        values.put(KEY_FATHER_NAME, parents.getFatherName());
        values.put(KEY_FATHER_CONTACT, parents.getFatherContact());
        values.put(KEY_MOTHER_NAME, parents.getMotherName());
        values.put(KEY_MOTHER_CONTACT, parents.getMotherContact());

        // Insert
        final SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PARENTS, null, values);
        db.close();
    }

    public int updateParents(final Parents parents) {
        Log.d("updateParents", parents.toString());

        // Create content values to update in table
        final ContentValues values = new ContentValues();
        values.put(KEY_PARENT_ID, parents.getParentId());
        values.put(KEY_PARENTS_ADDRESS_ID, parents.getAddressId());
        values.put(KEY_PARENTS_EMAIL_ADDRESS, parents.getEmailAddress());
        values.put(KEY_PARENTS_STUDENT_ID, parents.getStudentId());
        values.put(KEY_FATHER_NAME, parents.getFatherName());
        values.put(KEY_FATHER_CONTACT, parents.getFatherContact());
        values.put(KEY_MOTHER_NAME, parents.getMotherName());
        values.put(KEY_MOTHER_CONTACT, parents.getMotherContact());

        // Update
        final SQLiteDatabase db = this.getWritableDatabase();
        int numRows = db.update(TABLE_PARENTS, values,
                KEY_PARENT_ID + " = ?",
                new String[] {String.valueOf(parents.getParentId())});

        db.close();

        return numRows;
    }

    public void deleteParents(final Parents parents) {
        Log.d("deleteParents", parents.toString());
        // Delete
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PARENTS, KEY_STATE_CODE + " = ?",
                new String[]{String.valueOf(parents.getParentId())});

        db.close();
    }

    /********** CRUD operations for Records table *************/

    public Records getRecords(final String recordsId) {
        Log.d("getRecords", recordsId);
        final SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        final Cursor cursor = db.query(TABLE_RECORDS,
                null, // Null ensures cursor returns all the columns
                " recordsId = ?",
                new String[]{recordsId},
                null, null, null, null);

        // Read result and build records object
        Records records = null;
        if (cursor != null && cursor.moveToFirst()) {
            records = new Records();
            records.setRecordId(cursor.getInt(cursor.getColumnIndex(KEY_RECORD_ID)));
            records.setStudentId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_ID)));
            records.setTesId(cursor.getInt(cursor.getColumnIndex(KEY_TEST_ID)));
            records.setLastModifiedDate((new java.sql.Date(cursor.getLong(cursor.getColumnIndex(KEY_LAST_MODIFIED_DATE)))));
            records.setLastModifiedTime(new Time(cursor.getLong(cursor.getColumnIndex(KEY_LAST_MODIFIED_TIME))));
        }

        // Close cursor
        Utils.closeCursor(cursor);
        return records;
    }


    public List<Records> getAllRecordss() {
        final List<Records> recordss = new ArrayList<Records>();

        // Construct query
        String query = "SELECT  * FROM " + TABLE_RECORDS;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);

        // Iterate over results and populate list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                final Records records = new Records();
                records.setRecordId(cursor.getInt(cursor.getColumnIndex(KEY_RECORD_ID)));
                records.setStudentId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_ID)));
                records.setTesId(cursor.getInt(cursor.getColumnIndex(KEY_TEST_ID)));
                records.setLastModifiedDate((new java.sql.Date(cursor.getLong(cursor.getColumnIndex(KEY_LAST_MODIFIED_DATE)))));
                records.setLastModifiedTime(new Time(cursor.getLong(cursor.getColumnIndex(KEY_LAST_MODIFIED_TIME))));

                recordss.add(records);
            } while (cursor.moveToNext());
        }

        Utils.closeCursor(cursor);
        return recordss;
    }

    public void addRecords(final Records records) {
        Log.d("addRecords", records.toString());

        // Create content values to insert in table
        final ContentValues values = new ContentValues();
        values.put(KEY_RECORD_ID, records.getRecordId());
        values.put(KEY_STUDENT_ID, records.getStudentId());
        values.put(KEY_TEST_ID, records.getTesId());
        values.put(KEY_LAST_MODIFIED_DATE, records.getLastModifiedDate().toString());
        values.put(KEY_LAST_MODIFIED_TIME, records.getLastModifiedTime().toString());

        // Insert
        final SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_RECORDS, null, values);
        db.close();
    }

    public int updateRecords(final Records records) {
        Log.d("updateRecords", records.toString());

        // Create content values to update in table
        final ContentValues values = new ContentValues();
        values.put(KEY_RECORD_ID, records.getRecordId());
        values.put(KEY_STUDENT_ID, records.getStudentId());
        values.put(KEY_TEST_ID, records.getTesId());
        values.put(KEY_LAST_MODIFIED_DATE, records.getLastModifiedDate().toString());
        values.put(KEY_LAST_MODIFIED_TIME, records.getLastModifiedTime().toString());

        // Update
        final SQLiteDatabase db = this.getWritableDatabase();
        int numRows = db.update(TABLE_RECORDS, values,
                KEY_RECORD_ID + " = ?",
                new String[] {String.valueOf(records.getRecordId())});

        db.close();

        return numRows;
    }

    public void deleteRecords(final Records records) {
        Log.d("deleteRecords", records.toString());
        // Delete
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECORDS, KEY_RECORD_ID + " = ?",
                new String[] {String.valueOf(records.getRecordId())});

        db.close();
    }
    /********** CRUD operations for School table *************/

    public School getSchool(final String schoolId) {
        Log.d("getSchool", schoolId);
        final SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        final Cursor cursor = db.query(TABLE_SCHOOL,
                null, // Null ensures cursor returns all the columns
                " schoolId = ?",
                new String[]{schoolId},
                null, null, null, null);

        // Read result and build school object
        School school = null;
        if (cursor != null && cursor.moveToFirst()) {
            school = new School();
            school.setSchoolId(cursor.getInt(cursor.getColumnIndex(KEY_SCHOOL_ID)));
            school.setSchoolName(cursor.getString(cursor.getColumnIndex(KEY_SCHOOL_NAME)));
            school.setAddressId(cursor.getInt(cursor.getColumnIndex(KEY_ADDRESS_ID)));
        }

        // Close cursor
        Utils.closeCursor(cursor);
        return school;
    }


    public List<School> getAllSchools() {
        final List<School> schools = new ArrayList<School>();

        // Construct query
        String query = "SELECT  * FROM " + TABLE_SCHOOL;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);

        // Iterate over results and populate list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                final School school = new School();
                school.setSchoolId(cursor.getInt(cursor.getColumnIndex(KEY_SCHOOL_ID)));
                school.setSchoolName(cursor.getString(cursor.getColumnIndex(KEY_SCHOOL_NAME)));
                school.setAddressId(cursor.getInt(cursor.getColumnIndex(KEY_ADDRESS_ID)));

                schools.add(school);
            } while (cursor.moveToNext());
        }

        Utils.closeCursor(cursor);
        return schools;
    }

    public void addSchool(final School school) {
        Log.d("addSchool", school.toString());

        // Create content values to insert in table
        final ContentValues values = new ContentValues();
        values.put(KEY_SCHOOL_ID, school.getSchoolId());
        values.put(KEY_SCHOOL_NAME, school.getSchoolName());
        values.put(KEY_SCHOOL_ADDRESS_ID, school.getAddressId());

        // Insert
        final SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_SCHOOL, null, values);
        db.close();
    }

    public int updateSchool(final School school) {
        Log.d("updateSchool", school.toString());

        // Create content values to update in table
        final ContentValues values = new ContentValues();
        values.put(KEY_SCHOOL_ID, school.getSchoolId());
        values.put(KEY_SCHOOL_NAME, school.getSchoolName());
        values.put(KEY_SCHOOL_ADDRESS_ID, school.getAddressId());

        // Update
        final SQLiteDatabase db = this.getWritableDatabase();
        int numRows = db.update(TABLE_SCHOOL, values,
                KEY_SCHOOL_ID + " = ?",
                new String[] {String.valueOf(school.getSchoolId())});

        db.close();

        return numRows;
    }

    public void deleteSchool(final School school) {
        Log.d("deleteSchool", school.toString());
        // Delete
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCHOOL, KEY_STATE_CODE + " = ?",
                new String[] {String.valueOf(school.getSchoolId())});

        db.close();
    }

    /********** CRUD operations for Screener table *************/

    public Screener getScreener(final String screenerId) {
        Log.d("getScreener", screenerId);
        final SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        final Cursor cursor = db.query(TABLE_SCREENER,
                null,
                " screenerId = ?",
                new String[]{screenerId},
                null, null, null, null);

        // Read result and build screener object
        Screener screener = null;
        if (cursor != null && cursor.moveToFirst()) {
            screener = new Screener();
            screener.setScreenerId(cursor.getInt(cursor.getColumnIndex(KEY_SCREENER_ID)));
            screener.setEmailAddress(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_ADDRESS)));
            screener.setAddressId(cursor.getInt(cursor.getColumnIndex(KEY_SCREENER_ADDRESS_ID)));
            screener.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
            screener.setMiddleName(cursor.getString(cursor.getColumnIndex(KEY_MIDDLE_NAME)));
            screener.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
            screener.setContact(cursor.getString(cursor.getColumnIndex(KEY_CONTACT)));
        }

        // Close cursor
        Utils.closeCursor(cursor);
        return screener;
    }


    public List<Screener> getAllScreeners() {
        final List<Screener> screeners = new ArrayList<Screener>();

        // Construct query
        String query = "SELECT  * FROM " + TABLE_SCREENER;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);

        // Iterate over results and populate list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                final Screener screener = new Screener();
                screener.setScreenerId(cursor.getInt(cursor.getColumnIndex(KEY_SCREENER_ID)));
                screener.setEmailAddress(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_ADDRESS)));
                screener.setAddressId(cursor.getInt(cursor.getColumnIndex(KEY_SCREENER_ADDRESS_ID)));
                screener.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
                screener.setMiddleName(cursor.getString(cursor.getColumnIndex(KEY_MIDDLE_NAME)));
                screener.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
                screener.setContact(cursor.getString(cursor.getColumnIndex(KEY_CONTACT)));

                screeners.add(screener);
            } while (cursor.moveToNext());
        }

        Utils.closeCursor(cursor);
        return screeners;
    }

    public void addScreener(final Screener screener) {
        Log.d("addScreener", screener.toString());

        // Create content values to insert in table
        final ContentValues values = new ContentValues();
        values.put(KEY_SCREENER_ID, screener.getScreenerId());
        values.put(KEY_SCREENER_ADDRESS_ID, screener.getAddressId());
        values.put(KEY_FIRST_NAME, screener.getFirstName());
        values.put(KEY_MIDDLE_NAME, screener.getMiddleName());
        values.put(KEY_LAST_NAME, screener.getLastName());
        values.put(KEY_CONTACT, screener.getContact());
        values.put(KEY_EMAIL_ADDRESS, screener.getEmailAddress());

        // Insert
        final SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_SCREENER, null, values);
        db.close();
    }

    public int updateScreener(final Screener screener) {
        Log.d("updateScreener", screener.toString());

        // Create content values to update in table
        final ContentValues values = new ContentValues();
        values.put(KEY_SCREENER_ID, screener.getScreenerId());
        values.put(KEY_SCREENER_ADDRESS_ID, screener.getAddressId());
        values.put(KEY_FIRST_NAME, screener.getFirstName());
        values.put(KEY_MIDDLE_NAME, screener.getMiddleName());
        values.put(KEY_LAST_NAME, screener.getLastName());
        values.put(KEY_CONTACT, screener.getContact());
        values.put(KEY_EMAIL_ADDRESS, screener.getEmailAddress());

        // Update
        final SQLiteDatabase db = this.getWritableDatabase();
        int numRows = db.update(TABLE_SCREENER, values,
                KEY_SCREENER_ID + " = ?",
                new String[] {String.valueOf(screener.getScreenerId())});

        db.close();

        return numRows;
    }

    public void deleteScreener(final Screener screener) {
        Log.d("deleteScreener", screener.toString());
        // Delete
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCREENER, KEY_STATE_CODE + " = ?",
                new String[] {String.valueOf(screener.getScreenerId())});

        db.close();
    }

    /********** CRUD operations for ScreeningTest table *************/

    public ScreeningTest getScreeningTest(final String screeningTestId) {
        Log.d("getScreeningTest", screeningTestId);
        final SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        final Cursor cursor = db.query(TABLE_SCREENING_TEST,
                null,
                " screeningTestId = ?",
                new String[]{screeningTestId},
                null, null, null, null);

        // Read result and build screeningTest object
        ScreeningTest screeningTest = null;
        if (cursor != null && cursor.moveToFirst()) {
            screeningTest = new ScreeningTest();
            screeningTest.setRecordId(cursor.getInt(cursor.getColumnIndex(KEY_RECORD_ID)));
            screeningTest.setScreenerId(cursor.getInt(cursor.getColumnIndex(KEY_SCREENING_TEST_SCREENER_ID)));
            screeningTest.setDateConducted(new Date(cursor.getString(cursor.getColumnIndex(KEY_DATE_CONDUCTED))));
            screeningTest.setSandBag(cursor.getDouble(cursor.getColumnIndex(KEY_SANDBAG)));
            screeningTest.setTestId(cursor.getInt(cursor.getColumnIndex(KEY_TEST_ID)));
            screeningTest.setTrackingHorizontal(cursor.getString(cursor.getColumnIndex(KEY_TRACKING_HORIZONTAL)));
            screeningTest.setTrackingVertical(cursor.getString(cursor.getColumnIndex(KEY_TRACKING_VERTICAL)));
            screeningTest.setBalanceEyesCloseLeftFootSec(cursor.getLong(cursor.getColumnIndex(KEY_BALANCE_EYES_CLOSE_LEFT_FOOT)));
            screeningTest.setBalanceEyesCloseRightFootSec(cursor.getLong(cursor.getColumnIndex(KEY_BALANCE_EYES_CLOSE_RIGHT_FOOT)));
            screeningTest.setBalanceEyesOpenLeftFootSec(cursor.getLong(cursor.getColumnIndex(KEY_BALANCE_EYES_OPEN_LEFT_FOOT)));
            screeningTest.setBalanceEyesOpenRightFootSec(cursor.getLong(cursor.getColumnIndex(KEY_BALANCE_EYES_OPEN_RIGHT_FOOT)));
            screeningTest.setTeamingRound1(cursor.getString(cursor.getColumnIndex(KEY_TEAMING_ROUND1)));
            screeningTest.setTeamingRound2(cursor.getString(cursor.getColumnIndex(KEY_TEAMING_ROUND2)));
            screeningTest.setSkipping(cursor.getString(cursor.getColumnIndex(KEY_SKIPPING)));
            screeningTest.setCrawling(cursor.getString(cursor.getColumnIndex(KEY_CRAWLING)));
            screeningTest.setScores(cursor.getInt(cursor.getColumnIndex(KEY_SCORES)));
        }

        // Close cursor
        Utils.closeCursor(cursor);
        return screeningTest;
    }


    public List<ScreeningTest> getAllScreeningTests() {
        final List<ScreeningTest> screeningTests = new ArrayList<ScreeningTest>();

        // Construct query
        String query = "SELECT  * FROM " + TABLE_SCREENING_TEST;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);

        // Iterate over results and populate list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                final ScreeningTest screeningTest = new ScreeningTest();
                screeningTest.setRecordId(cursor.getInt(cursor.getColumnIndex(KEY_RECORD_ID)));
                screeningTest.setScreenerId(cursor.getInt(cursor.getColumnIndex(KEY_SCREENING_TEST_SCREENER_ID)));
                screeningTest.setDateConducted(new Date(cursor.getString(cursor.getColumnIndex(KEY_DATE_CONDUCTED))));
                screeningTest.setSandBag(cursor.getDouble(cursor.getColumnIndex(KEY_SANDBAG)));
                screeningTest.setTestId(cursor.getInt(cursor.getColumnIndex(KEY_TEST_ID)));
                screeningTest.setTrackingHorizontal(cursor.getString(cursor.getColumnIndex(KEY_TRACKING_HORIZONTAL)));
                screeningTest.setTrackingVertical(cursor.getString(cursor.getColumnIndex(KEY_TRACKING_VERTICAL)));
                screeningTest.setBalanceEyesCloseLeftFootSec(cursor.getLong(cursor.getColumnIndex(KEY_BALANCE_EYES_CLOSE_LEFT_FOOT)));
                screeningTest.setBalanceEyesCloseRightFootSec(cursor.getLong(cursor.getColumnIndex(KEY_BALANCE_EYES_CLOSE_RIGHT_FOOT)));
                screeningTest.setBalanceEyesOpenLeftFootSec(cursor.getLong(cursor.getColumnIndex(KEY_BALANCE_EYES_OPEN_LEFT_FOOT)));
                screeningTest.setBalanceEyesOpenRightFootSec(cursor.getLong(cursor.getColumnIndex(KEY_BALANCE_EYES_OPEN_RIGHT_FOOT)));
                screeningTest.setTeamingRound1(cursor.getString(cursor.getColumnIndex(KEY_TEAMING_ROUND1)));
                screeningTest.setTeamingRound2(cursor.getString(cursor.getColumnIndex(KEY_TEAMING_ROUND2)));
                screeningTest.setSkipping(cursor.getString(cursor.getColumnIndex(KEY_SKIPPING)));
                screeningTest.setCrawling(cursor.getString(cursor.getColumnIndex(KEY_CRAWLING)));
                screeningTest.setScores(cursor.getInt(cursor.getColumnIndex(KEY_SCORES)));

                screeningTests.add(screeningTest);
            } while (cursor.moveToNext());
        }

        Utils.closeCursor(cursor);
        return screeningTests;
    }

    public void addScreeningTest(final ScreeningTest screeningTest) {
        Log.d("addScreeningTest", screeningTest.toString());

        // Create content values to insert in table
        final ContentValues values = new ContentValues();
        values.put(KEY_TEST_ID, screeningTest.getTestId());
        values.put(KEY_SCREENING_TEST_RECORD_ID, screeningTest.getRecordId());
        values.put(KEY_SCREENING_TEST_SCREENER_ID, screeningTest.getScreenerId());
        values.put(KEY_DATE_CONDUCTED, screeningTest.getDateConducted().toString());
        values.put(KEY_SANDBAG, screeningTest.getSandBag());
        values.put(KEY_TRACKING_HORIZONTAL, screeningTest.getTrackingHorizontal());
        values.put(KEY_TRACKING_VERTICAL, screeningTest.getTrackingVertical());
        values.put(KEY_BALANCE_EYES_OPEN_LEFT_FOOT, screeningTest.getBalanceEyesOpenLeftFootSec());
        values.put(KEY_BALANCE_EYES_OPEN_RIGHT_FOOT, screeningTest.getBalanceEyesOpenRightFootSec());
        values.put(KEY_BALANCE_EYES_CLOSE_LEFT_FOOT, screeningTest.getBalanceEyesCloseLeftFootSec());
        values.put(KEY_BALANCE_EYES_CLOSE_RIGHT_FOOT, screeningTest.getBalanceEyesCloseRightFootSec());
        values.put(KEY_TEAMING_ROUND1, screeningTest.getTeamingRound1());
        values.put(KEY_TEAMING_ROUND2, screeningTest.getTeamingRound2());
        values.put(KEY_SKIPPING, screeningTest.getSkipping());
        values.put(KEY_CRAWLING, screeningTest.getCrawling());
        values.put(KEY_SCORES, screeningTest.getScores());

        // Insert
        final SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_SCREENING_TEST, null, values);
        db.close();
    }

    public int updateScreeningTest(final ScreeningTest screeningTest) {
        Log.d("updateScreeningTest", screeningTest.toString());

        // Create content values to update in table
        final ContentValues values = new ContentValues();
        values.put(KEY_TEST_ID, screeningTest.getTestId());
        values.put(KEY_SCREENING_TEST_RECORD_ID, screeningTest.getRecordId());
        values.put(KEY_SCREENING_TEST_SCREENER_ID, screeningTest.getScreenerId());
        values.put(KEY_DATE_CONDUCTED, screeningTest.getDateConducted().toString());
        values.put(KEY_SANDBAG, screeningTest.getSandBag());
        values.put(KEY_TRACKING_HORIZONTAL, screeningTest.getTrackingHorizontal());
        values.put(KEY_TRACKING_VERTICAL, screeningTest.getTrackingVertical());
        values.put(KEY_BALANCE_EYES_OPEN_LEFT_FOOT, screeningTest.getBalanceEyesOpenLeftFootSec());
        values.put(KEY_BALANCE_EYES_OPEN_RIGHT_FOOT, screeningTest.getBalanceEyesOpenRightFootSec());
        values.put(KEY_BALANCE_EYES_CLOSE_LEFT_FOOT, screeningTest.getBalanceEyesCloseLeftFootSec());
        values.put(KEY_BALANCE_EYES_CLOSE_RIGHT_FOOT, screeningTest.getBalanceEyesCloseRightFootSec());
        values.put(KEY_TEAMING_ROUND1, screeningTest.getTeamingRound1());
        values.put(KEY_TEAMING_ROUND2, screeningTest.getTeamingRound2());
        values.put(KEY_SKIPPING, screeningTest.getSkipping());
        values.put(KEY_CRAWLING, screeningTest.getCrawling());
        values.put(KEY_SCORES, screeningTest.getScores());

        // Update
        final SQLiteDatabase db = this.getWritableDatabase();
        int numRows = db.update(TABLE_SCREENING_TEST, values,
                KEY_TEST_ID+ " = ?",
                new String[] {String.valueOf(screeningTest.getTestId())});

        db.close();

        return numRows;
    }

    public void deleteScreeningTest(final ScreeningTest screeningTest) {
        Log.d("deleteScreeningTest", screeningTest.toString());
        // Delete
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCREENING_TEST, KEY_TEST_ID + " = ?",
                new String[] {String.valueOf(screeningTest.getTestId())});

        db.close();
    }

    /**
     * ******* CRUD operations for State table ************
     */

    public State getState(final String stateCode) {
        Log.d("getState", stateCode);
        final SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        final Cursor cursor = db.query(TABLE_STATE_INFO,
                null, // Null ensures cursor returns all the columns
                " stateCode = ?",
                new String[]{stateCode},
                null, null, null, null);

        // Read result and build state object
        State state = null;
        if (cursor != null && cursor.moveToFirst()) {
            state = new State();
            state.setStateCode(cursor.getString(cursor.getColumnIndex(KEY_STATE_CODE)));
            state.setStateName(cursor.getString(cursor.getColumnIndex(KEY_STATE_NAME)));
        }

        // Close cursor
        Utils.closeCursor(cursor);
        return state;
    }


    public List<State> getAllStates() {
        final List<State> states = new ArrayList<State>();

        // Construct query
        String query = "SELECT  * FROM " + TABLE_STATE_INFO;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);

        // Iterate over results and populate list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                final State state = new State();
                state.setStateCode(cursor.getString(cursor.getColumnIndex(KEY_STATE_CODE)));
                state.setStateName(cursor.getString(cursor.getColumnIndex(KEY_STATE_NAME)));

                states.add(state);
            } while (cursor.moveToNext());
        }

        Utils.closeCursor(cursor);
        return states;
    }

    public void addState(final State state) {
        Log.d("addState", state.toString());

        // Create content values to insert in table
        final ContentValues values = new ContentValues();
        values.put(KEY_STATE_CODE, state.getStateCode());
        values.put(KEY_STATE_NAME, state.getStateName());

        // Insert
        final SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_STATE_INFO, null, values);
        db.close();
    }

    public int updateState(final State state) {
        Log.d("updateState", state.toString());

        // Create content values to update in table
        final ContentValues values = new ContentValues();
        values.put(KEY_STATE_CODE, state.getStateCode());
        values.put(KEY_STATE_NAME, state.getStateName());

        // Update
        final SQLiteDatabase db = this.getWritableDatabase();
        int numRows = db.update(TABLE_STATE_INFO, values,
                KEY_STATE_CODE + " = ?",
                new String[] {state.getStateCode()});

        db.close();

        return numRows;
    }

    public void deleteState(State state) {
        Log.d("updateState", state.toString());
        // Delete
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STATE_INFO, KEY_STATE_CODE + " = ?",
                new String[] {state.getStateCode()});

        db.close();
    }

    /********** CRUD operations for Student table *************/

    public Student getStudent(final String studentId) {
        Log.d("getStudent", studentId);
        final SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        final Cursor cursor = db.query(TABLE_STUDENT,
                null,
                " studentId = ?",
                new String[]{studentId},
                null, null, null, null);

        // Read result and build student object
        Student student = null;
        if (cursor != null && cursor.moveToFirst()) {
            student = new Student();
            student.setStudentId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_ID)));
            student.setClassInfo(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_CLASS)));
            student.setAddressId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_ADDRESS_ID)));
            student.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
            student.setMiddleName(cursor.getString(cursor.getColumnIndex(KEY_MIDDLE_NAME)));
            student.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
            student.setAgeGroup(cursor.getString(cursor.getColumnIndex(KEY_AGE_GROUP)));
            student.setGender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));
            student.setGrade(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_GRADE)));
            student.setUsesGlasses(cursor.getString(cursor.getColumnIndex(KEY_USE_GLASSES)));
            student.setParentId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_PARENT_ID)));
            student.setRecordId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_RECORD_ID)));
            student.setSchoolId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_SCHOOL_ID)));
            student.setDateOfBirth(new Date(cursor.getString(cursor.getColumnIndex(KEY_DOB))));
        }

        // Close cursor
        Utils.closeCursor(cursor);
        return student;
    }


    public List<Student> getAllStudents() {
        final List<Student> students = new ArrayList<Student>();

        // Construct query
        String query = "SELECT  * FROM " + TABLE_STUDENT;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);

        // Iterate over results and populate list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                final Student student = new Student();
                student.setStudentId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_ID)));
                student.setClassInfo(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_CLASS)));
                student.setAddressId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_ADDRESS_ID)));
                student.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
                student.setMiddleName(cursor.getString(cursor.getColumnIndex(KEY_MIDDLE_NAME)));
                student.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
                student.setAgeGroup(cursor.getString(cursor.getColumnIndex(KEY_AGE_GROUP)));
                student.setGender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));
                student.setGrade(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_GRADE)));
                student.setUsesGlasses(cursor.getString(cursor.getColumnIndex(KEY_USE_GLASSES)));
                student.setParentId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_PARENT_ID)));
                student.setRecordId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_RECORD_ID)));
                student.setSchoolId(cursor.getInt(cursor.getColumnIndex(KEY_STUDENT_SCHOOL_ID)));
                student.setDateOfBirth(new Date(cursor.getString(cursor.getColumnIndex(KEY_DOB))));

                students.add(student);
            } while (cursor.moveToNext());
        }

        Utils.closeCursor(cursor);
        return students;
    }

    public void addStudent(final Student student) {
        Log.d("addStudent", student.toString());

        // Create content values to insert in table
        final ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, student.getStudentId());
        values.put(KEY_STUDENT_ADDRESS_ID, student.getAddressId());
        values.put(KEY_FIRST_NAME, student.getFirstName());
        values.put(KEY_MIDDLE_NAME, student.getMiddleName());
        values.put(KEY_LAST_NAME, student.getLastName());
        values.put(KEY_STUDENT_PARENT_ID, student.getParentId());
        values.put(KEY_STUDENT_SCHOOL_ID, student.getSchoolId());
        values.put(KEY_STUDENT_RECORD_ID, student.getRecordId());
        values.put(KEY_STUDENT_CLASS, student.getClassInfo());
        values.put(KEY_STUDENT_GRADE, student.getGrade());
        values.put(KEY_GENDER, student.getGender());
        values.put(KEY_AGE_GROUP, student.getAgeGroup());
        values.put(KEY_USE_GLASSES, student.getUsesGlasses());
        values.put(KEY_DOB, student.getDateOfBirth().toString());

        // Insert
        final SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    public int updateStudent(final Student student) {
        Log.d("updateStudent", student.toString());

        // Create content values to update in table
        final ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, student.getStudentId());
        values.put(KEY_STUDENT_ADDRESS_ID, student.getAddressId());
        values.put(KEY_FIRST_NAME, student.getFirstName());
        values.put(KEY_MIDDLE_NAME, student.getMiddleName());
        values.put(KEY_LAST_NAME, student.getLastName());
        values.put(KEY_STUDENT_PARENT_ID, student.getParentId());
        values.put(KEY_STUDENT_SCHOOL_ID, student.getSchoolId());
        values.put(KEY_STUDENT_RECORD_ID, student.getRecordId());
        values.put(KEY_STUDENT_CLASS, student.getClassInfo());
        values.put(KEY_STUDENT_GRADE, student.getGrade());
        values.put(KEY_GENDER, student.getGender());
        values.put(KEY_AGE_GROUP, student.getAgeGroup());
        values.put(KEY_USE_GLASSES, student.getUsesGlasses());
        values.put(KEY_DOB, student.getDateOfBirth().toString());

        // Update
        final SQLiteDatabase db = this.getWritableDatabase();
        int numRows = db.update(TABLE_STUDENT, values,
                KEY_STUDENT_ID + " = ?",
                new String[] {String.valueOf(student.getStudentId())});

        db.close();

        return numRows;
    }

    public void deleteStudent(final Student student) {
        Log.d("deleteStudent", student.toString());
        // Delete
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_STATE_CODE + " = ?",
                new String[] {String.valueOf(student.getStudentId())});

        db.close();
    }


    /********** CRUD operations for Student table *************/
    /************ CRUD operations for Class table *************/
    /********** CRUD operations for Classroom table ***********/
    /************ CRUD operations for Grades table *************/


}
