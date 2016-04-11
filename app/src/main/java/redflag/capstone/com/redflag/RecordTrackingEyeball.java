package redflag.capstone.com.redflag;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.AlertDialog;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;

/**
 * Created by rashm on 4/6/2016.
 */
public class RecordTrackingEyeball extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;
    RadioGroup rgrp1, rgrp2,rgrp3;
    int radioButtonID1, radioButtonID2, radioButtonID3;
    Button save, next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(ctxt);
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_tracking_eyeball);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        save = (Button) findViewById(R.id.button14);
        next = (Button) findViewById(R.id.button4);

        next.setEnabled(false);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle b = intent.getExtras();

                String studId = (String) b.get("SelectedstudId");

                rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
                radioButtonID1 = rgrp1.getCheckedRadioButtonId();
                rgrp2 = (RadioGroup) findViewById(R.id.rgrp2);
                radioButtonID2 = rgrp2.getCheckedRadioButtonId();
                rgrp3 = (RadioGroup) findViewById(R.id.rgrp3);
                radioButtonID3 = rgrp3.getCheckedRadioButtonId();


                if (radioButtonID1 < 0 || radioButtonID2 < 0 || radioButtonID3 < 0)
                    Toast.makeText(getBaseContext(), "Please select one option from each type!", Toast.LENGTH_LONG).show();
                else {

                    RadioButton checkedbutton = (RadioButton) findViewById(radioButtonID1);
                    String radioButton1 = checkedbutton.getText().toString();

                    checkedbutton = (RadioButton) findViewById(radioButtonID2);
                    String radioButton2 = checkedbutton.getText().toString();

                    checkedbutton = (RadioButton) findViewById(radioButtonID3);
                    String radioButton3 = checkedbutton.getText().toString();

                        dBHelper.insertStudentTrackingDetails(dBHelper, studId, radioButton1, radioButton2, radioButton3);
                        alertDialog.setMessage("Are you sure you want to Submit?");

                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                next.setEnabled(true);
                                save.setEnabled(false);

                            }
                        });
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });
                        alertDialog.show();


                }
            }

        });

    }


    public void savetrackingeyeball(View view) {
        Intent intent = new Intent(this, RecordTrackingEyeball.class);
        startActivity(intent);
    }

    public void nextactivity_balancing(View view){
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String studId = (String) b.get("SelectedstudId");
        intent = new Intent(this, RecordBalancing.class);
        intent.putExtra("SelectedstudId", studId);
        startActivity(intent);
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

}
