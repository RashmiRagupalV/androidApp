package redflag.capstone.com.redflag;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
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
import android.widget.Toast;
import android.app.AlertDialog;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Created by rashm on 4/10/2016.
 */
public class RecordBalancing extends Activity implements OnClickListener {

    private Chronometer chronometer,chronometer4,chronometer2,chronometer3;
    Button start1, stop1;
    String time1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balancing);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        chronometer = (Chronometer)findViewById(R.id.chronometer);
        chronometer2 = (Chronometer)findViewById(R.id.chronometer2);
        chronometer3 = (Chronometer)findViewById(R.id.chronometer3);
        chronometer4 = (Chronometer)findViewById(R.id.chronometer4);
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
        ((Button) findViewById(R.id.button6)).setEnabled(false);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button3:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                ((Button) findViewById(R.id.button3)).setEnabled(false);
                ((Button) findViewById(R.id.button6)).setEnabled(true);
                break;
            case R.id.button7:
                chronometer2.setBase(SystemClock.elapsedRealtime());
                chronometer2.start();
                ((Button) findViewById(R.id.button7)).setEnabled(false);
                ((Button) findViewById(R.id.button8)).setEnabled(true);
                break;
            case R.id.button9:
                chronometer3.setBase(SystemClock.elapsedRealtime());
                chronometer3.start();
                ((Button) findViewById(R.id.button9)).setEnabled(false);
                ((Button) findViewById(R.id.button10)).setEnabled(true);
                break;
            case R.id.button11:
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

                break;
            case R.id.button10:
                chronometer3.stop();
                ((Button) findViewById(R.id.button9)).setEnabled(true);
                ((Button) findViewById(R.id.button10)).setEnabled(false);
                break;
            case R.id.button12:
                chronometer4.stop();
                ((Button) findViewById(R.id.button11)).setEnabled(true);
                ((Button) findViewById(R.id.button12)).setEnabled(false);
                break;
        }

    }
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

}
