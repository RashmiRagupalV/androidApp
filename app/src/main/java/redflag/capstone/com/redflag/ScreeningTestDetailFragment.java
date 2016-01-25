package redflag.capstone.com.redflag;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
/*import android.widget.Button;
import android.widget.Chronometer;
import android.os.SystemClock;
import android.view.View.OnClickListener;*/

import redflag.capstone.com.redflag.dummy.DummyContent;

/**
 * A fragment representing a single ScreeningTest detail screen.
 * This fragment is either contained in a {@link ScreeningTestListActivity}
 * in two-pane mode (on tablets) or a {@link ScreeningTestDetailActivity}
 * on handsets.
 */
public class ScreeningTestDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    //private Chronometer chronometer;

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ScreeningTestDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. Eventually, we use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_screeningtest_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {

            if (mItem.content.equals("Crawling"))
            {
                rootView = inflater.inflate(R.layout.fragment_screeningtest_crawling, container, false);
            }
            else if (mItem.content.equals("Skipping"))
            {
                rootView = inflater.inflate(R.layout.fragment_screeningtest_skipping, container, false);
            }
            else if (mItem.content.equals("Tracking"))
            {
                rootView = inflater.inflate(R.layout.fragment_screeningtest_tracking, container, false);
            }
            else if (mItem.content.equals("Teaming"))
            {
                rootView = inflater.inflate(R.layout.fragment_screeningtest_teaming, container, false);
            }
            else if (mItem.content.equals("Balance"))
            {
                rootView = inflater.inflate(R.layout.fragment_screeningtest_balance, container, false);

               /* chronometer = (Chronometer) getView().findViewById(R.id.chronometer);
                ((ImageButton) getView().findViewById(R.id.start_button)).setOnClickListener(this);
                ((ImageButton) getView().findViewById(R.id.stop_button)).setOnClickListener(this);*/
            }
            else
            ((TextView) rootView.findViewById(R.id.screeningtest_detail)).setText(mItem.content);
        }

        return rootView;
    }

    /*public void saveTest(View view) {

        Toast.makeText(getActivity().getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
        Intent saveTestIntent = new Intent(getActivity(), LoginSuccessActivity.class);
        getActivity().startActivity(saveTestIntent);

    }*/
    /*@Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.start_button:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case R.id.stop_button:
                chronometer.stop();
                break;
        }
    }*/
}
