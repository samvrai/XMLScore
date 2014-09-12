package com.aeg.xmlscore;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by nemo on 9/11/14.
 *
 */
public class Language extends Service{
    private static Language mLanguage;


    private Language() {

    }


    public void onCreate() {

    }

    public void onDestroy() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static Language getLanguage() {
        if(mLanguage == null) {
            mLanguage = new Language();
        }
        return mLanguage;
    }

    public void changeLanguage(String pLng) {

        if(pLng == getString(R.string.languages)) {

        } else {

        }
    }
}
