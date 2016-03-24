package redflag.capstone.com.redflag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import redflag.capstone.com.redflag.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import redflag.capstone.com.redflag.database.DatabaseHelper;
import android.content.Intent;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.PendingIntent.getActivity;

/*public class RegisterStudentActivity extends Activity implements OnClickListener, OnItemSelectedListener{

    // Variable Declaration should be in onCreate()
    private Button mSubmit;
    private Button mCancel;

    private EditText mFname;
    private EditText mLname;
    private EditText mSchool;
    private EditText mClass;
    private EditText mAge;
    private EditText mGuardianName;
    private EditText mEmail;
    private Spinner mGender;
    private String Gen;

    protected DatabaseHelper DB = new DatabaseHelper(RegisterStudentActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        //Assignment of UI fields to the variables
        mSubmit = (Button)findViewById(R.id.btnRegister);
        mSubmit.setOnClickListener(this);

        mCancel = (Button)findViewById(R.id.cancel);
        mCancel.setOnClickListener(this);

        mFname = (EditText)findViewById(R.id.std_firstname);
        mLname = (EditText)findViewById(R.id.std_lastname);
        mSchool = (EditText)findViewById(R.id.std_schoolname);
        mClass = (EditText)findViewById(R.id.std_class);
        mAge = (EditText)findViewById(R.id.std_age);
        mGuardianName = (EditText)findViewById(R.id.std_guardianname);

       mEmail = (EditText)findViewById(R.id.std_email);
       mGender = (Spinner)findViewById(R.id.spinner1);

        // Spinner method to read the on selected value
        ArrayAdapter<State> spinnerArrayAdapter = new ArrayAdapter<State>(this,
                android.R.layout.simple_spinner_item, new State[] {
                new State("Male"),
                new State("Female")});
        mGender.setAdapter(spinnerArrayAdapter);
        mGender.setOnItemSelectedListener(this);
    }

    public void onClick(View v)
    {

        switch(v.getId()){

            case R.id.cancel:
                Intent i = new Intent(getBaseContext(), LoginSuccessActivity.class);
                startActivity(i);
                //finish();
                break;

            case R.id.btnRegister:


                String fname = mFname.getText().toString();
                String lname = mLname.getText().toString();

                String email = mEmail.getText().toString();
                String age = mAge.getText().toString();
                String school = mSchool.getText().toString();
                String stdClass = mClass.getText().toString();
                String guardianName = mGuardianName.getText().toString();

                boolean invalid = false;

                if(fname.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter student's First Name", Toast.LENGTH_SHORT).show();
                }
                else

                if(lname.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter student's Last Name", Toast.LENGTH_SHORT).show();
                }
                else

                if(age.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter student's age", Toast.LENGTH_SHORT).show();
                }
                else


                if(school.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter student's school name", Toast.LENGTH_SHORT).show();

                }
                else
                if(email.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter student's Email ID", Toast.LENGTH_SHORT).show();
                }
                else
                if(invalid == false)
                {
                    addEntry(fname, lname, Gen, age, school, email, stdClass, guardianName);
                    Intent i_register = new Intent(RegisterStudentActivity.this, LoginSuccessActivity.class);
                    startActivity(i_register);
                    //finish();
                }

                break;
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        DB.close();
    }

    private void addEntry(String fname, String lname, String Gen, String age, String school, String email, String stdClass, String guardian)
    {

       // SQLiteDatabase db = DB.getWritableDatabase();
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_STUDENT_FIRST_NAME, fname);
        values.put(DatabaseHelper.KEY_STUDENT_LAST_NAME, lname);
        values.put(DatabaseHelper.KEY_GENDER, Gen);
        values.put(DatabaseHelper.KEY_AGE_GROUP, age);
        values.put(DatabaseHelper.KEY_STUDENT_CLASS, stdClass);
        values.put(DatabaseHelper.KEY_STUDENT_SCHOOL_ID, school);

        try
        {
            db.InsertRecord(values, DatabaseHelper.TABLE_STUDENT);

            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        // Get the currently selected State object from the spinner
        State st = (State)mGender.getSelectedItem();

        // Show it via a toast
        toastState( "onItemSelected", st );
    }

    public void toastState(String name, State st)
    {
        if ( st != null )
        {
            Gen = st.name;
            //Toast.makeText(getBaseContext(), Gen, Toast.LENGTH_SHORT).show();

        }

    }


    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class State
    {
        public String name = "";


        public State(String _name)
        {

            name = _name;

        }
        public String toString()
        {
            return name;
        }
    }
}*/

