package redflag.capstone.com.redflag;

import android.content.Intent;
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
    Context ctxt = this;
    Cursor cursor;
    Button save, next;
    EditText ecomments;
    TextView txtview;
    String scomments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(ctxt);
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_comments);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        save = (Button) findViewById(R.id.button2);
        next = (Button) findViewById(R.id.button1);

        next.setEnabled(false);
        ecomments = (EditText) findViewById(R.id.editText1);
        txtview = (TextView)findViewById(R.id.textView1);

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
                Intent intent = getIntent();
                Bundle b = intent.getExtras();

                final String studId = (String) b.get("SelectedstudId");
                scomments = ecomments.getText().toString();

                if(scomments.isEmpty()){
                    Toast.makeText(getBaseContext(), "Please enter some additional comments !", Toast.LENGTH_LONG).show();
                } else {
                    alertDialog.setMessage("Are you sure you want to Submit?");

                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dBHelper.insertStudentComments(dBHelper, studId, scomments);

                            next.setEnabled(true);
                            save.setEnabled(false);
                            ecomments.setEnabled(false);
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

    public void saveskipping(View view) {
        Intent intent = new Intent(this, RecordComments.class);
        startActivity(intent);
    }

    public void nextgoto_homescreen(View view){
        Intent intent = new Intent(this, LoginSuccessActivity.class);
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
