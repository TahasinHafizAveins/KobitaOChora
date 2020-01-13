 package com.cpsdbd.kobitaochora.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.cpsdbd.kobitaochora.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


 public class MainActivity extends AppCompatActivity {

     private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.page_viewer);
        tabAdapter = new TabAdapter(this);
        viewPager2.setAdapter(tabAdapter);





       TabLayout tabLayout = findViewById(R.id.tablayout);


        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:{
                        tab.setText(R.string.bangla);
                        break;
                    }

                    case 1:{
                        tab.setText("English");
                        break;
                    }

                }
            }
        }
        );

        tabLayoutMediator.attach();

    }
}
