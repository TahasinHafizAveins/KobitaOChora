package com.cpsdbd.kobitaochora.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.cpsdbd.kobitaochora.bangla.bangla_fragmant.BanglaFragment;
import com.cpsdbd.kobitaochora.english.english_fragmant.EnglishFragment;

public class TabAdapter extends FragmentStateAdapter {
    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BanglaFragment();

            default:
                return new EnglishFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
