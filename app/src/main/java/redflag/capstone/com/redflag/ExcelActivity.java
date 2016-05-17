//package redflag.capstone.com.redflag;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.pm.ActivityInfo;
//import android.database.Cursor;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Environment;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//import au.com.bytecode.opencsv.CSVWriter;
//
///**
// * Created by rashm on 5/10/2016.
// */
//public class ExcelActivity extends Activity {
//
//    private DatabaseOperations dBHelper;
//    Context ctxt = this;
//    Cursor cursor;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_generate_excel);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        new ExcelGeneration().execute();
//
//
//    }
//}
//
