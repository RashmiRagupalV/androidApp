package redflag.capstone.com.redflag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.AutoCompleteTextView;
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
import java.util.List;

import static android.app.PendingIntent.getActivity;


public class RegisterStudentActivity extends Activity implements OnClickListener, OnItemSelectedListener {
    EditText user_cname, user_tname, user_age, user_dos, user_school, user_grade;
    String struser_cname, struser_tname, struser_age, struser_dos, struser_school, struser_grade;
    Button REG, LOGOUT;
    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;
    EditText others;
    String selectedlabel;
    Spinner spinner;
    AutoCompleteTextView actv;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        loadData();
        actv.setThreshold(1);



        // Spinner element
       // spinner = (Spinner) findViewById(R.id.spinnerschool);
        // Spinner click listener
//        spinner.setOnItemSelectedListener(this);
//        others = (EditText)findViewById(R.id.user_school);
//        others.setEnabled(false);
//        others.setBackgroundColor(Color.parseColor("BLUE"));
//        loadSpinnerData();



        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        String currentDateTimeString = dateFormat.getDateTimeInstance().format(new Date());
        user_cname = (EditText) findViewById(R.id.user_cname);
        user_tname = (EditText) findViewById(R.id.user_tname);

        user_age = (EditText) findViewById(R.id.user_age);

        user_dos = (EditText) findViewById(R.id.user_dos);
        user_dos.setText(dateFormat.format(new Date()));
        user_dos.setEnabled(false);
        //user_school = (EditText) findViewById(R.id.user_school);

        user_grade = (EditText) findViewById(R.id.user_grade);


        REG = (Button) findViewById(R.id.user_reg);
        LOGOUT = (Button) findViewById(R.id.user_logout);


       // final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

        LOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setMessage("Are you sure you want to log off ?");

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do this on logout button click
//                            final String LOG_OUT = "event_logout";
//                            Intent intent = new Intent(LOG_OUT);
//                            //send the broadcast to all activities who are listening
//                            LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);
                        Intent loginscreen = new Intent(getBaseContext(), Login.class);
                        startActivity(loginscreen);

                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                            Intent loginintent = new Intent(getBaseContext(), LoginSuccessActivity.class);
//                            startActivity(loginintent);
                    }
                });
                alertDialog.show();

            }

        });

        REG.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //FOr debugging// From GIT
//                Intent dbmanager = new Intent(RegisterStudentActivity.this, AndroidDatabaseManager.class);
//                startActivity(dbmanager);
                ///
                if( user_cname.getText().toString().length() == 0 )
                    user_cname.setError( "Student name is required !" );
                if( user_tname.getText().toString().length() == 0 )
                    user_tname.setError( "Teacher name is required !" );
                if( user_age.getText().toString().length() == 0 )
                    user_age.setError( "Age cannot be empty !" );

             //   if( user_school.getText().toString().length() == 0 )
               //     user_school.setError( "School name cannot be empty !" );

                if( actv.getText().toString().length() == 0 )
                    actv.setError( "School Name cannot be empty !" );

                if( user_grade.getText().toString().length() == 0 )
                    user_grade.setError( "Grade cannot be empty !" );


                struser_cname = user_cname.getText().toString();
                struser_tname = user_tname.getText().toString();
                struser_age = user_age.getText().toString();

                struser_dos = user_dos.getText().toString();
           //     struser_school = user_school.getText().toString();
                struser_grade = user_grade.getText().toString();
                selectedlabel = actv.getText().toString();
//                if(selectedlabel == "Other"){
//                    selectedlabel = others.getText().toString();
//                    if (selectedlabel.length()==0)
//                        others.setError("Please enter School Name !");
//                }

                if (struser_cname.isEmpty() || struser_tname.isEmpty() || struser_age.isEmpty() || struser_dos.isEmpty() || selectedlabel.isEmpty() || struser_grade.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please fill up all details !", Toast.LENGTH_LONG).show();
                } else {
                    int i = Integer.valueOf(struser_age);
                    int j = Integer.valueOf(struser_grade);

                    if (((i <= 0) || (i > 20)) || ((j<=0) || (j>8))) {
                        if ((i <= 0) || (i > 20)) {
                            user_age.setError("Age not in valid range (1-20) !");
                            Toast.makeText(getBaseContext(), "Please correct all errors !", Toast.LENGTH_LONG).show();
                        }
                        if ((j<=0) || (j>8)) {
                            user_grade.setError("Grade not in valid range (1-8) !");
                            Toast.makeText(getBaseContext(), "Please correct all errors !", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        dBHelper = new DatabaseOperations(ctxt);
                        dBHelper.register_student(dBHelper, struser_cname, struser_tname, struser_age, struser_dos, selectedlabel, struser_grade);
                        alertDialog.setMessage("Registration Success ! Do you want to Register more students ?");

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

            }
        });

        user_cname.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                user_cname.setError(null);
            }
        });

        user_tname.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                user_tname.setError(null);
            }
        });

        user_age.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                user_age.setError(null);
            }
        });

        actv.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                actv.setError(null);
            }
        });

        user_grade.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                user_grade.setError(null);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {

    }

    public void cancelRegistration(View view) {
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        startActivity(intent);
    }

    public void resetForm(View view) {
        struser_cname = user_cname.getText().toString();
        struser_tname = user_tname.getText().toString();
        struser_age = user_age.getText().toString();
        struser_dos = user_dos.getText().toString();
        struser_school = user_school.getText().toString();
        struser_grade = user_grade.getText().toString();
        Intent registrationIntent = new Intent(getBaseContext(), RegisterStudentActivity.class);
        startActivity(registrationIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
//        selectedlabel = parent.getItemAtPosition(position).toString();
//
//        // Showing selected spinner item
//
////        Toast.makeText(parent.getContext(), "You selected: " + label,
////                Toast.LENGTH_LONG).show();
//        if (selectedlabel == "Other"){
//            others.setEnabled(true);
//            others.setBackgroundColor(Color.parseColor("CYAN"));
//            others.requestFocus();
//            others.setTextColor(Color.BLACK);
//
//        }
//        else{
//            others.setEnabled(false);
//            others.setBackgroundColor(Color.parseColor("BLUE"));
//            others.setText("");
//            spinner.requestFocus();
//        }

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

    private void loadData(){

        // database handler
        dBHelper = new DatabaseOperations(ctxt);

        // Spinner Drop down elements
        List<String> lables = dBHelper.getAllSchoolNames(dBHelper);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);


//        // Drop down layout style - list view with radio button
//        dataAdapter
//                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        actv.setAdapter(dataAdapter);

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