/*
public class RegisterStudentActivity extends Activity
{
    EditText USER_NAME,USER_PASS,CON_PASS;
    String user_name,user_pass,con_pass;
    Button REG;
    Context ctxt = this;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        USER_NAME = (EditText)findViewById(R.id.reg_user);
        USER_PASS = (EditText)findViewById(R.id.reg_pass);
        CON_PASS = (EditText)findViewById(R.id.con_pass);
        REG = (Button) findViewById(R.id.user_reg);
        REG.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //FOr debugging
                Intent dbmanager = new Intent(RegisterStudentActivity.this, AndroidDatabaseManager.class);
                startActivity(dbmanager);
                ///

                user_name = USER_NAME.getText().toString();
                user_pass = USER_PASS.getText().toString();
                con_pass = CON_PASS.getText().toString();

                if(!(user_pass.equals(con_pass)))
                {
                    Toast.makeText(getBaseContext(),"Passwords are not matching !", Toast.LENGTH_LONG).show();
                    USER_NAME.setText("");
                    USER_PASS.setText("");
                    CON_PASS.setText("");
                }
                else
                {
                    DatabaseOperations DB = new DatabaseOperations(ctxt);
                    DB.putInformation(DB,user_name, user_pass);
                    Toast.makeText(getBaseContext(),"Registration success !",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
}*/


public class RegisterStudentActivity extends Activity implements OnClickListener, OnItemSelectedListener {
    EditText user_cname, user_tname, user_age, user_dos, user_school, user_grade;
    String struser_cname, struser_tname, struser_age, struser_dos, struser_school, struser_grade;
    Button REG;
    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        String currentDateTimeString = dateFormat.getDateTimeInstance().format(new Date());
        user_cname = (EditText) findViewById(R.id.user_cname);
        user_tname = (EditText) findViewById(R.id.user_tname);
        user_age = (EditText) findViewById(R.id.user_age);
        user_dos = (EditText) findViewById(R.id.user_dos);
        user_dos.setText(dateFormat.format(new Date()));
        user_dos.setEnabled(false);
        user_school = (EditText) findViewById(R.id.user_school);
        user_grade = (EditText) findViewById(R.id.user_grade);

        REG = (Button) findViewById(R.id.user_reg);

       // final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);


        REG.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //FOr debugging// From GIT
//                Intent dbmanager = new Intent(RegisterStudentActivity.this, AndroidDatabaseManager.class);
//                startActivity(dbmanager);
                ///

                struser_cname = user_cname.getText().toString();
                struser_tname = user_tname.getText().toString();
                struser_age = user_age.getText().toString();
                struser_dos = user_dos.getText().toString();
                struser_school = user_school.getText().toString();
                struser_grade = user_grade.getText().toString();

                if (struser_cname.isEmpty() || struser_tname.isEmpty() || struser_age.isEmpty() || struser_dos.isEmpty() || struser_school.isEmpty() || struser_grade.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please fill up all the details !", Toast.LENGTH_LONG).show();
                } else {
                    dBHelper = new DatabaseOperations(ctxt);
                    dBHelper.register_student(dBHelper, struser_cname, struser_tname, struser_age, struser_dos, struser_school, struser_grade);
                    //Toast.makeText(getBaseContext(), "Registration success !", Toast.LENGTH_LONG).show();

                    //alertDialog.setTitle("Alert Dialog")

                    alertDialog.setMessage("Registration Success ! Do you want to Register more?");
//                    user_cname.setText("");
//                    user_age.setText("");
//                    user_school.setText("");
//                    user_grade.setText("");
//                    user_tname.setText("");
                    //alertDialog.setIcon(R.drawable.tick);

                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent registrationIntent = new Intent(getBaseContext(), RegisterStudentActivity.class);
                            startActivity(registrationIntent);

                        }
                    });
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent loginintent = new Intent(getBaseContext(), LoginSuccessActivity.class);
                            startActivity(loginintent);
                        }
                    });
                    alertDialog.show();

                }

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "RegisterStudent Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://redflag.capstone.com.redflag/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "RegisterStudent Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://redflag.capstone.com.redflag/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

