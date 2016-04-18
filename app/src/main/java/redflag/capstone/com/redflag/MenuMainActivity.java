package redflag.capstone.com.redflag;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.ResponseHandler;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.BasicResponseHandler;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.params.BasicHttpParams;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.app.Activity;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Toast;

public class MenuMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiline_spinner_selection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_details, menu);
        return true;
    }

    public void callweb(View view){

        String serverURL = "http://androidappstudy.esy.es/getstudent.php";
        new ExecuteTask().execute(serverURL);
    }

    class ExecuteTask extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(params[0]);
            HttpResponse response=null;
            try {
                response = httpClient.execute(httpGet);
            } catch (ClientProtocolException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            //HttpEntity httpEntity = response.getEntity();
            InputStream is=null;
            String return_text="";
            try {
                is=response.getEntity().getContent();

                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
                String line="";
                StringBuffer sb=new StringBuffer();
                while ((line=bufferedReader.readLine())!=null)
                {
                    sb.append(line);
                    Log.w("response",sb.toString());
                }
                return_text=sb.toString();
                return return_text;

            } catch (Exception e)
            {

            }
            return "";

        }

        @Override
        protected void onPostExecute(String args){
            Toast.makeText(getApplicationContext(), args, Toast.LENGTH_LONG).show();
            String OutputData = "";
            JSONObject jsonResponse;

            try {

                /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                //jsonResponse = new JSONObject(args);
                //Toast.makeText(getBaseContext(), String.valueOf(jsonResponse), Toast.LENGTH_LONG).show();
                /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
                /*******  Returns null otherwise.  *******/
                //  JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");

                /*********** Process each JSON Node ************/

                JSONArray arr = new JSONArray(args);
                int lengthJsonArr = arr.length();
                Toast.makeText(getBaseContext(), String.valueOf(lengthJsonArr), Toast.LENGTH_LONG).show();
                for(int i=0; i < lengthJsonArr; i++)
                {
                    /****** Get Object for each JSON node.***********/
                    JSONObject jsonChildNode = arr.getJSONObject(i);

                    /******* Fetch node values **********/
                    String age       = jsonChildNode.optString("age").toString();
                    String grade     = jsonChildNode.optString("grade").toString();
                    String school = jsonChildNode.optString("school").toString();


                    Toast.makeText(getBaseContext(),age+grade+school,Toast.LENGTH_LONG).show();

                }
                /****************** End Parse Response JSON Data *************/

                //Show Parsed Output on screen (activity)
                //jsonParsed.setText( OutputData );


            } catch (JSONException e) {
                Toast.makeText(getBaseContext(), e.toString(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }

    }
}
