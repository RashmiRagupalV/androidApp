package redflag.capstone.com.redflag;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;
import android.content.Context;

public class Login extends Activity {

    public final static String EXTRA_MESSAGE = "com.Capstone.RedFlag2.MESSAGE";
    private DatabaseOperations dBHelper;
    Context ctxt = this;
    EditText edittxt1,edittxt2;
    Button button1;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(getBaseContext());
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_login);
        setUpView();
        setUpFocus();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        edittxt1=(EditText)findViewById(R.id.login_name);
        edittxt2=(EditText)findViewById(R.id.login_password);
        button1=(Button)findViewById(R.id.button_login);

        edittxt1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                edittxt1.setError(null);
            }
        });

        edittxt2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                edittxt2.setError(null);
            }
        });
    }

    private void setUpView(){
        edittxt1=(EditText)findViewById(R.id.login_name);
        edittxt2=(EditText)findViewById(R.id.login_password);
        button1=(Button)findViewById(R.id.button_login);
    }
    private void setUpFocus(){
        edittxt1.setNextFocusForwardId(R.id.login_password);
        edittxt2.setNextFocusForwardId(R.id.button_login);// you can give focus to any id

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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

    public void validateLogin(View view) {
        //EditText editText = (EditText) findViewById(R.id.login_name);
        String user_name = edittxt1.getText().toString().toUpperCase();
        //EditText editText1 = (EditText) findViewById(R.id.login_password);
        String user_pass = edittxt2.getText().toString();
        dBHelper = new DatabaseOperations(ctxt);
//        if( edittxt1.getText().toString().length() == 0 )
//            edittxt1.setError( "UserName is needed to Login !" );
//
//        if( edittxt2.getText().toString().length() == 0 )
//            edittxt2.setError("Password cannot be empty !");

        if(user_name.isEmpty()){
            if(user_pass.isEmpty()) {
                //edittxt1.requestFocus();
               Toast.makeText(getBaseContext(), "Please fill up all details !", Toast.LENGTH_LONG).show();
            }
            else{
                //edittxt1.requestFocus();
                Toast.makeText(getBaseContext(), "UserName is required to login !", Toast.LENGTH_LONG).show();
            }
        }
        else if(user_pass.isEmpty()){
           // edittxt2.requestFocus();
                Toast.makeText(getBaseContext(), "Please enter password !", Toast.LENGTH_LONG).show();
        }

        if(!user_name.isEmpty() && !user_pass.isEmpty()) {
            cursor = dBHelper.isValidUser(dBHelper);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    String column1 = cursor.getString(1).toUpperCase();
                    String column2 = cursor.getString(2);
                    if (!column1.equals(user_name)) {
                        Toast.makeText(getBaseContext(), "Username is incorrect !", Toast.LENGTH_LONG).show();
                        //edittxt1.requestFocus();
                       // edittxt1.setError("UserName is incorrect !" );
                        edittxt1.requestFocus();
                        edittxt1.setText("");
                        edittxt2.setText("");
                    }
                    else if (!column2.equals(user_pass)) {
                        Toast.makeText(getBaseContext(), "Password is incorrect !", Toast.LENGTH_LONG).show();
                       // edittxt2.requestFocus();
                      //  edittxt2.setError("Password is incorrect !" );
                       // edittxt1.setText("");
                        edittxt2.setText("");
                    }
                    else if (user_name.equals(column1) && user_pass.equals(column2)) {
                        Intent loginIntent = new Intent(this, LoginSuccessActivity.class);
                        loginIntent.putExtra(EXTRA_MESSAGE, user_name);
//                        cursor.close();
//                        dBHelper.close();
                        startActivity(loginIntent);

                    }
                } while (cursor.moveToNext());
            }
        }
    }
}
