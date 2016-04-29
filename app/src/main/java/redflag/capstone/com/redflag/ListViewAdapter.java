package redflag.capstone.com.redflag;

/**
 * Created by rashm on 4/6/2016.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtFourth;
    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.row_listview, null);

            txtFirst=(TextView) convertView.findViewById(R.id.studname);
            txtSecond=(TextView) convertView.findViewById(R.id.studschool);
            txtThird=(TextView) convertView.findViewById(R.id.studgrade);
            txtFourth=(TextView)convertView.findViewById(R.id.studid);
        }

        HashMap<String, String> map=list.get(position);
        txtFourth.setText(map.get(Student.ZEROETH_COLUMN));
        txtFirst.setText(map.get(Student.FIRST_COLUMN));
        txtSecond.setText(map.get(Student.SECOND_COLUMN));
        txtThird.setText(map.get(Student.THIRD_COLUMN));
        return convertView;
    }

}
