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
  //  Button LOGOUT;

    ListView listcontent,list_head;
    ArrayList<HashMap<String, String>> mylist, mylist_title;
    ListAdapter adapter_title, adapter;
    HashMap<String, String> map1, map2;

    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

//        LOGOUT = (Button)findViewById(R.id.user_logout);
//        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
//
//
//        LOGOUT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.setMessage("Are you sure you want to log off ?");
//
//                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        //do this on logout button click
////                            final String LOG_OUT = "event_logout";
////                            Intent intent = new Intent(LOG_OUT);
////                            //send the broadcast to all activities who are listening
////                            LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);
//                        Intent loginscreen = new Intent(getBaseContext(), Login.class);
//                        startActivity(loginscreen);
//
//                    }
//                });
//                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
////                            Intent loginintent = new Intent(getBaseContext(), LoginSuccessActivity.class);
////                            startActivity(loginintent);
//                    }
//                });
//                alertDialog.show();
//
//            }
//
//        });
//

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

    public void startrecording(View view) {

        if (Sid != null) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(StudId,Sid );
            editor.commit();
            Intent intent = new Intent(this, RecordTrackingEyeball.class);
            //intent.putExtra("SelectedstudId", Sid);
            startActivity(intent);
        } else {
            Toast.makeText(getBaseContext(), "Please select a student!", Toast.LENGTH_LONG).show();
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
        // editor.clear();
        editor.commit();

    }
}