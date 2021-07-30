package com.example.couponbazar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
