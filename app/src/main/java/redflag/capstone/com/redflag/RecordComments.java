package redflag.capstone.com.redflag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
 * Created by rashm on 4/18/2016.
 */
public class RecordComments extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener  {

    private DatabaseOperations dBHelper;
    public static final String MyPREFERENCES = "MyPrefs" ;
    Context ctxt = this;
    Cursor cursor;
    Button save, prev;
    EditText ecomments;
    TextView txtview;
    String scomments;
    SharedPreferences sharedpreferences;
    Button SKIP;
    boolean skip_pressed;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(ctxt);
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_comments);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        save = (Button) findViewById(R.id.button2);
        prev = (Button) findViewById(R.id.button3);
//       next = (Button) findViewById(R.id.button4);
        SKIP = (Button)findViewById(R.id.buttonkskip);
        ecomments = (EditText)findViewById(R.id.editText1);

        skip_pressed = false;

        ecomments = (EditText) findViewById(R.id.editText1);
        ecomments.setEnabled(true);

        txtview = (TextView)findViewById(R.id.textView1);
        save.setEnabled(true);
        prev.setEnabled(true);

        SKIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setMessage("Are you sure you want to skip this activity ?");

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SKIP.setEnabled(false);
                        skip_pressed = true;
                        ecomments.setEnabled(false);
                        ecomments.setBackgroundColor(Color.GRAY);
                        ecomments.setText("");
                        ecomments.setHint("");

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("comments", "NA");
                        editor.putString("CommentsSkipped", "skipped");
                        editor.commit();
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(ecomments.getWindowToken(), 0);

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




        final TextWatcher mTextEditorWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //This sets a textview to the current length
                txtview.setText(("Characters left " + (100 - s.toString().length()) + "/100"));
            }

            public void afterTextChanged(Editable s) {
            }
        };

        ecomments.addTextChangedListener(mTextEditorWatcher);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(skip_pressed){
                    scomments = "NA";
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ecomments.getWindowToken(), 0);

                    alertDialog.setMessage("Are you sure you want to Submit?");

                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            String studId, radioButton1, radioButton2, radioButton3;
                            studId = sharedpreferences.getString("StudId", "");
                            radioButton1 = sharedpreferences.getString("Radiobutton1", "");
                            radioButton2 = sharedpreferences.getString("Radiobutton2", "");
                            radioButton3 = sharedpreferences.getString("Radiobutton3", "");
                            dBHelper.insertStudentTrackingDetails(dBHelper, studId, radioButton1, radioButton2, radioButton3);


                            String time1, time2, time3, time4;
                            time1 = sharedpreferences.getString("time1", "");
                            time2 = sharedpreferences.getString("time2", "");
                            time3 = sharedpreferences.getString("time3", "");
                            time4 = sharedpreferences.getString("time4", "");
                            dBHelper.insertStudentBalancingDetails(dBHelper, studId, time1, time2, time3, time4);


                            String Radiobuttonskipping;
                            Radiobuttonskipping = sharedpreferences.getString("Radiobuttonskipping", "");
                            dBHelper.insertStudentSkippingDetails(dBHelper, studId, Radiobuttonskipping);

                            String Radiobuttonteaming1, Radiobuttonteaming2;
                            Radiobuttonteaming1 = sharedpreferences.getString("Radiobuttonteaming1", "");
                            Radiobuttonteaming2 = sharedpreferences.getString("Radiobuttonteaming1", "");
                            dBHelper.insertStudentTeamingDetails(dBHelper, studId, Radiobuttonteaming1, Radiobuttonteaming2);

                            String Radiobuttonvd;
                            Radiobuttonvd = sharedpreferences.getString("Radiobuttonvd", "");
                            dBHelper.insertStudentVDDetails(dBHelper, studId, Radiobuttonvd);

                            String Crawlingtext;
                            Crawlingtext = sharedpreferences.getString("Crawlingtext", "");
                            dBHelper.insertStudentCrawlingDetails(dBHelper, studId, Crawlingtext);

                            dBHelper.insertStudentComments(dBHelper, studId, scomments);

                            save.setEnabled(false);
                            prev.setEnabled(false);
                            SKIP.setEnabled(false);
                            ecomments.setEnabled(false);
                            editor.remove("Radiobutton1");
                            editor.remove("Radiobutton2");
                            editor.remove("Radiobutton3");
                            editor.remove("time1");
                            editor.remove("time2");
                            editor.remove("time3");
                            editor.remove("time4");
                            editor.remove("Radiobuttonskipping");
                            editor.remove("Radiobuttonteaming1");
                            editor.remove("Radiobuttonteaming2");
                            editor.remove("Radiobuttonvd");
                            editor.remove("Crawlingtext");
                            editor.remove("comments");
                            editor.clear();
                            editor.commit();
                        }
                    });
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });
                    alertDialog.show();

                }else {
                    scomments = ecomments.getText().toString();
                    if (scomments.isEmpty()) {
                        Toast.makeText(getBaseContext(), "Please enter some comments !", Toast.LENGTH_LONG).show();
                    } else {
                        alertDialog.setMessage("Are you sure you want to Submit?");


                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                String studId, radioButton1, radioButton2, radioButton3;
                                studId = sharedpreferences.getString("StudId", "");
                                radioButton1 = sharedpreferences.getString("Radiobutton1", "");
                                radioButton2 = sharedpreferences.getString("Radiobutton2", "");
                                radioButton3 = sharedpreferences.getString("Radiobutton3", "");
                                dBHelper.insertStudentTrackingDetails(dBHelper, studId, radioButton1, radioButton2, radioButton3);


                                String time1, time2, time3, time4;
                                time1 = sharedpreferences.getString("time1", "");
                                time2 = sharedpreferences.getString("time2", "");
                                time3 = sharedpreferences.getString("time3", "");
                                time4 = sharedpreferences.getString("time4", "");
                                dBHelper.insertStudentBalancingDetails(dBHelper, studId, time1, time2, time3, time4);


                                String Radiobuttonskipping;
                                Radiobuttonskipping = sharedpreferences.getString("Radiobuttonskipping", "");
                                dBHelper.insertStudentSkippingDetails(dBHelper, studId, Radiobuttonskipping);

                                String Radiobuttonteaming1, Radiobuttonteaming2;
                                Radiobuttonteaming1 = sharedpreferences.getString("Radiobuttonteaming1", "");
                                Radiobuttonteaming2 = sharedpreferences.getString("Radiobuttonteaming1", "");
                                dBHelper.insertStudentTeamingDetails(dBHelper, studId, Radiobuttonteaming1, Radiobuttonteaming2);

                                String Radiobuttonvd;
                                Radiobuttonvd = sharedpreferences.getString("Radiobuttonvd", "");
                                dBHelper.insertStudentVDDetails(dBHelper, studId, Radiobuttonvd);

                                String Crawlingtext;
                                Crawlingtext = sharedpreferences.getString("Crawlingtext", "");
                                dBHelper.insertStudentCrawlingDetails(dBHelper, studId, Crawlingtext);

                                dBHelper.insertStudentComments(dBHelper, studId, scomments);

                                save.setEnabled(false);
                                prev.setEnabled(false);
                                SKIP.setEnabled(false);
                                ecomments.setEnabled(false);
                                editor.remove("Radiobutton1");
                                editor.remove("Radiobutton2");
                                editor.remove("Radiobutton3");
                                editor.remove("time1");
                                editor.remove("time2");
                                editor.remove("time3");
                                editor.remove("time4");
                                editor.remove("Radiobuttonskipping");
                                editor.remove("Radiobuttonteaming1");
                                editor.remove("Radiobuttonteaming2");
                                editor.remove("Radiobuttonvd");
                                editor.remove("Crawlingtext");
                                editor.remove("comments");


                                editor.clear();
                                editor.commit();
                            }
                        });
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });
                        alertDialog.show();


                    }
                }
            }

        });

        Intent intent = getIntent();
        String activity = intent.getStringExtra("activity");
        if (activity == null || activity.length()==0){

        }
        else if((activity.equals("nextcomments"))){

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor = sharedpreferences.edit();
            String check = sharedpreferences.getString("CommentsSkipped", "");
            if(check.equals("skipped")){
                SKIP.setEnabled(false);
                skip_pressed = true;
                ecomments.setEnabled(false);
                ecomments.setBackgroundColor(Color.GRAY);
                ecomments.setText("");
                ecomments.setHint("");
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ecomments.getWindowToken(), 0);

            }else {
                String value1 = sharedpreferences.getString("comments", "");
                ecomments.setText(value1);
                skip_pressed = false;
            }
        }

    }

    public void prevactivity_crawling(View view) {

        if(skip_pressed==true) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("comments", "NA");
            editor.putString("CommentsSkipped", "skipped");
            editor.commit();
            Intent intent = new Intent(this, RecordCrawling.class);
            intent.putExtra("activity", "prevcrawling");
            startActivity(intent);
        }else{
            String comments = ecomments.getText().toString();
            if ((comments == null) || (comments.length() == 0)) {
                Intent intent = new Intent(this, RecordCrawling.class);
                intent.putExtra("activity", "prevcrawling");
                startActivity(intent);
            } else {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("comments", comments);
                editor.putString("CommentsSkipped", "notskipped");
                editor.commit();
                Intent intent = new Intent(this, RecordCrawling.class);
                intent.putExtra("activity", "prevcrawling");
                startActivity(intent);
            }
        }
       }

//    public void homescreen(View view){
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.remove("Radiobutton1");
//        editor.remove("Radiobutton2");
//        editor.remove("Radiobutton3");
//        editor.remove("time1");
//        editor.remove("time2");
//        editor.remove("time3");
//        editor.remove("time4");
//        editor.remove("Radiobuttonskipping");
//        editor.remove("Radiobuttonteaming1");
//        editor.remove("Radiobuttonteaming2");
//        editor.remove("Radiobuttonvd");
//        editor.remove("Crawlingtext");
//        editor.remove("comments");
//
//
//        editor.clear();
//        editor.commit();
//        Intent intent = new Intent(this, LoginSuccessActivity.class);
//        startActivity(intent);
//    }

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
