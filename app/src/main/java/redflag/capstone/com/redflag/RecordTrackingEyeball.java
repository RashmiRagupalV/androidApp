package redflag.capstone.com.redflag;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

    public static final String MyPREFERENCES = "MyPrefs" ;

    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;
    RadioGroup rgrp1, rgrp2,rgrp3;
    TextView txt1,txt2,txt3;
    int radioButtonID1, radioButtonID2, radioButtonID3;
    Button save, next;
    String radioButton1, radioButton2,radioButton3;
    SharedPreferences sharedpreferences;
    Button SKIP;
    boolean skip_pressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(ctxt);
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_tracking_eyeball);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putString("TrackingEyeballSkipped", "notskipped");

        next = (Button) findViewById(R.id.button4);
        SKIP = (Button)findViewById(R.id.button6);
        rgrp1 = (RadioGroup)findViewById(R.id.rgrp1);
        rgrp1.clearCheck();
        rgrp2 = (RadioGroup)findViewById(R.id.rgrp2);
        rgrp2.clearCheck();
        rgrp3 = (RadioGroup)findViewById(R.id.rgrp3);
        rgrp3.clearCheck();

        txt1= (TextView)findViewById(R.id.horizontal);
        txt2= (TextView)findViewById(R.id.vertical);
        txt3= (TextView)findViewById(R.id.beanbag);
        skip_pressed = false;
        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);


        SKIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setMessage("Are you sure you want to skip this activity ?");

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SKIP.setEnabled(false);
                        skip_pressed = true;
                        txt1.setEnabled(false);
                        txt1.setTextColor(Color.GRAY);
                        txt2.setEnabled(false);
                        txt2.setTextColor(Color.GRAY);

                        txt3.setEnabled(false);
                        txt3.setTextColor(Color.GRAY);

                        for(int i = 0; i < rgrp1.getChildCount(); i++){
                            ((RadioButton)rgrp1.getChildAt(i)).setEnabled(false);
                            ((RadioButton)rgrp1.getChildAt(i)).setTextColor(Color.GRAY);
                            ((RadioButton)rgrp1.getChildAt(i)).setChecked(false);
                        }
                        for(int i = 0; i < rgrp2.getChildCount(); i++){
                            ((RadioButton)rgrp2.getChildAt(i)).setEnabled(false);
                            ((RadioButton)rgrp2.getChildAt(i)).setTextColor(Color.GRAY);
                            ((RadioButton)rgrp2.getChildAt(i)).setChecked(false);
                        }
                        for(int i = 0; i < rgrp3.getChildCount(); i++){
                            ((RadioButton)rgrp3.getChildAt(i)).setEnabled(false);
                            ((RadioButton)rgrp3.getChildAt(i)).setTextColor(Color.GRAY);
                            ((RadioButton)rgrp3.getChildAt(i)).setChecked(false);
                        }
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("Radiobutton1","NA" );
                        editor.putString("Radiobutton2", "NA");
                        editor.putString("Radiobutton3", "NA");
                        editor.putString("TrackingEyeballSkipped", "skipped");
                        editor.commit();

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

        Intent intent = getIntent();
        String activity = intent.getStringExtra("activity");
        if (activity == null || activity.length()==0){

        }
        else if (activity.equals("prevtracking")){ // From the balancing activity

            editor = sharedpreferences.edit();
            String check = sharedpreferences.getString("TrackingEyeballSkipped", "");
            if(check.equals("skipped")){
                SKIP.setEnabled(false);
                txt1.setEnabled(false);
                txt1.setTextColor(Color.GRAY);
                txt2.setEnabled(false);
                txt2.setTextColor(Color.GRAY);

                txt3.setEnabled(false);
                txt3.setTextColor(Color.GRAY);

                for(int i = 0; i < rgrp1.getChildCount(); i++){
                    ((RadioButton)rgrp1.getChildAt(i)).setEnabled(false);
                    ((RadioButton)rgrp1.getChildAt(i)).setTextColor(Color.GRAY);
                    ((RadioButton)rgrp1.getChildAt(i)).setChecked(false);
                }
                for(int i = 0; i < rgrp2.getChildCount(); i++){
                    ((RadioButton)rgrp2.getChildAt(i)).setEnabled(false);
                    ((RadioButton)rgrp2.getChildAt(i)).setTextColor(Color.GRAY);
                    ((RadioButton)rgrp2.getChildAt(i)).setChecked(false);
                }
                for(int i = 0; i < rgrp3.getChildCount(); i++){
                    ((RadioButton)rgrp3.getChildAt(i)).setEnabled(false);
                    ((RadioButton)rgrp3.getChildAt(i)).setTextColor(Color.GRAY);
                    ((RadioButton)rgrp3.getChildAt(i)).setChecked(false);
                }
                skip_pressed = true;
            }
            else {
                int value1 = sharedpreferences.getInt("id1", 0);
                int value2 = sharedpreferences.getInt("id2", 0);
                int value3 = sharedpreferences.getInt("id3", 0);

                rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
                rgrp1.check(value1);
                rgrp2 = (RadioGroup) findViewById(R.id.rgrp2);
                rgrp2.check(value2);
                rgrp3 = (RadioGroup) findViewById(R.id.rgrp3);
                rgrp3.check(value3);
                skip_pressed = false;
            }
        }
    }

    public void savetrackingeyeball(View view) {
        Intent intent = new Intent(this, RecordTrackingEyeball.class);
        startActivity(intent);
    }

    public void cancelRegistration(View view) {
        clearall();
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        startActivity(intent);
    }

    public void clearall(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove("Radiobutton1");
        editor.remove("Radiobutton2");
        editor.remove("Radiobutton3");
        editor.remove("id1");
        editor.remove("id2");
        editor.remove("id3");
        editor.remove("TrackingEyeballSkipped");

       // editor.clear();
        editor.commit();

    }

    public void backscreen(View view) {
        clearall();
        Intent intent = new Intent(this, StudentDetailsActivity.class);
        startActivity(intent);
    }

    public void nextactivity_balancing(View view){

        rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
        radioButtonID1 = rgrp1.getCheckedRadioButtonId();
        rgrp2 = (RadioGroup) findViewById(R.id.rgrp2);
        radioButtonID2 = rgrp2.getCheckedRadioButtonId();
        rgrp3 = (RadioGroup) findViewById(R.id.rgrp3);
        radioButtonID3 = rgrp3.getCheckedRadioButtonId();

        if (!skip_pressed) {
            if ((rgrp1.getCheckedRadioButtonId() <= 0) || (rgrp2.getCheckedRadioButtonId() <= 0) || (rgrp3.getCheckedRadioButtonId() <= 0)) {
                Toast.makeText(getBaseContext(), "Please select one option from each type !", Toast.LENGTH_LONG).show();
            } else {

                RadioButton checkedbutton = (RadioButton) findViewById(radioButtonID1);
                int radioId1 = rgrp1.getCheckedRadioButtonId();
                radioButton1 = checkedbutton.getText().toString();

                checkedbutton = (RadioButton) findViewById(radioButtonID2);
                radioButton2 = checkedbutton.getText().toString();
                int radioId2 = rgrp2.getCheckedRadioButtonId();

                checkedbutton = (RadioButton) findViewById(radioButtonID3);
                radioButton3 = checkedbutton.getText().toString();
                int radioId3 = rgrp3.getCheckedRadioButtonId();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Radiobutton1", radioButton1);
                editor.putString("Radiobutton2", radioButton2);
                editor.putString("Radiobutton3", radioButton3);
                editor.putInt("id1", radioId1);
                editor.putInt("id2", radioId2);
                editor.putInt("id3", radioId3);
                editor.putString("TrackingEyeballSkipped", "notskipped");
                editor.commit();
                skip_pressed = false;
                Intent intent = new Intent(this, RecordBalancing.class);
                intent.putExtra("activity", "nextbalancing");
                startActivity(intent);
            }

        }
        else{
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Radiobutton1","NA" );
            editor.putString("Radiobutton2", "NA");
            editor.putString("Radiobutton3", "NA");
            editor.putString("TrackingEyeballSkipped", "skipped");
            editor.commit();
            skip_pressed = true;
            Intent intent = new Intent(this, RecordBalancing.class);
            intent.putExtra("activity", "nextbalancing");
            startActivity(intent);

        }

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
