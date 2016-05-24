package redflag.capstone.com.redflag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rashm on 5/1/2016.
 */
public class RecordVisualDiscrimination extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private DatabaseOperations dBHelper;
    public static final String MyPREFERENCES = "MyPrefs" ;

    Context ctxt = this;
    Cursor cursor;
    RadioGroup rgrp1;
    int radioButtonID1;
    Button next;
    String radioButton1;
    SharedPreferences sharedpreferences;
    Button SKIP;
    boolean skip_pressed;
    TextView vd;
    String Studname;

    TextView studnm;
    String user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(ctxt);
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_visualdiscrimination);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Studname = sharedpreferences.getString("Studname","");
        studnm = (TextView)findViewById(R.id.studname);
        studnm.setText("Student: " + Studname);
        Bundle bundle = getIntent().getExtras();
        user_name = bundle.getString("TesterName");
//        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        //save = (Button) findViewById(R.id.button2);
        next = (Button) findViewById(R.id.button4);
        SKIP = (Button)findViewById(R.id.buttonkskip);
        vd = (TextView)findViewById(R.id.vd);
        rgrp1 = (RadioGroup)findViewById(R.id.rgrp1);

        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        skip_pressed = false;

        SKIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setMessage("Are you sure you want to skip this activity ?");

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SKIP.setEnabled(false);
                        skip_pressed = true;
                        vd.setEnabled(false);
                        vd.setTextColor(Color.GRAY);

                        for(int i = 0; i < rgrp1.getChildCount(); i++){
                            ((RadioButton)rgrp1.getChildAt(i)).setEnabled(false);
                            ((RadioButton)rgrp1.getChildAt(i)).setTextColor(Color.GRAY);
                            ((RadioButton)rgrp1.getChildAt(i)).setChecked(false);
                        }
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("Radiobuttonvd", "NA");
                        editor.putString("VDSkipped", "skipped");
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
        else if((activity.equals("nextvisualdiscrimination")) || (activity.equals("prevvisualdiscrimination"))) {

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor = sharedpreferences.edit();
            String check = sharedpreferences.getString("VDSkipped", "");
            if (check.equals("skipped")) {
                SKIP.setEnabled(false);
                skip_pressed = true;
                vd.setEnabled(false);
                vd.setTextColor(Color.GRAY);

                for(int i = 0; i < rgrp1.getChildCount(); i++){
                    ((RadioButton)rgrp1.getChildAt(i)).setEnabled(false);
                    ((RadioButton)rgrp1.getChildAt(i)).setTextColor(Color.GRAY);
                    ((RadioButton)rgrp1.getChildAt(i)).setChecked(false);
                }
            } else {
                int value1 = sharedpreferences.getInt("vdid", 0);
                rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
                rgrp1.check(value1);
                skip_pressed = false;
            }
        }
    }


    public void prevactivity_teaming(View view){

        if(skip_pressed==true) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Radiobuttonvd", "NA");
            editor.putString("VDSkipped", "skipped");
            editor.commit();
            Intent intent = new Intent(this, RecordTeaming.class);
            intent.putExtra("activity", "prevteaming");
            intent.putExtra("TesterName",user_name);
            startActivity(intent);
        }else {
            rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
            radioButtonID1 = rgrp1.getCheckedRadioButtonId();
//            if (radioButtonID1 <= 0)
//                Toast.makeText(getBaseContext(), "Please select one option !", Toast.LENGTH_LONG).show();
//            else {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("VDSkipped", "notskipped");
                editor.putInt("vdid", radioButtonID1);
                editor.commit();
                Intent intent = new Intent(this, RecordTeaming.class);
                intent.putExtra("activity", "prevteaming");
            intent.putExtra("TesterName",user_name);
                startActivity(intent);
           // }
        }
    }


    public void nextactivity_crawling(View view){
        rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
        radioButtonID1 = rgrp1.getCheckedRadioButtonId();
        if (!skip_pressed) {
            if (radioButtonID1 <= 0) {
             //   Toast.makeText(getBaseContext(), "Please select one option !", Toast.LENGTH_LONG).show();
                radioButton1 = "NA";
                radioButtonID1 = 0;
            }
            else {
                RadioButton checkedbutton = (RadioButton) findViewById(radioButtonID1);
                radioButton1 = checkedbutton.getText().toString();
                int vdid = rgrp1.getCheckedRadioButtonId();
            }
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Radiobuttonvd", radioButton1);
                editor.putInt("vdid", radioButtonID1);
                editor.putString("VDSkipped", "notskipped");
                editor.commit();
                skip_pressed = false;
                Intent intent = new Intent(this, RecordCrawling.class);
                intent.putExtra("activity", "nextcrawling");
            intent.putExtra("TesterName",user_name);
                startActivity(intent);
        //    }
        }
        else{
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Radiobuttonvd", radioButton1);
            editor.putString("VDSkipped", "skipped");
            editor.commit();
            Intent intent = new Intent(this, RecordCrawling.class);
            intent.putExtra("activity", "nextcrawling");
            intent.putExtra("TesterName",user_name);
            startActivity(intent);
        }
    }

    public void cancelRegistration(View view) {
        clearall();
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        intent.putExtra("TesterName",user_name);
        startActivity(intent);
    }

    public void clearall(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
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