 package com.cpsdbd.kobitaochora.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.cpsdbd.kobitaochora.R;
import com.cpsdbd.kobitaochora.bangla.bangla_fragmant.BanglaFragment;
import com.cpsdbd.kobitaochora.english.english_fragmant.EnglishFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


 public class MainActivity extends AppCompatActivity {

     private TabLayout tabLayout;
     private ViewPager viewPager;
     private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.page_viewer);
        tabLayout = findViewById(R.id.tablayout);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

     private void setupViewPager(ViewPager viewPager) {

         if(pagerAdapter==null){
             pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
         }
         pagerAdapter.addFragment(new BanglaFragment(), getString(R.string.bangla));
         pagerAdapter.addFragment(new EnglishFragment(), getString(R.string.english));

         viewPager.setAdapter(pagerAdapter);


     }
}
