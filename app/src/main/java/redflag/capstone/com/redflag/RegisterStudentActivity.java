package redflag.capstone.com.redflag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
                    alertDialog.setMessage("Registration Success ! Do you want to Register more?");

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

