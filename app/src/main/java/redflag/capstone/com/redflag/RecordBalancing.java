package redflag.capstone.com.redflag;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Created by rashm on 4/10/2016.
 */
public class RecordBalancing extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static final String MyPREFERENCES = "MyPrefs" ;
    private Chronometer chronometer,chronometer4,chronometer2,chronometer3;
    Button start1, stop1;
    String time1, time2, time3, time4;
    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;
    Button prev, next;
    Button SKIP;
    TextView txt1,txt2,txt3,txt4;
    boolean skip_pressed;
    String Studname;

    TextView studnm;
    String user_name;


    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(ctxt);
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_balancing);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Studname = sharedpreferences.getString("Studname","");
        studnm = (TextView)findViewById(R.id.studname);
        studnm.setText("Student: " + Studname);
        Bundle bundle = getIntent().getExtras();
        user_name = bundle.getString("TesterName");
       // editor.remove("BalancingSkipped");
        txt1 = (TextView)findViewById(R.id.opr);
        txt2 = (TextView)findViewById(R.id.opl);
        txt3 = (TextView)findViewById(R.id.clr);
        txt4 = (TextView)findViewById(R.id.cll);

        chronometer = (Chronometer)findViewById(R.id.chronometer);
        skip_pressed = false;
        chronometer2 = (Chronometer)findViewById(R.id.chronometer2);
        chronometer3 = (Chronometer)findViewById(R.id.chronometer3);
        chronometer4 = (Chronometer)findViewById(R.id.chronometer4);
        chronometer.setText("");
        chronometer2.setText("");
        chronometer3.setText("");
        chronometer4.setText("");
        SKIP = (Button)findViewById(R.id.buttonkskip);

        ((Button) findViewById(R.id.button3)).setOnClickListener(this);
        ((Button) findViewById(R.id.button6)).setOnClickListener(this);
        ((Button) findViewById(R.id.button6)).setEnabled(false);
        ((Button) findViewById(R.id.button7)).setOnClickListener(this);
        ((Button) findViewById(R.id.button8)).setOnClickListener(this);
        ((Button) findViewById(R.id.button8)).setEnabled(false);
        ((Button) findViewById(R.id.button9)).setOnClickListener(this);
        ((Button) findViewById(R.id.button10)).setOnClickListener(this);
        ((Button) findViewById(R.id.button10)).setEnabled(false);
        ((Button) findViewById(R.id.button11)).setOnClickListener(this);
        ((Button) findViewById(R.id.button12)).setOnClickListener(this);
        ((Button) findViewById(R.id.button12)).setEnabled(false);

       // save = (Button) findViewById(R.id.button2);
        next = (Button) findViewById(R.id.button1);


        Intent intent = getIntent();
        intent.putExtra("TesterName",user_name);
        String activity = intent.getStringExtra("activity");
        if (activity == null || activity.length()==0){
            chronometer.setText("00:00:00");
            chronometer2.setText("00:00:00");
            chronometer3.setText("00:00:00");
            chronometer4.setText("00:00:00");
        }
         else if ((activity.equals("nextbalancing")) || (activity.equals("prevbalancing"))) {

            editor = sharedpreferences.edit();
            String check = sharedpreferences.getString("BalancingSkipped", "");
            if (check.equals("skipped")) {
                SKIP.setEnabled(false);
                skip_pressed = true;
                txt1.setEnabled(false);
                txt1.setTextColor(Color.DKGRAY);
                txt2.setEnabled(false);
                txt2.setTextColor(Color.DKGRAY);

                txt3.setEnabled(false);
                txt3.setTextColor(Color.DKGRAY);

                txt4.setEnabled(false);
                txt4.setTextColor(Color.DKGRAY);
                chronometer.setText("00:00:00");
                chronometer2.setText("00:00:00");
                chronometer3.setText("00:00:00");
                chronometer4.setText("00:00:00");
                chronometer.setTextColor(Color.DKGRAY);
                chronometer2.setTextColor(Color.DKGRAY);
                chronometer3.setTextColor(Color.DKGRAY);
                chronometer4.setTextColor(Color.DKGRAY);

                ((Button) findViewById(R.id.button3)).setEnabled(false);
                ((Button) findViewById(R.id.button6)).setEnabled(false);
                ((Button) findViewById(R.id.button7)).setEnabled(false);
                ((Button) findViewById(R.id.button8)).setEnabled(false);
                ((Button) findViewById(R.id.button9)).setEnabled(false);
                ((Button) findViewById(R.id.button10)).setEnabled(false);
                ((Button) findViewById(R.id.button11)).setEnabled(false);
                ((Button) findViewById(R.id.button12)).setEnabled(false);



            } else {
                String value1 = sharedpreferences.getString("time1", "00:00:00");
                String value2 = sharedpreferences.getString("time2", "00:00:00");
                String value3 = sharedpreferences.getString("time3", "00:00:00");
                String value4 = sharedpreferences.getString("time4", "00:00:00");
                chronometer.setText(value1);
                chronometer2.setText(value2);
                chronometer3.setText(value3);
                chronometer4.setText(value4);
                skip_pressed=false;
            }
        }

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
                        txt1.setTextColor(Color.DKGRAY);
                        txt2.setEnabled(false);
                        txt2.setTextColor(Color.DKGRAY);

                        txt3.setEnabled(false);
                        txt3.setTextColor(Color.DKGRAY);

                        txt4.setEnabled(false);
                        txt4.setTextColor(Color.DKGRAY);
                        chronometer.setText("00:00:00");
                        chronometer2.setText("00:00:00");
                        chronometer3.setText("00:00:00");
                        chronometer4.setText("00:00:00");
                        chronometer.setTextColor(Color.DKGRAY);
                        chronometer2.setTextColor(Color.DKGRAY);
                        chronometer3.setTextColor(Color.DKGRAY);
                        chronometer4.setTextColor(Color.DKGRAY);

                        ((Button) findViewById(R.id.button3)).setEnabled(false);
                        ((Button) findViewById(R.id.button6)).setEnabled(false);
                        ((Button) findViewById(R.id.button7)).setEnabled(false);
                        ((Button) findViewById(R.id.button8)).setEnabled(false);
                        ((Button) findViewById(R.id.button9)).setEnabled(false);
                        ((Button) findViewById(R.id.button10)).setEnabled(false);
                        ((Button) findViewById(R.id.button11)).setEnabled(false);
                        ((Button) findViewById(R.id.button12)).setEnabled(false);

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("time1", "NA");
                        editor.putString("time2","NA" );
                        editor.putString("time3", "NA");
                        editor.putString("time4", "NA");
                        editor.putString("BalancingSkipped", "skipped");
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button3:
                chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
                    @Override
                    public void onChronometerTick(Chronometer cArg) {
                        long time = SystemClock.elapsedRealtime() - cArg.getBase();
                        int h   = (int)(time /3600000);
                        int m = (int)(time - h*3600000)/60000;
                        int s= (int)(time - h*3600000- m*60000)/1000 ;
                        String hh = h < 10 ? "0"+h: h+"";
                        String mm = m < 10 ? "0"+m: m+"";
                        String ss = s < 10 ? "0"+s: s+"";
                        cArg.setText(hh+":"+mm+":"+ss);
                    }
                });
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                ((Button) findViewById(R.id.button3)).setEnabled(false);
                ((Button) findViewById(R.id.button6)).setEnabled(true);
                break;
            case R.id.button7:
                chronometer2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
                    @Override
                    public void onChronometerTick(Chronometer cArg) {
                        long time = SystemClock.elapsedRealtime() - cArg.getBase();
                        int h   = (int)(time /3600000);
                        int m = (int)(time - h*3600000)/60000;
                        int s= (int)(time - h*3600000- m*60000)/1000 ;
                        String hh = h < 10 ? "0"+h: h+"";
                        String mm = m < 10 ? "0"+m: m+"";
                        String ss = s < 10 ? "0"+s: s+"";
                        cArg.setText(hh+":"+mm+":"+ss);
                    }
                });
                chronometer2.setBase(SystemClock.elapsedRealtime());
                chronometer2.start();
                ((Button) findViewById(R.id.button7)).setEnabled(false);
                ((Button) findViewById(R.id.button8)).setEnabled(true);
                break;
            case R.id.button9:
                chronometer3.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
                    @Override
                    public void onChronometerTick(Chronometer cArg) {
                        long time = SystemClock.elapsedRealtime() - cArg.getBase();
                        int h   = (int)(time /3600000);
                        int m = (int)(time - h*3600000)/60000;
                        int s= (int)(time - h*3600000- m*60000)/1000 ;
                        String hh = h < 10 ? "0"+h: h+"";
                        String mm = m < 10 ? "0"+m: m+"";
                        String ss = s < 10 ? "0"+s: s+"";
                        cArg.setText(hh+":"+mm+":"+ss);
                    }
                });
                chronometer3.setBase(SystemClock.elapsedRealtime());
                chronometer3.start();
                ((Button) findViewById(R.id.button9)).setEnabled(false);
                ((Button) findViewById(R.id.button10)).setEnabled(true);
                break;
            case R.id.button11:
                chronometer4.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
                    @Override
                    public void onChronometerTick(Chronometer cArg) {
                        long time = SystemClock.elapsedRealtime() - cArg.getBase();
                        int h   = (int)(time /3600000);
                        int m = (int)(time - h*3600000)/60000;
                        int s= (int)(time - h*3600000- m*60000)/1000 ;
                        String hh = h < 10 ? "0"+h: h+"";
                        String mm = m < 10 ? "0"+m: m+"";
                        String ss = s < 10 ? "0"+s: s+"";
                        cArg.setText(hh+":"+mm+":"+ss);
                    }
                });
                chronometer4.setBase(SystemClock.elapsedRealtime());
                chronometer4.start();
                ((Button) findViewById(R.id.button11)).setEnabled(false);
                ((Button) findViewById(R.id.button12)).setEnabled(true);

                break;
            case R.id.button6:
               chronometer.stop();
                ((Button) findViewById(R.id.button3)).setEnabled(true);
                ((Button) findViewById(R.id.button6)).setEnabled(false);
                time1 = chronometer.getText().toString();
                break;
            case R.id.button8:
                chronometer2.stop();
                ((Button) findViewById(R.id.button7)).setEnabled(true);
                ((Button) findViewById(R.id.button8)).setEnabled(false);
                time2 = chronometer2.getText().toString();

                break;
            case R.id.button10:
                chronometer3.stop();
                ((Button) findViewById(R.id.button9)).setEnabled(true);
                ((Button) findViewById(R.id.button10)).setEnabled(false);
                time3 = chronometer3.getText().toString();

                break;
            case R.id.button12:
                chronometer4.stop();
                ((Button) findViewById(R.id.button11)).setEnabled(true);
                ((Button) findViewById(R.id.button12)).setEnabled(false);
                time4 = chronometer4.getText().toString();
                break;
        }

    }



    public void savebalancingeyeball(View view) {
        Intent intent = new Intent(this, RecordBalancing.class);
        intent.putExtra("TesterName",user_name);
        startActivity(intent);
    }

    public void nextactivity_skipping(View view) {
        time1 = chronometer.getText().toString();
        time2 = chronometer2.getText().toString();
        time3 = chronometer3.getText().toString();
        time4 = chronometer4.getText().toString();

        if (!skip_pressed) {
            if (time1 == null || time2 == null || time3 == null || time4 == null)
                Toast.makeText(getBaseContext(), "Please complete all the parameters !", Toast.LENGTH_LONG).show();
            else if (((Button) findViewById(R.id.button6)).isEnabled() || ((Button) findViewById(R.id.button8)).isEnabled()
                    || ((Button) findViewById(R.id.button10)).isEnabled() || ((Button) findViewById(R.id.button12)).isEnabled()) {
                Toast.makeText(getBaseContext(), "Please complete all the activities in progress !", Toast.LENGTH_LONG).show();

            } else {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("time1", time1);
                editor.putString("time2", time2);
                editor.putString("time3", time3);
                editor.putString("time4", time4);
                editor.putString("BalancingSkipped", "notskipped");
                editor.commit();
                skip_pressed = false;
                Intent intent = new Intent(this, RecordSkipping.class);
                intent.putExtra("activity", "nextskipping");
                intent.putExtra("TesterName",user_name);
                startActivity(intent);
            }
        }
        else{
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("time1", "NA");
            editor.putString("time2", "NA");
            editor.putString("time3", "NA");
            editor.putString("time4", "NA");
            editor.putString("BalancingSkipped", "skipped");
            editor.commit();
            skip_pressed = true;
            Intent intent = new Intent(this, RecordSkipping.class);
            intent.putExtra("activity", "nextskipping");
            intent.putExtra("TesterName",user_name);
            startActivity(intent);
        }
    }

    public void prevactivity_eyetracking(View view) {

        if (((Button) findViewById(R.id.button6)).isEnabled() || ((Button) findViewById(R.id.button8)).isEnabled()
                || ((Button) findViewById(R.id.button10)).isEnabled() || ((Button) findViewById(R.id.button12)).isEnabled()) {
            Toast.makeText(getBaseContext(), "Please complete all the activities in progress !", Toast.LENGTH_LONG).show();
        }
        else{
            SharedPreferences.Editor editor = sharedpreferences.edit();
            time1 = chronometer.getText().toString();
            editor.putString("time1",time1 );
            time2 = chronometer2.getText().toString();
            editor.putString("time2",time2 );
            time3 = chronometer3.getText().toString();
            editor.putString("time3", time3);
            time4 = chronometer4.getText().toString();
            editor.putString("time4",time4 );
            editor.commit();
            Intent intent = new Intent(this, RecordTrackingEyeball.class);
            intent.putExtra("activity", "prevtracking");
            intent.putExtra("TesterName",user_name);
            startActivity(intent);
            }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void clearall(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove("time1");
        editor.remove("time2");
        editor.remove("time3");
        editor.remove("time4");
        editor.remove("BalancingSkipped");

       // editor.clear();
        editor.commit();
    }
    public void cancelRegistration(View view) {
        clearall();
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        intent.putExtra("TesterName",user_name);
        startActivity(intent);
    }



}
