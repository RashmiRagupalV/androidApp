package redflag.capstone.com.redflag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class StudentDetailsActivity extends Activity implements AdapterView.OnItemSelectedListener   {

    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;
    Spinner spinStudentDetails;
    TextView cName, cAge, cGrade,cSchool,cTeacherName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        spinStudentDetails = (Spinner) findViewById(R.id.spinStudent);

        cName = (TextView) findViewById(R.id.ChildName);
        cSchool = (TextView) findViewById(R.id.School);
        cGrade = (TextView) findViewById(R.id.Grade);
        cAge = (TextView) findViewById(R.id.Age);
        cTeacherName = (TextView) findViewById(R.id.TeacherName);

//        spinStudentDetails.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
//        loadStudent();

        List<Student> students = getAll();
        spinStudentDetails.setAdapter(new StudentSpinnerAdapter(StudentDetailsActivity.this,R.layout.multiline_spinner_selection, students));

    }

//    public void beginScreeningTest(View view)
//    {
//        Intent screeningTest = new Intent(this, ScreeningTestListActivity.class);
//
//        startActivity(screeningTest);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_details, menu);
        return true;
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

    //Retrieve all records and populate into List<String>
    //Retrieve all records and populate into List<Student>
    //This method allow you to retrieve more fields/information into List.
    public List<Student> getAll() {
        dBHelper = new DatabaseOperations(ctxt);
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Student.USER_CNAME + "," + Student.USER_AGE + "," +
                Student.USER_GRADE + "," + Student.USER_SCHOOL + "," +
                Student.USER_TNAME +
                " FROM " + Student.TABLE_NAME;

        List<Student> studentList = new ArrayList<Student>() ;
        cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student  = new Student();
                student.setUser_cname(cursor.getString(cursor.getColumnIndex(Student.USER_CNAME)));
                student.setUser_age(cursor.getString(cursor.getColumnIndex(Student.USER_AGE)));
                student.setUser_grade(cursor.getString(cursor.getColumnIndex(Student.USER_GRADE)));
                student.setUser_school(cursor.getString(cursor.getColumnIndex(Student.USER_SCHOOL)));
                student.setUser_tname(cursor.getString(cursor.getColumnIndex(Student.USER_TNAME)));
                studentList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();
        return studentList;

    }


    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        if (parentView== findViewById(R.id.spinStudent)){
            Student selected = (Student) parentView.getItemAtPosition(position);
            cName.setText(selected.getUser_cname());
            cSchool.setText(selected.getUser_school());
            cGrade.setText(selected.getUser_grade());
            cAge.setText(selected.getUser_age());
            cTeacherName.setText(selected.getUser_tname());

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        cName.setText( "");
        cSchool.setText("");
        cGrade.setText("");
        cAge.setText("");
        cTeacherName.setText("");
    }


    private void loadStudent(){
        StudentSpinnerAdapter  studentAdapter;
        List<Student> students = getAll();
        studentAdapter = new StudentSpinnerAdapter(StudentDetailsActivity.this,
                android.R.layout.simple_spinner_item , students );

        spinStudentDetails.setAdapter(studentAdapter);

        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //studentAdapter.setDropDownViewResource(R.layout.multiline_spinner_selection);
    }







}
