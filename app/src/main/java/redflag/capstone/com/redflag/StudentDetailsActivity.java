package redflag.capstone.com.redflag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class StudentDetailsActivity extends Activity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String StudId = "StudId" ;

    private ArrayList<HashMap<String, String>> list;
    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;
    String selectedstudid;
    String Sid;
    String Studname;
    Button button_start;

    TextView studentrec;

    ListView listcontent,list_head;
    ArrayList<HashMap<String, String>> mylist, mylist_title;
    ListAdapter adapter_title, adapter;
    HashMap<String, String> map1, map2;
    String user_name;

    SharedPreferences sharedpreferences;
    Button START;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        button_start = (Button)findViewById(R.id.button_start);
        studentrec = (TextView)findViewById(R.id.studrec);
        Bundle bundle = getIntent().getExtras();
        user_name =bundle.getString("TesterName");
        START = (Button)findViewById(R.id.button_start);


        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);


        START.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Sid != null) {
                    try {
                        dBHelper = new DatabaseOperations(ctxt);
                        cursor = dBHelper.checkifRecordedToday(dBHelper, Sid);
                        if (cursor.moveToFirst()) {
                            alertDialog.setMessage("The student's activities for today's date have been recorded ! Do you want to record again? ");
                            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString(StudId, Sid);
                                    editor.putString("Studname", Studname);
                                    editor.commit();
                                    Intent intent = new Intent(getBaseContext(), RecordTrackingEyeball.class);
                                    intent.putExtra("TesterName", user_name);
                                    startActivity(intent);
                                }
                            });
                            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.show();
                        }else{
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(StudId, Sid);
                            editor.putString("Studname", Studname);
                            editor.commit();
                            Intent intent = new Intent(getBaseContext(), RecordTrackingEyeball.class);
                            intent.putExtra("TesterName", user_name);
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error encountered.",
                                Toast.LENGTH_LONG);
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Please select a student!", Toast.LENGTH_LONG).show();
                }
            }

        });


        listcontent = (ListView) findViewById(R.id.listView2);
        list_head = (ListView) findViewById(R.id.listView1);
        showActivity();
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
               } while (cursor.moveToNext());
                button_start.setVisibility(View.VISIBLE);
                list_head.setVisibility(View.VISIBLE);

            }else{
                studentrec.setText("No Student registered !");
                button_start.setVisibility(View.INVISIBLE);
                list_head.setVisibility(View.INVISIBLE);
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
                Studname = selectedstudid.get("one");
                view.setSelected(true);
//                view.getFocusables(position);
//                view.setSelected(true);
//                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.listview_row_selected));
            }

        });

    }

 //   public void startrecording(View view) {

//        if (Sid != null) {
//            try {
//                int i = 0;
//
//                dBHelper = new DatabaseOperations(ctxt);
//                cursor = dBHelper.checkifRecordedToday(dBHelper);
//                if (cursor.moveToFirst())
//                }else{
//                    studentrec.setText("No Student registered !");
//                    button_start.setVisibility(View.INVISIBLE);
//                    list_head.setVisibility(View.INVISIBLE);
//                }
//
//            } catch (Exception e) {
//                Toast.makeText(getApplicationContext(), "Error encountered.",
//                        Toast.LENGTH_LONG);
//            }
//            else
//
//
//            SharedPreferences.Editor editor = sharedpreferences.edit();
//            editor.putString(StudId,Sid );
//            editor.putString("Studname",Studname);
//            editor.commit();
//            Intent intent = new Intent(this, RecordTrackingEyeball.class);
//            intent.putExtra("TesterName", user_name);
//            startActivity(intent);
//        } else {
//            Toast.makeText(getBaseContext(), "Please select a student!", Toast.LENGTH_LONG).show();
//        }

  //  }

    public void cancelRegistration(View view) {
        clearall();
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        intent.putExtra("TesterName",user_name);
        startActivity(intent);
    }

    public void clearall(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        // editor.clear();
        editor.commit();

    }
}