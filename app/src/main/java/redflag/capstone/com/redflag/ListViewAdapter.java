////package redflag.capstone.com.redflag;
////
/////**
//// * Created by rashm on 4/6/2016.
//// */
////import java.util.ArrayList;
////import java.util.HashMap;
////
////import android.app.Activity;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.BaseAdapter;
////import android.widget.TextView;
////
////public class ListViewAdapter extends BaseAdapter{
////
////    public ArrayList<HashMap<String, String>> list;
////    Activity activity;
////    TextView txtFirst;
////    TextView txtSecond;
////    TextView txtThird;
////    TextView txtFourth;
////    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
////        super();
////        this.activity=activity;
////        this.list=list;
////    }
////
////    @Override
////    public int getCount() {
////        // TODO Auto-generated method stub
////        return list.size();
////    }
////
////    @Override
////    public Object getItem(int position) {
////        // TODO Auto-generated method stub
////        return list.get(position);
////    }
////
////    @Override
////    public long getItemId(int position) {
////        // TODO Auto-generated method stub
////        return 0;
////    }
////
////
////
////    @Override
////    public View getView(int position, View convertView, ViewGroup parent) {
////        // TODO Auto-generated method stub
////
////
////        LayoutInflater inflater=activity.getLayoutInflater();
////
////        if(convertView == null){
////
////            convertView=inflater.inflate(R.layout.row_listview, null);
////
////            txtFirst=(TextView) convertView.findViewById(R.id.studname);
////            txtSecond=(TextView) convertView.findViewById(R.id.studschool);
////            txtThird=(TextView) convertView.findViewById(R.id.studgrade);
////            txtFourth=(TextView)convertView.findViewById(R.id.studid);
////        }
////
////        HashMap<String, String> map=list.get(position);
////        txtFourth.setText(map.get(Student.ZEROETH_COLUMN));
////        txtFirst.setText(map.get(Student.FIRST_COLUMN));
////        txtSecond.setText(map.get(Student.SECOND_COLUMN));
////        txtThird.setText(map.get(Student.THIRD_COLUMN));
////        return convertView;
////    }
////
////}
//
//package redflag.capstone.com.redflag;
//import android.app.Activity;
//import android.graphics.Color;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class ListViewAdapter extends ArrayAdapter {
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = super.getView(position, convertView, parent);
//        if (position % 2 == 1) {
//            view.setBackgroundColor(Color.BLUE);
//        } else {
//            view.setBackgroundColor(Color.CYAN);
//        }
//
//        return view;
//    }
//
//    public ListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> list) {
//        super(activity);
//        this.activity = activity;
//        this.list = list;
//    }
//
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//}
//
//
//
//}
package redflag.capstone.com.redflag;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListViewAdapter extends ArrayAdapter<String> {
    public ListViewAdapter(Context context, String[] values, int position) {
        super(context,position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (position % 2 == 1) {
            view.setBackgroundColor(Color.BLUE);
        } else {
            view.setBackgroundColor(Color.CYAN);
        }

        return view;

    }
}