package redflag.capstone.com.redflag.database;

import android.database.Cursor;

/**
 * Created by Anshu on 10/20/14.
 */
public class Utils {
    public static void closeCursor(final Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
}
