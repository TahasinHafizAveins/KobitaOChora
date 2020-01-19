package com.cpsdbd.kobitaochora.ui.main;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cpsdbd.kobitaochora.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class BaseActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





//        InterstitialAd interstitialAd = new InterstitialAd(this);
//        interstitialAd.setAdUnitId("ca-app-pub-9933816283866409/4477970674");
//        interstitialAd.loadAd(new AdRequest.Builder().build());
//        interstitialAd.show();
    }

    public  void setAds(){
        mAdView = findViewById(R.id.ad_View);
        //mAdView.setAdSize(AdSize.SMART_BANNER);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
