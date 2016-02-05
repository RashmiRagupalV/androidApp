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
//        dBHelper = new DatabaseOperations(getBaseContext());
//        dBHelper.getWritableDatabase();
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
        String user_name = editText.getText().toString();
        EditText editText1 = (EditText) findViewById(R.id.password);
        String user_pass = editText1.getText().toString();
        CheckBox isChecked = ((CheckBox) findViewById(R.id.checkBox));
        dBHelper = new DatabaseOperations(ctxt);
        if (isChecked.isChecked()) { // Check if Admin checkbox is selected
            cursor = dBHelper.isValidUser(dBHelper, user_name, user_pass, "Admin");
            if(cursor.moveToFirst()){
                do{
                    //assing values
                    String column1 = cursor.getString(0);
                    String column2 = cursor.getString(1);
                    String column3 = cursor.getString(2);
                    //Do something Here with values

                }while(cursor.moveToNext());
            }
            cursor.close();
            dBHelper.close();
            Intent loginIntent = new Intent(this, LoginSuccessActivity.class);
            loginIntent.putExtra(EXTRA_MESSAGE, user_name);
            startActivity(loginIntent);
        }
        else
        {
            Toast.makeText(getBaseContext(), "User is not an authenticated Admin !", Toast.LENGTH_LONG).show();
            editText.setText("");
            editText1.setText("");
            isChecked.toggle();

        }
//        else
//        if(!(user_name.equals(TableData.TableInfo.USER_NAME)))
//        {
//            Toast.makeText(getBaseContext(), "User is not an authenticated Admin !", Toast.LENGTH_LONG).show();
//            editText.setText("");
//            editText1.setText("");
//        }
//        else if(!(user_pass.equals(TableData.TableInfo.USER_PASS)))
//        {
//                Toast.makeText(getBaseContext(),"Passwords are not matching !", Toast.LENGTH_LONG).show();
//                editText.setText("");
//                editText1.setText("");
//        }
//        else {
//            Intent loginIntent = new Intent(this, LoginSuccessActivity.class);
//            loginIntent.putExtra(EXTRA_MESSAGE, user_name);
//            startActivity(loginIntent);
//        }
    }
}
