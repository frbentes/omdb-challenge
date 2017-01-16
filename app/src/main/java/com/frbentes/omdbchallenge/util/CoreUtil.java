package com.frbentes.omdbchallenge.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by frbentes on 11/01/17.
 */
public class CoreUtil {

    public static final String NO_PICTURE_URL = "http://www.imdb.com/images/nopicture/medium/film.png";
    private static final String TYPE_WIFI = "WIFI";
    private static final String TYPE_MOBILE = "MOBILE";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        if (connManager == null) {
            return false;
        }

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String connType = networkInfo.getTypeName();
            if (connType.equalsIgnoreCase(TYPE_WIFI) || connType.equalsIgnoreCase(TYPE_MOBILE)) {
                return true;
            }
        }

        return false;
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity
                    .INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

}
