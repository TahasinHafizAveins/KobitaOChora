package com.cpsdbd.kobitaochora;

import android.app.Application;
import android.util.Log;

import com.cpsdbd.kobitaochora.firebase.MyDatabase;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.FirebaseDatabase;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

       /* Log.d("Myapp","Myapp called");
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        MyDatabase.getPoemRef().keepSynced(true);
*/
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d("adddd","ads init");
            }
        });
    }
}
