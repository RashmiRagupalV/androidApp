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
public class RecordCrawling extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

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
    TextView tc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(ctxt);
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_crawling);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


//        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        //save = (Button) findViewById(R.id.button2);
        //next = (Button) findViewById(R.id.button1);

        next = (Button) findViewById(R.id.button4);
        SKIP = (Button)findViewById(R.id.buttonkskip);
        tc = (TextView)findViewById(R.id.tc);
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
                        tc.setEnabled(false);
                        tc.setTextColor(Color.GRAY);

                        for(int i = 0; i < rgrp1.getChildCount(); i++){
                            ((RadioButton)rgrp1.getChildAt(i)).setEnabled(false);
                            ((RadioButton)rgrp1.getChildAt(i)).setTextColor(Color.GRAY);
                            ((RadioButton)rgrp1.getChildAt(i)).setChecked(false);
                        }
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("crawlingid", "NA");
                        editor.putString("CrawlingSkipped", "skipped");
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
        else if((activity.equals("nextcrawling")) || (activity.equals("prevcrawling"))) {

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor = sharedpreferences.edit();
            String check = sharedpreferences.getString("CrawlingSkipped", "");
            if (check.equals("skipped")) {
                SKIP.setEnabled(false);
                skip_pressed = true;
                tc.setEnabled(false);
                tc.setTextColor(Color.GRAY);

                for(int i = 0; i < rgrp1.getChildCount(); i++){
                    ((RadioButton)rgrp1.getChildAt(i)).setEnabled(false);
                    ((RadioButton)rgrp1.getChildAt(i)).setTextColor(Color.GRAY);
                    ((RadioButton)rgrp1.getChildAt(i)).setChecked(false);
                }
            } else {

                int value1 = sharedpreferences.getInt("crawlingid", 0);
                rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
                rgrp1.check(value1);
                skip_pressed = false;
            }
        }
    }


    public void prevactivity_visualdisc(View view){

        if(skip_pressed==true) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("crawling", "NA");
            editor.putString("CrawlingSkipped", "skipped");
            editor.commit();
            Intent intent = new Intent(this, RecordVisualDiscrimination.class);
            intent.putExtra("activity", "prevvisualdiscrimination");
            startActivity(intent);
        }else {
            rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
            radioButtonID1 = rgrp1.getCheckedRadioButtonId();
//            if (radioButtonID1 <= 0)
//                Toast.makeText(getBaseContext(), "Please select one option !", Toast.LENGTH_LONG).show();
//            else {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("crawlingid", radioButtonID1);
                editor.commit();
                Intent intent = new Intent(this, RecordVisualDiscrimination.class);
                intent.putExtra("activity", "prevvisualdiscrimination");
                startActivity(intent);
           // }
        }
    }


    public void nextactivity_comments(View view){
        rgrp1 = (RadioGroup) findViewById(R.id.rgrp1);
        radioButtonID1 = rgrp1.getCheckedRadioButtonId();
        if (!skip_pressed) {

            if (radioButtonID1 <= 0)
                Toast.makeText(getBaseContext(), "Please select one option !", Toast.LENGTH_LONG).show();
            else {
                RadioButton checkedbutton = (RadioButton) findViewById(radioButtonID1);
                radioButton1 = checkedbutton.getText().toString();
                int crawlingid = rgrp1.getCheckedRadioButtonId();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Crawlingtext", radioButton1);
                editor.putString("CrawlingSkipped", "notskipped");
                editor.putInt("crawlingid", crawlingid);
                editor.commit();

                Intent intent = new Intent(this, RecordComments.class);
                intent.putExtra("activity", "nextcomments");
                startActivity(intent);
            }
        }else{
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Crawlingtext", "NA");
            editor.putString("CrawlingSkipped", "skipped");
            editor.commit();
            Intent intent = new Intent(this, RecordComments.class);
            intent.putExtra("activity", "nextcrawling");
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