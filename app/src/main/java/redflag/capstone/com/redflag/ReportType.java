package redflag.capstone.com.redflag;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import android.widget.CalendarView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Created by rashm on 5/11/2016.
 */
public class ReportType extends Activity {

    public final static String EXTRA_MESSAGE = "com.Capstone.RedFlag2.MESSAGE";
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String StudId = "StudId" ;

    private DatabaseOperations dBHelper;
    Context ctxt = this;
    TextView edittxt1,date,selectstud;
    CalendarView calender;
    RadioGroup rgp;
    RadioButton rdbt1, rdbt2;
    Button button1,button2,viewreport;
    Spinner spinner;
    String spinnervalue;
    //ListView lv1,lv2;

    private ArrayList<HashMap<String, String>> list;
    Cursor cursor;
    String selectedstudid;
    String Sid;
    //  Button LOGOUT;

    ListView listcontent,list_head;
    ArrayList<HashMap<String, String>> mylist, mylist_title;
    ListAdapter adapter_title, adapter;
    HashMap<String, String> map1, map2;

    SharedPreferences sharedpreferences;

    String user_name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(getBaseContext());
        dBHelper.getWritableDatabase();
        setContentView(R.layout.create_report);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Bundle bundle = getIntent().getExtras();
        user_name = bundle.getString("TesterName");
        button1 = (Button)findViewById(R.id.excel);
        button1.setVisibility(View.INVISIBLE);

        button2 = (Button)findViewById(R.id.button_sendmail);
        button2.setVisibility(View.INVISIBLE);


        date = (TextView)findViewById(R.id.date);
        date.setVisibility(View.INVISIBLE);


        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setVisibility(View.INVISIBLE);

        selectstud = (TextView)findViewById(R.id.selectstud);
        selectstud.setVisibility(View.INVISIBLE);

        viewreport = (Button)findViewById(R.id.viewreport);
        viewreport.setVisibility(View.INVISIBLE);
        list_head = (ListView)findViewById(R.id.listView1);
        list_head.setVisibility(View.INVISIBLE);

        listcontent = (ListView)findViewById(R.id.listView2);
        listcontent.setVisibility(View.INVISIBLE);
//        edittxt1 = (TextView) findViewById(R.id.textViewdate);
//        edittxt1.setVisibility(View.INVISIBLE);
////        calender = (CalendarView) findViewById(R.id.calendarView);
//        calender.setVisibility(View.INVISIBLE);
//        rdbt1 = (RadioButton) findViewById(R.id.radiobtnreport);
        rdbt2 = (RadioButton) findViewById(R.id.radioButtonstudent);
        rgp = (RadioGroup)findViewById(R.id.rgrp1);


        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                String text = checkedRadioButton.getText().toString();
                int pos = group.indexOfChild(findViewById(checkedId));

                if (pos == 0){
            //    if(text.equals("Email daily report")){
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    date.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                    list_head.setVisibility(View.INVISIBLE);
                    listcontent.setVisibility(View.INVISIBLE);
                    selectstud.setVisibility(View.INVISIBLE);
                    viewreport.setVisibility(View.INVISIBLE);
                    // Spinner click listener
                //   spinner.setOnItemSelectedListener(getApplicationContext());

                    // Spinner Drop down elements
                    dBHelper = new DatabaseOperations(ctxt);
                    List<String> lables = dBHelper.getAllDates(dBHelper);

                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, lables);

