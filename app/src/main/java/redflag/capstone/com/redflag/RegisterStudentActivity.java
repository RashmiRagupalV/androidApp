package redflag.capstone.com.redflag;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

/*public class RegisterStudentActivity extends Activity
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


public class RegisterStudentActivity extends Activity {
    EditText user_fname, user_lname, user_email, user_age, user_school, user_class, user_guardian;
    String struser_fname, struser_lname, struser_email, struser_age, struser_school, struser_class, struser_guardian;
    Button REG;
    Context ctxt = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        user_fname = (EditText) findViewById(R.id.user_fname);
        user_lname = (EditText) findViewById(R.id.user_lname);
        user_email = (EditText) findViewById(R.id.user_email);
        user_age = (EditText) findViewById(R.id.user_email);
        user_school = (EditText) findViewById(R.id.user_school);
        user_class = (EditText) findViewById(R.id.user_class);
        user_guardian = (EditText) findViewById(R.id.user_guardian);

        REG = (Button) findViewById(R.id.user_reg);
        REG.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //FOr debugging
                Intent dbmanager = new Intent(RegisterStudentActivity.this, AndroidDatabaseManager.class);
                startActivity(dbmanager);
                ///

                struser_fname = user_fname.getText().toString();
                struser_lname = user_lname.getText().toString();
                struser_email = user_email.getText().toString();
                struser_age = user_age.getText().toString();
                struser_school = user_school.getText().toString();
                struser_class = user_class.getText().toString();
                struser_guardian = user_guardian.getText().toString();

                DatabaseOperations DB = new DatabaseOperations(ctxt);
                DB.register_student(DB, struser_fname, struser_lname, struser_email, struser_age, struser_school, struser_class, struser_guardian);
                Toast.makeText(getBaseContext(), "Registration success !", Toast.LENGTH_LONG).show();
                finish();

            }
        });
    }
}

