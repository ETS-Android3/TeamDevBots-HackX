package com.example.couponbazar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.MyViewHolder>{

    Context context;
    ArrayList<ManageSales> list;

    public FirstAdapter(ArrayList<ManageSales> list) {
//        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_item,parent,false);
            return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstAdapter.MyViewHolder holder, int position) {
        ManageSales m =list.get(position);
        holder.cou_ben.setText(m.getBenefits());
        holder.cou_brand.setText(m.getBrand());
        holder.cou_code.setText(m.getCode());
        holder.cou_price.setText(m.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cou_brand,cou_ben,cou_price,cou_code;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cou_code=itemView.findViewById(R.id.coucode);
            cou_brand=itemView.findViewById(R.id.coubrand);
            cou_ben=itemView.findViewById(R.id.couben);
            cou_price=itemView.findViewById(R.id.couprice);

        }
    }
}
