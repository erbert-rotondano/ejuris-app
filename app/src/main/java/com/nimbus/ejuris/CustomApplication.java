package com.nimbus.ejuris;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by nimbus on 22/09/17.
 */
public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        // Get a Realm instance for this thread
    }

}
