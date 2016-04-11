package redflag.capstone.com.redflag;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class StudentDetailsActivity extends Activity {

    private ArrayList<HashMap<String, String>> list;
    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;
    String selectedstudid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView columnHeader1 = (TextView) findViewById(R.id.column_header1);
        TextView columnHeader2 = (TextView) findViewById(R.id.column_header2);
        TextView columnHeader3 = (TextView) findViewById(R.id.column_header3);

        columnHeader1.setText("Student Name");
        columnHeader2.setText("School Names");
        columnHeader3.setText("Grades");
        ListView listView=(ListView)findViewById(R.id.listView1);

        list=new ArrayList<HashMap<String,String>>();
        HashMap<String,String> temp=new HashMap<String, String>();

        try {
            dBHelper = new DatabaseOperations(ctxt);
            cursor = dBHelper.getData(dBHelper);

            if (cursor.moveToFirst()) {
                do {
                    temp=new HashMap<String, String>();
                    temp.put(Student.ZEROETH_COLUMN, cursor.getString(0));

                    temp.put(Student.FIRST_COLUMN, cursor.getString(1));
                    temp.put(Student.SECOND_COLUMN, cursor.getString(6));
                    temp.put(Student.THIRD_COLUMN, cursor.getString(5));
                    list.add(temp);

                } while (cursor.moveToNext());
            }
            // cursor.close();
            //dBHelper.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }

        ListViewAdapter adapter=new ListViewAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;
                Toast.makeText(StudentDetailsActivity.this, Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();
                //String selectedid = parent.getAdapter().getItem(position).toString().get("");
                TextView studid = (TextView) view.findViewById(R.id.studid);
                selectedstudid = studid.getText().toString();

            }

        });

    }

    public void Recording(View view){

        if(selectedstudid != null) {
            Intent intent = new Intent(this, RecordTrackingEyeball.class);
            intent.putExtra("SelectedstudId", selectedstudid);
            startActivity(intent);
        }
        else{
            Toast.makeText(getBaseContext(), "Please select a student!", Toast.LENGTH_LONG).show();
        }

    }

    private void loadData() {
        // database handler
        ArrayList<String> my_array = new ArrayList<String>();
        try {
            dBHelper = new DatabaseOperations(ctxt);
            cursor = dBHelper.getData(dBHelper);

            if (cursor.moveToFirst()) {
                do {

                    String NAME = cursor.getString(0);
                    my_array.add(NAME);

                } while (cursor.moveToNext());
            }
            // cursor.close();
            //dBHelper.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }
    }
}






//package redflag.capstone.com.redflag;
//
//import  java.util.ArrayList;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemSelectedListener;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//public class StudentDetailsActivity extends Activity implements
//        OnItemSelectedListener
//{
//    private DatabaseOperations dBHelper;
//    Context ctxt = this;
//    Cursor cursor;
//    Spinner My_spinner1, My_spinner2;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_student_details);
//
//
//        My_spinner1 = (Spinner) findViewById(R.id.spinnerstudname);
//        My_spinner1.setOnItemSelectedListener(this);
//        loadSpinner1Data();
//
//    }
//
//    /**
//     * Function to load the spinner data from SQLite database
//     * */
//    private void loadSpinner1Data() {
//        // database handler
//        ArrayList<String> my_array = new ArrayList<String>();
//        try {
//            dBHelper = new DatabaseOperations(ctxt);
//            cursor = dBHelper.getData(dBHelper);
//
//            if (cursor.moveToFirst()) {
//                do {
//
//                    String NAME = cursor.getString(0);
//                    my_array.add(NAME);
//
//                } while (cursor.moveToNext());
//            }
//            // cursor.close();
//            //dBHelper.close();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "Error encountered.",
//                    Toast.LENGTH_LONG);
//        }
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, my_array);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter
//                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        My_spinner1.setAdapter(dataAdapter);
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position,
//                               long id) {
//        // On selecting a spinner item
//        String studname = parent.getItemAtPosition(position).toString();
//        int studnumber = getStudentNumber(studname);
//        TextView studnum = (TextView) findViewById(R.id.hidden);
//        studnum.setText(this.getString(studnumber));
////        ArrayList<String> my_array = new ArrayList<String>();
////         my_array = loadSpinner2Data(studname);
////        // Creating adapter for spinner
////        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
////                android.R.layout.simple_spinner_item, my_array);
////
////        // Drop down layout style - list view with radio button
////        dataAdapter
////                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////
////        // attaching data adapter to spinner
////        My_spinner2.setAdapter(dataAdapter);
////
////
////
////        // Showing selected spinner item
////        Toast.makeText(parent.getContext(), "You selected: " + studname,
////                Toast.LENGTH_LONG).show();
//
//    }
//
//
//    private int getStudentNumber(String studname){
//
//        int studnumber = 0;
//        try {
//            dBHelper = new DatabaseOperations(ctxt);
//            cursor = dBHelper.getStudentNumber(dBHelper, studname);
//
//            if (cursor.moveToFirst()) {
//
//                studnumber = cursor.getInt(0);
//
//            }
//            // cursor.close();
//            //dBHelper.close();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "Error encountered.",
//                    Toast.LENGTH_LONG);
//        }
//        return studnumber;
//    }
//    /**
//     * Function to load the spinner data from SQLite database
//     * */
//    private ArrayList<String> loadSpinner2Data(String studname) {
//        // database handler
//        ArrayList<String> my_array = new ArrayList<String>();
//        try {
//            dBHelper = new DatabaseOperations(ctxt);
//            cursor = dBHelper.getSchoolNames(dBHelper, studname);
//
//            if (cursor.moveToFirst()) {
//                do {
//
//                    String schoolname = cursor.getString(0);
//                    my_array.add(schoolname);
//
//                } while (cursor.moveToNext());
//            }
//            // cursor.close();
//            //dBHelper.close();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "Error encountered.",
//                    Toast.LENGTH_LONG);
//        }
//     return my_array;
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> arg0) {
//        // TODO Auto-generated method stub
//
//    }
//
//    public void startRecording(View view){
//        Intent trackIntent = new Intent(this, LoginSuccessActivity.class);
//        startActivity(trackIntent);
//    }
//
//}
//
//
