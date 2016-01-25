package redflag.capstone.com.redflag;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;
import redflag.capstone.com.redflag.database.DatabaseHelper;


public class StudentSearchAndListActivity extends ListActivity {//} implements ActionBar.TabListener {

    protected EditText searchText;
    protected DatabaseHelper db;
    protected Cursor cursor;
    protected ListAdapter adapter;

    protected ArrayAdapter myArrayAdapter;
    ArrayList<String> remNotesfmDb = new ArrayList<String>() {{
        add("A");
        add("B");
        add("C");
    }};
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    //SectionsPagerAdapter mSectionsPagerAdapter;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_searchlist);

        searchText = (EditText) findViewById(R.id.searchText);
        db = new DatabaseHelper(getApplicationContext());



        // Set up the action bar.
       /* final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }*/
    }

    public void onListItemClick(ListView parent, View view, int position, long id) {
        Intent intent = new Intent(this, StudentListActivity.class);
        Cursor cursor = (Cursor) adapter.getItem(position);
        intent.putExtra("STUDENT_ID", cursor.getInt(cursor.getColumnIndex(db.KEY_STUDENT_ID.toString())));
        startActivity(intent);
    }

    public void search(View view) {
        // || is the concatenation operation in SQLite
      /*  cursor = db.search(searchText.getText().toString()); //have to uncomment*/
       // if (cursor != null) {
           /* adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.activity_studentrow,
                    cursor,
                    new String[]{"firstName", "lastName"},
                    new int[]{R.id.studentFirstName, R.id.studentLastName});*/
            //uncommentListView lv = (ListView)findViewById(R.id.list);
            //lv.setAdapter(adapter);
            //setListAdapter(adapter);

      /*  myArrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_studentrow, remNotesfmDb);
        lv.setAdapter(myArrayAdapter); //have to uncomment*/
       /* }
        else
        {
            Log.v("StudentSearchAndListActivity", "error in search method of StudentSearchAndList class");
        }*/

        /*RelativeLayout layout1 = (RelativeLayout)findViewById(R.id.main);
        RelativeLayout layout2 = (RelativeLayout)findViewById(R.id.main2);
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);*/
        Intent dummylist = new Intent(this, DummyListActivity.class);
        startActivity(dummylist);
    }

    public void studentDetail(View view) {
        Intent studentListBySchoolIntent = new Intent(this, StudentDetailsActivity.class);
        //SearchView searchText = (SearchView) findViewById(R.id.studentSearch);
        //String user_name = searchText.getQuery().toString();
        //studentListBySchoolIntent.putExtra(STUDENT_NAME, user_name);
        startActivity(studentListBySchoolIntent);
    }

    public void dummylistsearch(View view) {
        Intent studentListBySchoolIntent = new Intent(this, DummyListActivity.class);
        //SearchView searchText = (SearchView) findViewById(R.id.studentSearch);
        //String user_name = searchText.getQuery().toString();
        //studentListBySchoolIntent.putExtra(STUDENT_NAME, user_name);
        startActivity(studentListBySchoolIntent);
    }

    public void searchStudent(View view) {
        Intent studentListBySchoolIntent = new Intent(this, StudentListActivity.class);
        //SearchView searchText = (SearchView) findViewById(R.id.studentSearch);
        //String user_name = searchText.getQuery().toString();
        //studentListBySchoolIntent.putExtra(STUDENT_NAME, user_name);
        startActivity(studentListBySchoolIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_search_and_list, menu);
        return true;
    }

   /* @Override
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }*/

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
   /* public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }*/

    /**
     * A placeholder fragment containing a simple view.
     */
   // public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
     /*   private static final String ARG_SECTION_NUMBER = "section_number";
        ListView lv;
        ArrayAdapter<String> myarrayAdapter;
        ArrayList<String> remNotesfmDb = new ArrayList<String>() {{
            add("A");
            add("B");
            add("C");
        }};*/
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
       /* public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_student_search_and_list, container, false);

            lv = (ListView) rootView.findViewById(R.id.list);

            myarrayAdapter = new MyListAdapter(getActivity().getApplicationContext(), R.layout.activity_studentrow, remNotesfmDb);
            lv.setAdapter(myarrayAdapter);

            return rootView;
        }
    }

    public static class MyListAdapter extends ArrayAdapter<String> {
        private LayoutInflater mInflater;
        private Context context;
        private ArrayList<String> studentName;

        private int mViewResourceId;

        class ViewHolder {
            public TextView text;
        }

        public MyListAdapter(Context context, int viewResourceId,
                             ArrayList<String> studentName) {
            super(context, R.layout.activity_studentrow, studentName);

            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.context = context;
            this.studentName = studentName;
            mViewResourceId = viewResourceId;
        }

        //@Override
        public int getCount() {
            return studentName.size();
        }

        //@Override
        public String getItem(int position) {
            return studentName.get(position);
        }

        //@Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(mViewResourceId, null);
            TextView tvn = (TextView)convertView.findViewById(R.id.studentName);
            tvn.setText(studentName.get(position));                                 */
            /*TextView tvd = (TextView)convertView.findViewById(R.id.remdate);
            tvd.setText(date.get(position).toString());
            TextView tvt = (TextView)convertView.findViewById(R.id.remtime);
            tvt.setText(time.get(position).toString());*/

    /*        return convertView;
        }


    }*/

}
