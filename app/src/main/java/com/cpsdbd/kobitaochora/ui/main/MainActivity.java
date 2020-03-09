package com.cpsdbd.kobitaochora.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cpsdbd.kobitaochora.R;
import com.cpsdbd.kobitaochora.ui.main.base.BaseFragment;
import com.cpsdbd.kobitaochora.model.Kobita;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class MainActivity extends BaseActivity implements MainContract.View {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private MainPresenter mPresenter;
    private RelativeLayout main_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected void onStart() {
        super.onStart();

        initactvity();
    }

    private void initactvity() {

        if (isOnline()){
            setContentView(R.layout.activity_main);

            setAds();

            viewPager = findViewById(R.id.page_viewer);
            tabLayout = findViewById(R.id.tablayout);

            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);

            mPresenter = new MainPresenter(this);
            mPresenter.getAllPoem();
        }
        else {

            BaseActivity.showNoConnectionDialog(this);
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        if(pagerAdapter==null){
            pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        }
        pagerAdapter.addFragment(new BaseFragment(), getString(R.string.bangla));
        pagerAdapter.addFragment(new BaseFragment(), getString(R.string.english));

        viewPager.setAdapter(pagerAdapter);


    }


    @Override
    public void updateFragment(List<Kobita> banglaPoems, List<Kobita> englishPoems) {
        BaseFragment banglaFragment = (BaseFragment) pagerAdapter.getItem(0);
        BaseFragment englishFragment = (BaseFragment) pagerAdapter.getItem(1);

        banglaFragment.updateKobita(banglaPoems);
        englishFragment.updateKobita(englishPoems);
    }
}
