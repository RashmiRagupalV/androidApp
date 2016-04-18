package redflag.capstone.com.redflag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by rashm on 4/18/2016.
 */
public class RecordSkipping extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;
    RadioGroup rgrp1;
    int radioButtonID1;
    Button save, next;
    String radioButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(ctxt);
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_skipping);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        save = (Button) findViewById(R.id.button2);
        next = (Button) findViewById(R.id.button1);

        next.setEnabled(false);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle b = intent.getExtras();

                final String studId = (String) b.get("SelectedstudId");

                rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
                radioButtonID1 = rgrp1.getCheckedRadioButtonId();

                if (radioButtonID1 < 0 )
                    Toast.makeText(getBaseContext(), "Please select one option!", Toast.LENGTH_LONG).show();
                else {

                    RadioButton checkedbutton = (RadioButton) findViewById(radioButtonID1);
                    radioButton1 = checkedbutton.getText().toString();

                    alertDialog.setMessage("Are you sure you want to Submit?");

                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dBHelper.insertStudentSkippingDetails(dBHelper, studId, radioButton1);

                            next.setEnabled(true);
                            save.setEnabled(false);
                            for (int i = 0; i < rgrp1.getChildCount(); i++) {
                                rgrp1.getChildAt(i).setEnabled(false);

                            }

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




    public void saveskippingdetails(View view) {
        Intent intent = new Intent(this, RecordSkipping.class);
        startActivity(intent);
    }

    public void nextactivity_comments(View view){
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String studId = (String) b.get("SelectedstudId");
        intent = new Intent(this, RecordComments.class);
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
