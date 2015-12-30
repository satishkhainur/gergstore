package com.example.appsriv01.gergstore.Activity;

/**
 * Created by vibhor on 13/8/15.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ApplicationUtility {
    ConnectivityManager connectivityManager;
    NetworkInfo info;

    public boolean checkConnection(Context context) {
        boolean flag = false;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            info = connectivityManager.getActiveNetworkInfo();

            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                System.out.println(info.getTypeName());
                System.out.println("Hi in app class ********* ");
                flag = true;
            }
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {

                System.out.println("Hi in app class  mobile ********* ");
                System.out.println(info.getTypeName());
                flag = true;
            }
        } catch (Exception exception) {
            System.out.println("Exception at network connection....."
                    + exception);
        }
        return flag;
    }

}
