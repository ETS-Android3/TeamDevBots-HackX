package com.example.couponbazar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.couponbazar.Model.MyCoupons;
import com.example.couponbazar.R;

import java.util.ArrayList;

public class ThirdAdapter extends RecyclerView.Adapter<ThirdAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyCoupons> list;

    public ThirdAdapter() {
    }

    public ThirdAdapter(ArrayList<MyCoupons> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.mycouponsitems,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MyCoupons m = list.get(position);
        holder.code3.setText(m.getCode());
        holder.brand3.setText(m.getBrand());
        holder.ben3.setText(m.getBenefits());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView code3,brand3,ben3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            code3=itemView.findViewById(R.id.code3);
            brand3=itemView.findViewById(R.id.brand3);
            ben3=itemView.findViewById(R.id.ben3);
        }
    }

}
