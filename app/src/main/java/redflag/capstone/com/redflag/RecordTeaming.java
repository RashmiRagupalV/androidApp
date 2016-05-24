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

import org.w3c.dom.Text;

/**
 * Created by rashm on 5/1/2016.
 */
public class RecordTeaming extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener  {

    public static final String MyPREFERENCES = "MyPrefs" ;

    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;
    RadioGroup rgrp1,rgrp2;
    int radioButtonID1, radioButtonID2;
    Button next;
    String radioButton1, radioButton2;
    SharedPreferences sharedpreferences;
    Button SKIP;
    TextView txt1,txt2;
    boolean skip_pressed;
    String Studname;
    String user_name;
    TextView studnm;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(ctxt);
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_teaming);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Studname = sharedpreferences.getString("Studname","");
        studnm = (TextView)findViewById(R.id.studname);
        studnm.setText("Student: " + Studname);
        next = (Button) findViewById(R.id.button4);
        Bundle bundle = getIntent().getExtras();
        user_name = bundle.getString("TesterName");
        SKIP = (Button)findViewById(R.id.buttonkskip);

        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        skip_pressed = false;

        txt1 = (TextView)findViewById(R.id.teaming);
        txt2 = (TextView)findViewById(R.id.teaming2);
        rgrp1 = (RadioGroup)findViewById(R.id.rgrp1);
        rgrp2 = (RadioGroup)findViewById(R.id.rgrp2);

        Intent intent = getIntent();
        String activity = intent.getStringExtra("activity");
        if (activity == null || activity.length()==0){

        }
        else if ((activity.equals("nextteaming")) || (activity.equals("prevteaming"))){

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor = sharedpreferences.edit();
            String check = sharedpreferences.getString("Teamingskipped", "");
            if (check.equals("skipped")) {
                SKIP.setEnabled(false);
                skip_pressed = true;
                txt1.setEnabled(false);
                txt1.setTextColor(Color.GRAY);

                txt2.setEnabled(false);
                txt2.setTextColor(Color.GRAY);

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
            }
            else {
                int value1 = sharedpreferences.getInt("teamingidround1", 0);
                int value2 = sharedpreferences.getInt("teamingidround2", 0);

                rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
                rgrp1.check(value1);
                rgrp2 = (RadioGroup) findViewById(R.id.rgrp2);
                rgrp2.check(value2);
                skip_pressed = false;
            }
        }

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
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("Radiobuttonteaming1", "NA");
                        editor.putString("Radiobuttonteaming2", "NA");
                        editor.putString("Teamingskipped", "skipped");
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

    }

    public void nextactivity_visualdiscrimination(View view) {
        rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
        radioButtonID1 = rgrp1.getCheckedRadioButtonId();
        rgrp2 = (RadioGroup) findViewById(R.id.rgrp2);
        radioButtonID2 = rgrp2.getCheckedRadioButtonId();
        int radioId1 =0;
        int radioId2 =0;
        if (!skip_pressed) {
//            if (radioButtonID1 <= 0 || radioButtonID2 <= 0) {
//                // Toast.makeText(getBaseContext(), "Please select one option from each type !", Toast.LENGTH_LONG).show();
//                if(radioButtonID1 <= 0)
//                    radioButton1 = "NA";
//                if(radioButtonID2 <= 0)
//                     radioButton2 ="NA";
//            }
//            else {
                RadioButton checkedbutton = (RadioButton) findViewById(radioButtonID1);
                radioId1 = rgrp1.getCheckedRadioButtonId();
                 if(radioId1 <= 0)
                   radioButton1 = "NA";
                 else
                     radioButton1 = checkedbutton.getText().toString();

                 RadioButton checkedbutton1 = (RadioButton) findViewById(radioButtonID2);
                radioId2 = rgrp2.getCheckedRadioButtonId();

                if(radioId2 <= 0)
                    radioButton2 = "NA";
                else
                    radioButton2 = checkedbutton1.getText().toString();

       //     }
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Radiobuttonteaming1", radioButton1);
                editor.putString("Radiobuttonteaming2", radioButton2);
                editor.putInt("teamingidround1", radioId1);
                editor.putInt("teamingidround2", radioId2);
                editor.putString("Teamingskipped", "notskipped");
                editor.commit();
                Intent intent = new Intent(this, RecordVisualDiscrimination.class);
                intent.putExtra("activity", "nextvisualdiscrimination");
            intent.putExtra("TesterName",user_name);
                startActivity(intent);

        //    }
        }else{
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Radiobuttonteaming1", "NA");
            editor.putString("Radiobuttonteaming2", "NA");
            editor.putString("Teamingskipped", "skipped");
            editor.commit();
            Intent intent = new Intent(this, RecordVisualDiscrimination.class);
            intent.putExtra("activity", "nextvisualdiscrimination");
            intent.putExtra("TesterName",user_name);
            startActivity(intent);

        }
    }

    public void prevactivity_skipping(View view){

        if(skip_pressed==true){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Radiobuttonteaming1", "NA");
            editor.putString("Radiobuttonteaming2", "NA");
            editor.putString("Teamingskipped", "skipped");
            editor.commit();
            Intent intent = new Intent(this, RecordSkipping.class);
            intent.putExtra("activity", "prevskipping");
            intent.putExtra("TesterName",user_name);
            startActivity(intent);
        }
        else {
            rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
            radioButtonID1 = rgrp1.getCheckedRadioButtonId();

            rgrp2 = (RadioGroup) findViewById(R.id.rgrp2);
            radioButtonID2 = rgrp2.getCheckedRadioButtonId();
//            if (radioButtonID1 == -1 || radioButtonID2 == -1)
//                Toast.makeText(getBaseContext(), "Please select one option from each type !", Toast.LENGTH_LONG).show();
//            else {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("teamingidround1", radioButtonID1);
                editor.putInt("teamingidround2", radioButtonID2);
                editor.putString("Teamingskipped", "notskipped");
                editor.commit();
                Intent intent = new Intent(this, RecordSkipping.class);
                intent.putExtra("activity", "prevskipping");
            intent.putExtra("TesterName",user_name);
                startActivity(intent);
            //}
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
