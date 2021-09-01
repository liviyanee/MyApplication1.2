package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.myapplication.onboard.firstfragment.FirstFragment;
import com.example.myapplication.onboard.secondfragment.SecondFragment;
import com.example.myapplication.onboard.thirdfragment.ThirdFragment;

import org.jetbrains.annotations.NotNull;

public class OnBoardAdapter extends FragmentStatePagerAdapter {
    FragmentManager manager;
    public OnBoardAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