                    spinner.setAdapter(dataAdapter);

                }
                else{
                    list_head.setVisibility(View.VISIBLE);
                    listcontent.setVisibility(View.VISIBLE);
                    selectstud.setVisibility(View.VISIBLE);
                    viewreport.setVisibility(View.VISIBLE);
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    date.setVisibility(View.INVISIBLE);
                    spinner.setVisibility(View.INVISIBLE);
                    showActivity();

                }
                //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnervalue = spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        Button btn = (Button) findViewById(R.id.excel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExcelGeneration().execute("");

            }
        });
        Button btnsendmail=(Button) findViewById(R.id.button_sendmail);
        btnsendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Send email", "");
                String[] TO = {""};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    //finish();
                    Log.d("Email sent...", "");
                   // setContentView(R.layout.create_report);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ReportType.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void generateexcel(View view){

    }

    class ExcelGeneration extends AsyncTask<String, String, Boolean> {
        private final ProgressDialog dialog = new ProgressDialog(ReportType.this);
        boolean memoryErr = false;
        //  private Context context;
        Cursor cursor;
        private DatabaseOperations dBHelper;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        //String currentDateTimeString = dateFormat.getDateTimeInstance().format(new Date());
        Date date = new Date();
        String currentDateTimeString = dateFormat.format(date);

        // to show Loading dialog box
        @Override
        protected void onPreExecute() {
            //  super.onPreExecute();
            this.dialog.setMessage("Creating report...");
            this.dialog.show();

            final int totalProgressTime = 100;
            final Thread t = new Thread() {
                @Override
                public void run() {
                    int jumpTime = 0;

                    while(jumpTime < totalProgressTime) {
                        try {
                            sleep(200);
                            jumpTime += 5;
                            dialog.setProgress(jumpTime);
                        }
                        catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            };
            t.start();


        }

        // to write process
        protected Boolean doInBackground(final String... args) {
            boolean success = false;
            // String currentDateString = new SimpleDateFormat(Constants.SimpleDtFrmt_ddMMyyyy).format(new Date());

            String state = Environment.getExternalStorageState();

            if (!Environment.MEDIA_MOUNTED.equals(state)) {
                return false;
            } else {

                //  Log.d("Db path is: " + dbFile, "database"); // get the path of db
                File exportDir = new File(Environment.getExternalStorageDirectory(), "");
                dBHelper = new DatabaseOperations(ctxt);

//        long freeBytesInternal = new File(get().getFilesDir().getAbsoluteFile().toString()).getFreeSpace();
//        long megAvailable = freeBytesInternal / 1048576;
//
//        if (megAvailable < 0.1) {
//            System.out.println("Please check"+megAvailable);
//            memoryErr = true;
//        }else {
                String exportDirStr = exportDir.toString();// to show in dialogbox
                Log.d("exportDir path::" + exportDir, "Path");
                if (!exportDir.exists()) {
                    exportDir.mkdirs();
                }
                File file = new File(exportDir, "Report_" + spinnervalue + ".csv");
                try {
                    if (file.createNewFile()) {
                        System.out.println("File is created!");
                        System.out.println("Report_" + spinnervalue + ".csv" + file.getAbsolutePath());
                    } else {
                        System.out.println("File already exists.");
                    }

                    CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                    String headers[] = {"Tester Name","Record Date","Student Name", "Teacher Name","Student Age","School", "Grade",
                            "HorizontalParameter", "VerticalParameter","NeedBeanBag",
                            "EyeOpenRight","EyeOpenLeft","EyeCloseRight","EyeCloseLeft",
                            "SkippingParameter",
                            "CrawlingParameter",
                            "TeamingRound1","TeamingRound2",
                            "VisualDiscriminationParameter",
                            "Additional Comments"
                    };
                    csvWrite.writeNext(headers);

                    dBHelper = new DatabaseOperations(ctxt);
                    cursor = dBHelper.getAllData(dBHelper, spinnervalue);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                   // String testername = sharedpreferences.getString("TesterName", "Admin");
                    if (cursor.moveToFirst()) {
                        do {
                            String s[] = {cursor.getString(13),spinnervalue, cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(6),cursor.getString(5),
                                    cursor.getString(10), cursor.getString(12),cursor.getString(11),
                                    cursor.getString(16), cursor.getString(17),cursor.getString(18),cursor.getString(19),
                                    cursor.getString(23),
                                    cursor.getString(27),
                                    cursor.getString(31), cursor.getString(32),
                                    cursor.getString(36),
                                    cursor.getString(40)
                                    };
                                    csvWrite.writeNext(s);
                        } while (cursor.moveToNext());
                        success = true;
                    }
                    csvWrite.close();
                } catch (IOException e) {
                    Log.e("ReportType", e.getMessage(), e);
                    return success;
                }
            }
            return success;

        }

        // close dialog and give msg
        protected void onPostExecute(Boolean success) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (success) {
                Toast.makeText(ctxt, "Report_" + spinnervalue + " created !", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(ctxt, "Report_" + spinnervalue + "creation failed ! Please try again", Toast.LENGTH_LONG).show();
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    public void onClick() {

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

    public void gohome(View view){
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        intent.putExtra("TesterName",user_name);
        startActivity(intent);
    }

    public void showActivity() {

        mylist = new ArrayList<HashMap<String, String>>();
        mylist_title = new ArrayList<HashMap<String, String>>();

        /**********Display the headings************/


        map1 = new HashMap<String, String>();

        map1.put("slno", "SlNo");
        map1.put("one", " Student");
        map1.put("two", " School");
        map1.put("three","Grade");
        mylist_title.add(map1);

        try {
            adapter_title = new SimpleAdapter(this, mylist_title, R.layout.row_listview,
                    new String[] { "slno", "one", "two","three","hiddenid" }, new int[] {R.id.Slno, R.id.one, R.id.two,R.id.three,R.id.hiddenid }) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View v = super.getView(position, convertView, parent);
                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_list_view_title));
                    return v;
                }
            };
            list_head.setAdapter(adapter_title);
        } catch (Exception e) {

        }

        /**********Display the contents************/

        try {
            int i = 0;

            dBHelper = new DatabaseOperations(ctxt);
            cursor = dBHelper.getData(dBHelper);
            if (cursor.moveToFirst()) {
                do {
                    i = i+1;
                    map2 = new HashMap<String, String>();

                    map2.put("slno", String.valueOf(i));
                    map2.put("one", cursor.getString(1));
                    map2.put("two", cursor.getString(6));
                    map2.put("three", cursor.getString(5));
                    map2.put("hiddenid", cursor.getString(0));
                    mylist.add(map2);

                    //   temp.put(Student.ZEROETH_COLUMN, cursor.getString(0));

//                    temp.put(Student.FIRST_COLUMN, cursor.getString(1));
//                    temp.put(Student.SECOND_COLUMN, cursor.getString(6));
//                    temp.put(Student.THIRD_COLUMN, cursor.getString(5));
//                    list.add(temp);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }

        try {
            adapter = new SimpleAdapter(this, mylist, R.layout.row_listview,
                    new String[] { "slno", "one", "two","three","hiddenid" }, new int[] {
                    R.id.Slno, R.id.one, R.id.two,R.id.three,R.id.hiddenid }){

                @Override
                public View getView (int position, View convertView, ViewGroup parent)
                {
                    View v = super.getView(position, convertView, parent);
                    if(position %2==0)
                    {
                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_list_view));

                    }
                    else
                    {
                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_list_view1));
                    }

                    return v;
                }


            };
            listcontent.setAdapter(adapter);
        } catch (Exception e) {

        }

        listcontent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                int pos = position + 1;
                //   Toast.makeText(StudentDetailsActivity.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
                HashMap<String, String> selectedstudid = (HashMap)parent.getAdapter().getItem(position);
                Sid = selectedstudid.get("hiddenid");
                view.setSelected(true);
//                view.getFocusables(position);
//                view.setSelected(true);
//                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.listview_row_selected));
            }

        });

    }


    public void viewreport(View view) {

        if (Sid != null) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(StudId,Sid );
            editor.commit();
            Intent intent = new Intent(this, ViewAllDetailsStudent.class);
            //intent.putExtra("SelectedstudId", Sid);
            intent.putExtra("TesterName",user_name);
            startActivity(intent);
        } else {
            Toast.makeText(getBaseContext(), "Please select a student !", Toast.LENGTH_LONG).show();
        }

    }


}
