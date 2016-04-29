package redflag.capstone.com.redflag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ReportActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private ArrayList<HashMap<String, String>> list;
    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;
    String selectedstudid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Spinner element
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//
//        // Spinner click listener
//        spinner.setOnItemSelectedListener(this);
//
//        // Spinner Drop down elements
//        List<String> categories = new ArrayList<String>();
//        categories.add("Student report");
//        categories.add("Class report");
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        spinner.setAdapter(dataAdapter);


        TextView columnHeader1 = (TextView) findViewById(R.id.column_header1);
        TextView columnHeader2 = (TextView) findViewById(R.id.column_header2);
        TextView columnHeader3 = (TextView) findViewById(R.id.column_header3);
        TextView columnHeader4 = (TextView) findViewById(R.id.column_header4);

        columnHeader1.setText("ID");
        columnHeader2.setText("Student Name");
        columnHeader3.setText("School");
        columnHeader4.setText("Grade");
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
                Toast.makeText(ReportActivity.this, Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();
                //String selectedid = parent.getAdapter().getItem(position).toString().get("");
                TextView studid = (TextView) view.findViewById(R.id.studid);
                selectedstudid = studid.getText().toString();


            }

        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        // On selecting a spinner item
//        String item = parent.getItemAtPosition(position).toString();
//
//        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.report, menu);
        return true;
    }

    public void viewData(View view){

        if(selectedstudid != null) {
            Intent intent = new Intent(this, ViewAllDetailsStudent.class);
            intent.putExtra("SelectedstudId", selectedstudid);
            startActivity(intent);
        }
        else{
            Toast.makeText(getBaseContext(), "Please select a student!", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
