package redflag.capstone.com.redflag;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;
import android.content.Context;

public class Login extends Activity {

    public final static String EXTRA_MESSAGE = "com.Capstone.RedFlag2.MESSAGE";
    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DatabaseOperations(getBaseContext());
        dBHelper.getWritableDatabase();
        setContentView(R.layout.activity_login);
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
        EditText editText = (EditText) findViewById(R.id.login_name);
        String user_name = editText.getText().toString().toUpperCase();
        EditText editText1 = (EditText) findViewById(R.id.login_password);
        String user_pass = editText1.getText().toString();
        dBHelper = new DatabaseOperations(ctxt);
        if(user_name.isEmpty()){
            if(user_pass.isEmpty()) {
               Toast.makeText(getBaseContext(), "User details not entered !", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getBaseContext(), "UserName not entered !", Toast.LENGTH_LONG).show();
            }
        }
        else if(user_pass.isEmpty()){
                Toast.makeText(getBaseContext(), "Password not entered !", Toast.LENGTH_LONG).show();
        }

        if(!user_name.isEmpty() && !user_pass.isEmpty()) {
            cursor = dBHelper.isValidUser(dBHelper);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    String column1 = cursor.getString(0).toUpperCase();
                    String column2 = cursor.getString(1);
                    if (!column1.equals(user_name)) {
                        Toast.makeText(getBaseContext(), "Username is incorrect !", Toast.LENGTH_LONG).show();
                        editText.setText("");
                        editText1.setText("");
                    }
                    else if (!column2.equals(user_pass)) {
                        Toast.makeText(getBaseContext(), "Password is incorrect !", Toast.LENGTH_LONG).show();
                        editText.setText("");
                        editText1.setText("");
                    }
                    else if (user_name.equals(column1) && user_pass.equals(column2)) {
                        Intent loginIntent = new Intent(this, LoginSuccessActivity.class);
                        loginIntent.putExtra(EXTRA_MESSAGE, user_name);
                        cursor.close();
                        dBHelper.close();
                        startActivity(loginIntent);
                    }
                } while (cursor.moveToNext());
            }
        }
    }
}
