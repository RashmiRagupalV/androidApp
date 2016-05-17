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
 * Created by rashm on 4/18/2016.
 */
public class RecordSkipping extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

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
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(ctxt);
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_skipping);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SKIP = (Button)findViewById(R.id.buttonkskip);

        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

        next = (Button) findViewById(R.id.button1);
        txt1 = (TextView) findViewById(R.id.skipping);
        rgrp1 = (RadioGroup)findViewById(R.id.rgrpskipping1);
        rgrp1.clearCheck();
        skip_pressed = false;

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

                        for(int i = 0; i < rgrp1.getChildCount(); i++){
                            ((RadioButton)rgrp1.getChildAt(i)).setEnabled(false);
                            ((RadioButton)rgrp1.getChildAt(i)).setTextColor(Color.GRAY);
                            ((RadioButton)rgrp1.getChildAt(i)).setChecked(false);
                        }
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("Radiobuttonskipping", "NA");
                        editor.putString("SkippingSkipped", "skipped");
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
        else if((activity.equals("nextskipping")) || (activity.equals("prevskipping"))){

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor = sharedpreferences.edit();
            String check = sharedpreferences.getString("SkippingSkipped", "");
            if (check.equals("skipped")) {
                SKIP.setEnabled(false);
                skip_pressed = true;
                txt1.setEnabled(false);
                txt1.setTextColor(Color.GRAY);

                for(int i = 0; i < rgrp1.getChildCount(); i++){
                    ((RadioButton)rgrp1.getChildAt(i)).setEnabled(false);
                    ((RadioButton)rgrp1.getChildAt(i)).setTextColor(Color.GRAY);
                    ((RadioButton)rgrp1.getChildAt(i)).setChecked(false);
                }
             }
            else {
                int value1 = sharedpreferences.getInt("skippingid", 0);
                rgrp1 = (RadioGroup) findViewById(R.id.rgrpskipping1);
                rgrp1.check(value1);
                skip_pressed = false;
            }
        }

    }

    public void prevactivity_balancing(View view) {

        if(skip_pressed==true){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Radiobuttonskipping", "NA");
            editor.putString("SkippingSkipped", "skipped");
            editor.commit();
            Intent intent = new Intent(this, RecordBalancing.class);
            intent.putExtra("activity", "prevbalancing");
            startActivity(intent);
        }
        else {
            rgrp1 = (RadioGroup) findViewById(R.id.rgrpskipping1);
            radioButtonID1 = rgrp1.getCheckedRadioButtonId();
//            if (rgrp1.getCheckedRadioButtonId() == -1)
//                Toast.makeText(getBaseContext(), "Please select one option !", Toast.LENGTH_LONG).show();
//            else {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("skippingid", radioButtonID1);
                editor.putString("SkippingSkipped", "notskipped");
                editor.commit();
                Intent intent = new Intent(this, RecordBalancing.class);
                intent.putExtra("activity", "prevbalancing");
                startActivity(intent);
        //    }
        }
    }

    public void cancelRegistration(View view) {
        clearall();
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        startActivity(intent);
    }

    public void clearall(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }


    public void saveskippingdetails(View view) {
        Intent intent = new Intent(this, RecordSkipping.class);
        startActivity(intent);
    }

    public void nextactivity_teaming(View view){
        rgrp1 = (RadioGroup) findViewById(R.id.rgrpskipping1);
        radioButtonID1 = rgrp1.getCheckedRadioButtonId();
        if (!skip_pressed) {
            if (rgrp1.getCheckedRadioButtonId() <= 0)
                Toast.makeText(getBaseContext(), "Please select one option !", Toast.LENGTH_LONG).show();
            else {
                RadioButton checkedbutton = (RadioButton) findViewById(radioButtonID1);
                radioButton1 = checkedbutton.getText().toString();
                int skippingid = rgrp1.getCheckedRadioButtonId();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Radiobuttonskipping", radioButton1);
                editor.putString("SkippingSkipped", "notskipped");
                editor.putInt("skippingid", skippingid);
                editor.commit();
                skip_pressed = false;
                Intent intent = new Intent(this, RecordTeaming.class);
                intent.putExtra("activity", "nextteaming");
                startActivity(intent);
            }
        }
        else{
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Radiobuttonskipping", radioButton1);
            editor.putString("SkippingSkipped", "skipped");
            editor.commit();
            skip_pressed = true;
            Intent intent = new Intent(this, RecordTeaming.class);
            intent.putExtra("activity", "nextteaming");
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
