package com.frbentes.omdbchallenge;

import android.app.Application;

import com.orm.SugarContext;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by frbentes on 12/01/17.
 */
public class OmdbApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SugarContext.init(this);
        setupCalligraphy();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    public void setupCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

}
