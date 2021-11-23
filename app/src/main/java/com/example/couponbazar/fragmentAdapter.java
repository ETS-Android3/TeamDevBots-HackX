package com.example.couponbazar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.couponbazar.Fragments.aboutUsFragment;
import com.example.couponbazar.Fragments.addSaleFragment;
import com.example.couponbazar.Fragments.buyFragment;
import com.example.couponbazar.Fragments.contactUsFragment;
import com.example.couponbazar.Fragments.yourBuyFragment;
import com.example.couponbazar.Fragments.yourSaleFragment;

public class fragmentAdapter extends FragmentStateAdapter {

    public fragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new addSaleFragment();
            case 2:
                return new buyFragment();
            case 3:
                return new yourSaleFragment();
            case 4:
                return new yourBuyFragment();
            case 5:
                return new contactUsFragment();
        }
        return new aboutUsFragment();
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
