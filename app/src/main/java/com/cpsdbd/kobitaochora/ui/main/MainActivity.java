 package com.cpsdbd.kobitaochora.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.cpsdbd.kobitaochora.R;
import com.cpsdbd.kobitaochora.ui.main.base.BaseFragment;
import com.cpsdbd.kobitaochora.model.Kobita;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


 public class MainActivity extends AppCompatActivity implements MainContract.View {

     private TabLayout tabLayout;
     private ViewPager viewPager;
     private ViewPagerAdapter pagerAdapter;
     private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.page_viewer);
        tabLayout = findViewById(R.id.tablayout);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        mPresenter = new MainPresenter(this);

        mPresenter.getAllPoem();

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
